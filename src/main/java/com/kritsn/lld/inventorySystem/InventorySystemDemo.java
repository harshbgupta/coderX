package com.kritsn.lld.inventorySystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class SKU {
    final String skuId;
    final Map<String, String> attributes;
    SKU(String skuId) {
        this.skuId = skuId;
        this.attributes = new HashMap<>();
    }
}

/**
 * Two separate counters, not one. This is the modeling decision that makes
 * "reserve now, confirm/release later" possible at all — a single
 * `quantity` field can't distinguish "sold" from "spoken for but pending."
 */
class InventoryItem {
    final String skuId;
    final AtomicInteger quantity;         // available for new reservations
    final AtomicInteger reservedQuantity; // currently held by in-flight checkouts

    InventoryItem(String skuId, int initialQty) {
        this.skuId = skuId;
        this.quantity = new AtomicInteger(initialQty);
        this.reservedQuantity = new AtomicInteger(0);
    }
}

enum ReservationStatus { RESERVED, PAYMENT_IN_PROGRESS, CONFIRMED, RELEASED }

class StockReservation {
    final String id;
    final String skuId;
    final int qty;
    volatile ReservationStatus status; // volatile: written by checkout thread, read by TTL-sweep thread
    final long createdAt;
    volatile String paymentTransactionId;

    StockReservation(String skuId, int qty) {
        this.id = UUID.randomUUID().toString();
        this.skuId = skuId;
        this.qty = qty;
        this.status = ReservationStatus.RESERVED;
        this.createdAt = System.currentTimeMillis();
    }
}

interface PaymentGatewayClient {
    PaymentStatus checkStatus(String paymentTransactionId);
}
enum PaymentStatus { SUCCESS, FAILED, PENDING, NOT_FOUND }

class InventoryService {
    private final Map<String, InventoryItem> items = new ConcurrentHashMap<>();
    private final Map<String, StockReservation> reservations = new ConcurrentHashMap<>();
    private static final long RESERVATION_TTL_MILLIS = 5 * 60 * 1000; // 5 minutes

    void restock(String skuId, int qty) {
        items.computeIfAbsent(skuId, k -> new InventoryItem(skuId, 0))
                .quantity.addAndGet(qty);
    }

    /**
     * THE core concurrency-safe operation. Optimistic, lock-free via CAS:
     * read current value, compute the new value, atomically swap ONLY if
     * nobody else changed it since the read; otherwise retry the loop.
     * This is the single-JVM stand-in for what a real distributed system
     * would do via Redis DECRBY or a DB row with a version column.
     */
    Optional<StockReservation> reserveStock(String skuId, int qty) {
        InventoryItem item = items.get(skuId);
        if (item == null) return Optional.empty();

        while (true) {
            int available = item.quantity.get();
            if (available < qty) return Optional.empty(); // not enough stock — reject, no retry needed
            if (item.quantity.compareAndSet(available, available - qty)) {
                item.reservedQuantity.addAndGet(qty);
                StockReservation reservation = new StockReservation(skuId, qty);
                reservations.put(reservation.id, reservation);
                return Optional.of(reservation);
            }
            // CAS failed — another thread changed quantity concurrently; loop and retry the read
        }
    }

    /**
     * Called the MOMENT before hitting the payment gateway — not after.
     * This is what protects against the crash-between-charge-and-confirm
     * scenario: once this state is set, the TTL sweep will never blindly
     * release this reservation; it gets flagged for reconciliation instead.
     */
    void markPaymentInProgress(String reservationId, String paymentTransactionId) {
        StockReservation r = getOrThrow(reservationId);
        if (r.status != ReservationStatus.RESERVED) {
            throw new IllegalStateException("Cannot mark payment in progress from state: " + r.status);
        }
        r.paymentTransactionId = paymentTransactionId;
        r.status = ReservationStatus.PAYMENT_IN_PROGRESS;
    }

