package com.kritsn.lld.parkingLot;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

enum VehicleType { MOTORCYCLE, CAR, TRUCK }

class Vehicle {
    final String licensePlate;
    final VehicleType type;
    Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }
}

/**
 * Abstract spot. Concurrency safety lives HERE, at the spot level — not in
 * the Level or ParkingLot — because the actual race condition is "two
 * threads both try to claim THIS specific spot," which is a per-spot
 * concern. Putting the lock at a higher level (e.g., locking the whole
 * Level while searching) would work but serializes unrelated spots
 * unnecessarily — a senior-level distinction worth stating out loud in
 * an interview.
 */
abstract class ParkingSpot {
    final String id;
    private volatile boolean isFree = true; // volatile: visibility across threads for the read-only check
    private Vehicle vehicle;
    private final ReentrantLock lock = new ReentrantLock();

    ParkingSpot(String id) { this.id = id; }

    abstract boolean canFitVehicle(Vehicle v);

    /**
     * Atomic check-then-claim. This is the ONE piece of code in the whole
     * design that prevents overselling a spot to two vehicles at once.
     */
    boolean tryOccupy(Vehicle v) {
        if (!canFitVehicle(v)) return false; // cheap check first, no lock needed for a definite "no"
        lock.lock();
        try {
            if (!isFree) return false; // re-check under lock — another thread may have claimed it
            isFree = false;
            vehicle = v;
            return true;
        } finally {
            lock.unlock();
        }
    }

    void vacate() {
        lock.lock();
        try {
            isFree = true;
            vehicle = null;
        } finally {
            lock.unlock();
        }
    }

    boolean isFree() { return isFree; } // fast, lock-free read for search/filtering
}

class CompactSpot extends ParkingSpot {
    CompactSpot(String id) { super(id); }
    public boolean canFitVehicle(Vehicle v) {
        return v.type == VehicleType.MOTORCYCLE || v.type == VehicleType.CAR;
    }
}

class LargeSpot extends ParkingSpot {
    LargeSpot(String id) { super(id); }
    public boolean canFitVehicle(Vehicle v) { return true; } // fits anything, including trucks
}

/**
 * STRATEGY PATTERN — pricing is the one thing in a parking lot that
 * genuinely changes independently of everything else (surge pricing,
 * weekend rates, membership tiers). Isolating it means ParkingLot never
 * needs a code change when Finance changes the pricing model.
 */
interface PricingStrategy {
    double calculateFee(long entryTimeMillis, long exitTimeMillis);
}

class HourlyPricing implements PricingStrategy {
    private final double ratePerHour;
    HourlyPricing(double ratePerHour) { this.ratePerHour = ratePerHour; }

    public double calculateFee(long entry, long exit) {
        long millis = exit - entry;
        long hours = (long) Math.ceil(millis / (1000.0 * 60 * 60)); // round UP — 61 minutes bills as 2 hours
        return Math.max(1, hours) * ratePerHour;
    }
}

class Level {
    final int floor;
    private final List<ParkingSpot> spots = new ArrayList<>();

    Level(int floor) { this.floor = floor; }
    void addSpot(ParkingSpot spot) { spots.add(spot); }

    /**
     * Linear scan is intentional and fine at interview scope — for a real
     * system with thousands of spots per level, this would be replaced with
     * per-type free-spot queues/counts so lookup isn't O(n) on every park
     * request. Worth saying this out loud even if you don't implement it.
     */
    Optional<ParkingSpot> findAndOccupySpot(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if (spot.isFree() && spot.tryOccupy(v)) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }
}

class Ticket {
    final String id;
    final Vehicle vehicle;
    final ParkingSpot spot;
    final long entryTime;

    Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
    }
}

/**
 * SINGLETON — one ParkingLot instance owns the canonical list of levels
 * and active tickets for this facility. final class + private constructor
 * + volatile double-checked locking, per your earlier Singleton deep dive.
 */
final class ParkingLot {
    private static volatile ParkingLot instance;

    private final List<Level> levels = new ArrayList<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();
    private PricingStrategy pricingStrategy;

    private ParkingLot() {
        if (instance != null) {
            throw new IllegalStateException("Instance already exists — use getInstance()");
        }
        this.pricingStrategy = new HourlyPricing(20.0); // sensible default
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) instance = new ParkingLot();
            }
        }
        return instance;
    }

    public void addLevel(Level level) { levels.add(level); }
    public void setPricingStrategy(PricingStrategy strategy) { this.pricingStrategy = strategy; }

    public synchronized Optional<Ticket> parkVehicle(Vehicle vehicle) {
        // synchronized here guards activeTickets' map mutation, NOT the spot search
        // itself (that's already thread-safe per-spot) — keeps the lock's scope
        // as narrow as correctness allows
        for (Level level : levels) {
            Optional<ParkingSpot> spot = level.findAndOccupySpot(vehicle);
            if (spot.isPresent()) {
                Ticket ticket = new Ticket(vehicle, spot.get());
                activeTickets.put(ticket.id, ticket);
                return Optional.of(ticket);
            }
        }
        return Optional.empty(); // lot full
    }

    public synchronized double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) throw new IllegalArgumentException("Invalid or already-used ticket: " + ticketId);
        ticket.spot.vacate();
        return pricingStrategy.calculateFee(ticket.entryTime, System.currentTimeMillis());
    }
}

public class ParkingLotDemo {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = ParkingLot.getInstance();
        Level level1 = new Level(1);
        level1.addSpot(new CompactSpot("L1-C1"));
        level1.addSpot(new LargeSpot("L1-L1"));
        lot.addLevel(level1);

        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);
        Optional<Ticket> ticket = lot.parkVehicle(car);
        ticket.ifPresent(t -> System.out.println("Parked. Ticket: " + t.id));

        Thread.sleep(50); // simulate time passing
        ticket.ifPresent(t -> System.out.println("Fee: ₹" + lot.unparkVehicle(t.id)));
    }
}