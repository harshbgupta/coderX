package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */

// Step 1: the subsystem classes — each already has its own focused responsibility
class InventoriesService {
    void reserveStock(String skuId, int qty) {
        System.out.println("Reserving " + qty + " units of " + skuId);
    }
}
class PaymentService {
    void charge(String userId, double amount) {
        System.out.println("Charging " + userId + " ₹" + amount);
    }
}
class NotificationService {
    void send(String userId, String message) {
        System.out.println("Notifying " + userId + ": " + message);
    }
}

// Step 2 & 3: the Facade — orchestrates the subsystem, exposes one simple method
class OrderFacade {
    private final InventoriesService inventory = new InventoriesService();
    private final PaymentService payment = new PaymentService();
    private final NotificationService notification = new NotificationService();

    void placeOrder(String skuId, int qty, double amount, String userId) {
        inventory.reserveStock(skuId, qty);
        payment.charge(userId, amount);
        notification.send(userId, "Order placed!");
    }
}

public class _08FacadeDemo {
    public static void main(String[] args) {
        // Step 4: caller only ever touches OrderFacade
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder("SKU123", 2, 999.0, "user1");
    }
}