    void confirmReservation(String reservationId) {
        StockReservation r = getOrThrow(reservationId);
        if (r.status != ReservationStatus.RESERVED && r.status != ReservationStatus.PAYMENT_IN_PROGRESS) {
            throw new IllegalStateException("Cannot confirm — already finalized: " + r.status);
        }
        InventoryItem item = items.get(r.skuId);
        item.reservedQuantity.addAndGet(-r.qty); // permanently removed — sold, not returned to pool
        r.status = ReservationStatus.CONFIRMED;
    }

    void releaseReservation(String reservationId) {
        StockReservation r = getOrThrow(reservationId);
        if (r.status == ReservationStatus.CONFIRMED || r.status == ReservationStatus.RELEASED) {
            throw new IllegalStateException("Cannot release — already finalized: " + r.status);
        }
        InventoryItem item = items.get(r.skuId);
        item.reservedQuantity.addAndGet(-r.qty);
        item.quantity.addAndGet(r.qty); // returned to available pool
        r.status = ReservationStatus.RELEASED;
    }

    /**
     * Background sweep — run on a schedule (e.g., every 30s). Deliberately
     * asymmetric: RESERVED reservations are safe to auto-release (payment
     * was never attempted); PAYMENT_IN_PROGRESS ones are NEVER auto-released
     * — they need reconciliation against the actual payment gateway, since
     * local state alone can't distinguish "abandoned" from "charged, but
     * confirmation crashed."
     */
    void releaseExpiredReservations() {
        long now = System.currentTimeMillis();
        for (StockReservation r : reservations.values()) {
            long age = now - r.createdAt;
            if (r.status == ReservationStatus.RESERVED && age > RESERVATION_TTL_MILLIS) {
                releaseReservation(r.id);
                System.out.println("Auto-released abandoned reservation: " + r.id);
            } else if (r.status == ReservationStatus.PAYMENT_IN_PROGRESS && age > RESERVATION_TTL_MILLIS) {
                System.out.println("STUCK — needs reconciliation: " + r.id
                        + " (paymentTxnId=" + r.paymentTransactionId + ")");
            }
        }
    }

    void reconcileStuckReservation(String reservationId, PaymentGatewayClient gatewayClient) {
        StockReservation r = reservations.get(reservationId);
        if (r == null || r.status != ReservationStatus.PAYMENT_IN_PROGRESS) return;

        PaymentStatus actual = gatewayClient.checkStatus(r.paymentTransactionId); // ask the source of truth
        switch (actual) {
            case SUCCESS -> confirmReservation(reservationId);
            case FAILED, NOT_FOUND -> releaseReservation(reservationId);
            case PENDING -> System.out.println("Still pending, re-check later: " + reservationId);
        }
    }

    private StockReservation getOrThrow(String reservationId) {
        StockReservation r = reservations.get(reservationId);
        if (r == null) throw new IllegalArgumentException("Invalid reservation: " + reservationId);
        return r;
    }

    int getAvailableQuantity(String skuId) {
        InventoryItem item = items.get(skuId);
        return item == null ? 0 : item.quantity.get();
    }
}

public class InventorySystemDemo {
    public static void main(String[] args) throws InterruptedException {
        InventoryService inventory = new InventoryService();
        inventory.restock("SKU1", 5);

        // simulate two concurrent checkouts racing for the last units
        Runnable checkout = () -> {
            Optional<StockReservation> r = inventory.reserveStock("SKU1", 3);
            if (r.isPresent()) {
                System.out.println(Thread.currentThread().getName() + " reserved 3 units: " + r.get().id);
                inventory.markPaymentInProgress(r.get().id, "TXN-" + r.get().id);
                inventory.confirmReservation(r.get().id);
            } else {
                System.out.println(Thread.currentThread().getName() + " failed — insufficient stock");
            }
        };

        Thread t1 = new Thread(checkout, "Checkout-1");
        Thread t2 = new Thread(checkout, "Checkout-2");
        t1.start(); t2.start();
        t1.join(); t2.join();
        // only ONE of these should succeed — 5 units, both want 3

        System.out.println("Remaining available: " + inventory.getAvailableQuantity("SKU1"));
    }
}