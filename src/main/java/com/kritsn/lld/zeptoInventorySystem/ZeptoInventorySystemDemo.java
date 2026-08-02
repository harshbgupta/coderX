package com.kritsn.lld.zeptoInventorySystem;

/*
    SCALE & CONFIGURATION:
    - How many dark stores? (10? 100? 1000+?)
    - How many products per store? (1K? 10K? 100K?)
    - Average orders per day? (1K? 10K? 100K?)
    - Geographic coverage? (Single city? Multiple cities?)

    FUNCTIONALITY:
    - Direct delivery from multiple stores simultaneously?
    - How many stores to check per item? (1? 3? 5?)
    - Delivery radius per store? (5km? 10km?)
    - Delivery SLA? (8 minutes? 10 minutes?)

    CONSTRAINTS:
    - Maximum items per order?
    - Capacity per dark store?
    - Concurrent orders per store?
    - Real-time inventory sync?

    EDGE CASES:
    - What if product out of stock everywhere?
    - What if no stores within radius?
    - What if transfer fails mid-operation?
    - Multiple customers ordering same item simultaneously?

    -------------------------------
    Entities:
    - Product
    - DarkStore
    - Location
    - Order
    - FulfillmentPlan
    - StoreDelivery
    - AllocationService
    - InventoryManager
    - AllocationStrategy: interface
    - LocationService: interface
    - RiderDispatchService: Interface

    Error:
    - ProductNotFoundException
    - OutOfStockException
    - InvalidLocationException
    - FulfillmentFailedException
 */


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Product {
    private String sku;
    private String name;
    private double price;
    private double weight;

    public Product(String sku, String name, double price, double weight) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }
}

class Location {
    private double latitude;
    private double longitude;
    private static final double EARTH_RADIUS_KM = 6371;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Calculate distance using Haversine formula
     * <p>
     * Why: Accurate great-circle distance on Earth
     * Benefit: Real-world geographic accuracy
     * Formula: d = 2*R*asin(sqrt(a))
     * where a = sin²(Δlat/2) + cos(lat1)*cos(lat2)*sin²(Δlon/2)
     * Time Complexity: O(1)
     */
    public double distanceTo(Location other) {
        double lat1 = Math.toRadians(this.latitude);
        double lat2 = Math.toRadians(other.latitude);
        double deltaLat = Math.toRadians(other.latitude - this.latitude);
        double deltaLon = Math.toRadians(other.longitude - this.longitude);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public boolean isValid() {
        return latitude >= -90 && latitude <= 90 &&
                longitude >= -180 && longitude <= 180;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

class DarkStore {
    private String storeId;
    private Location location;
    private int maxCapacity;
    private Map<String, Integer> stockData; // synchronized map
    private boolean isActive;

    /**
     * DarkStore owns inventory internally
     * Why: Clear ownership, no separate entity
     * Benefit: Per-store locking (high concurrency)
     * Trade-off: Need storeId to query
     */
    public DarkStore(String storeId, Location location, int maxCapacity) {
        this.storeId = storeId;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.stockData = new ConcurrentHashMap<>();
        this.isActive = true;
    }

    /**
     * Get stock quantity (thread-safe)
     */
    public synchronized int getStock(String sku) {
        return stockData.getOrDefault(sku, 0);
    }

    /**
     * Check if stock available (thread-safe)
     */
    public synchronized boolean hasStock(String sku, int quantity) {
        return getStock(sku) >= quantity;
    }

    /**
     * Remove stock for order fulfillment (thread-safe)
     * DIRECT delivery: no transfers!
     */
    public synchronized boolean removeStock(String sku, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        int current = stockData.getOrDefault(sku, 0);

        if (current < quantity) {
            System.out.println("[STORE " + storeId + "] FAILED to remove " +
                    quantity + " of " + sku +
                    " (Available: " + current + ")");
            return false;
        }

        stockData.put(sku, current - quantity);
        System.out.println("[STORE " + storeId + "] Removed " + quantity +
                " of " + sku + " (Remaining: " +
                stockData.get(sku) + ")");
        return true;
    }

    /**
     * Add stock (restocking/returns)
     */
    public synchronized void addStock(String sku, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        int current = stockData.getOrDefault(sku, 0);
        stockData.put(sku, current + quantity);
        System.out.println("[STORE " + storeId + "] Added " + quantity +
                " of " + sku + " (Total: " +
                stockData.get(sku) + ")");
    }

    public String getStoreId() {
        return storeId;
    }

    public Location getLocation() {
        return location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isActive() {
        return isActive;
    }
}

class OrderItem {
    private int quantity;
    private Product product;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getSku() {
        return product.getSku();
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}

class Order {
    private String orderId;
    private String customerId;
    private Location customerLocation;
    private List<OrderItem> items;
    private OrderStatus status;
    private long createdAt;

    enum OrderStatus {
        PENDING, FULFILLED, FAILED
    }

    public Order(String orderId, String customerId, Location customerLocation) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerLocation = customerLocation;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.createdAt = System.currentTimeMillis();
    }

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        items.add(new OrderItem(product, quantity));
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Location getCustomerLocation() {
        return customerLocation;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

class StoreDelivery {
    private String deliveryId;
    private String storeId;
    private DarkStore store;
    private List<OrderItem> items;
    private long estimatedDeliveryTime;
    private String riderId;
    private DeliveryStatus status;

    enum DeliveryStatus {
        PENDING, DISPATCHED, DELIVERED, FAILED
    }

    public StoreDelivery(String storeId, DarkStore store,
                         List<OrderItem> items, long eta) {
        this.deliveryId = UUID.randomUUID().toString();
        this.storeId = storeId;
        this.store = store;
        this.items = items;
        this.estimatedDeliveryTime = eta;
        this.status = DeliveryStatus.PENDING;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public DarkStore getStore() {
        return store;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public long getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}

class FulfillmentPlan {
    private String orderId;
    private List<StoreDelivery> deliveries;
    private long maxDeliveryETA;
    private String fulfillmentStatus;

    public FulfillmentPlan(String orderId) {
        this.orderId = orderId;
        this.deliveries = new ArrayList<>();
        this.fulfillmentStatus = "PENDING";
    }

    public void addStoreDelivery(StoreDelivery delivery) {
        deliveries.add(delivery);
        // Update max ETA
        maxDeliveryETA = Math.max(maxDeliveryETA,
                delivery.getEstimatedDeliveryTime());
    }

    public String getOrderId() {
        return orderId;
    }

    public List<StoreDelivery> getDeliveries() {
        return deliveries;
    }

    public long getMaxDeliveryETA() {
        return maxDeliveryETA;
    }

    public String getStatus() {
        return fulfillmentStatus;
    }

    public void setStatus(String status) {
        fulfillmentStatus = status;
    }
}

class AllocationService {
    private List<DarkStore> stores;
    private static final double SEARCH_RADIUS_KM = 5.0;
    private static final double PICKUP_TIME_MINUTES = 1.0;
    private static final double RIDER_SPEED_KM_PER_MINUTE = 0.5; // 30km/hour

    public AllocationService(List<DarkStore> stores) {
        this.stores = stores;
    }

    /**
     * Allocate items to nearest stores for DIRECT delivery
     * <p>
     * Why: Strategy Pattern - flexible allocation algorithm
     * Benefit: Can swap greedy, ML, or other algorithms
     * Trade-off: Slightly more complex
     * <p>
     * Algorithm (Greedy - nearest store per item):
     * 1. For each item in order:
     * - Find nearest store with stock
     * - Allocate to that store
     * - Calculate ETA from that store
     * 2. Create StoreDelivery for each participating store
     * 3. Return plan with max ETA
     */
    public FulfillmentPlan allocateOrder(Order order)
            throws FulfillmentFailedException {

        System.out.println("\n[ALLOCATION] Processing order: " +
                order.getOrderId());

        FulfillmentPlan plan = new FulfillmentPlan(order.getOrderId());
        Map<String, List<OrderItem>> storeAllocations = new HashMap<>();

        // For each item, find nearest store with stock
        for (OrderItem item : order.getItems()) {
            DarkStore nearestStore = findNearestStoreForItem(
                    item.getSku(),
                    order.getCustomerLocation()
            );

            if (nearestStore == null) {
                throw new FulfillmentFailedException(
                        "Item " + item.getSku() + " not available anywhere"
                );
            }

            // Check stock
            if (!nearestStore.hasStock(item.getSku(), item.getQuantity())) {
                throw new FulfillmentFailedException(
                        "Item " + item.getSku() + " not available in quantities"
                );
            }

            // Allocate to this store
            storeAllocations.putIfAbsent(nearestStore.getStoreId(),
                    new ArrayList<>());
            storeAllocations.get(nearestStore.getStoreId()).add(item);

            System.out.println("[ALLOCATION] Item " + item.getSku() +
                    " (qty: " + item.getQuantity() +
                    ") → Store " + nearestStore.getStoreId());
        }

        // Create StoreDelivery for each participating store
        for (Map.Entry<String, List<OrderItem>> entry :
                storeAllocations.entrySet()) {

            String storeId = entry.getKey();
            List<OrderItem> items = entry.getValue();
            DarkStore store = stores.stream()
                    .filter(s -> s.getStoreId().equals(storeId))
                    .findFirst()
                    .get();

            // Calculate ETA from this store to customer
            long eta = calculateETA(store.getLocation(),
                    order.getCustomerLocation());

            StoreDelivery delivery = new StoreDelivery(storeId, store, items, eta);
            plan.addStoreDelivery(delivery);

            System.out.println("[DELIVERY] Store " + storeId +
                    " → Customer ETA: " +
                    (eta / 1000) + " seconds");
        }

        System.out.println("[FULFILLMENT] Max ETA: " +
                (plan.getMaxDeliveryETA() / 1000) + " seconds\n");

        return plan;
    }

    /**
     * Find nearest store with item in stock
     */
    private DarkStore findNearestStoreForItem(String sku, Location customerLoc) {
        return stores.stream()
                .filter(store -> store.isActive())
                .filter(store -> store.hasStock(sku, 1))
                .filter(store -> store.getLocation()
                        .distanceTo(customerLoc) <= SEARCH_RADIUS_KM)
                .min((s1, s2) -> Double.compare(
                        s1.getLocation().distanceTo(customerLoc),
                        s2.getLocation().distanceTo(customerLoc)
                ))
                .orElse(null);
    }

    /**
     * Calculate ETA from store to customer
     * <p>
     * Formula:
     * distance_km = haversine(store, customer)
     * pickup_time = 1 minute (prep + packing)
     * delivery_time = distance_km / rider_speed
     * eta_seconds = (pickup_time + delivery_time) * 60
     */
    private long calculateETA(Location storeLocation, Location customerLocation) {
        double distanceKm = storeLocation.distanceTo(customerLocation);

        // Time in minutes
        double pickupMinutes = PICKUP_TIME_MINUTES;
        double deliveryMinutes = distanceKm / RIDER_SPEED_KM_PER_MINUTE;
        double totalMinutes = pickupMinutes + deliveryMinutes;

        // Convert to milliseconds
        return (long) (totalMinutes * 60 * 1000);
    }
}

class InventoryManager {
    private Map<String, DarkStore> stores;
    private Map<String, Product> products;
    private AllocationService allocationService;
    private Map<String, FulfillmentPlan> fulfillmentPlans;

    public InventoryManager() {
        this.stores = new ConcurrentHashMap<>();
        this.products = new ConcurrentHashMap<>();
        this.fulfillmentPlans = new ConcurrentHashMap<>();
        this.allocationService = new AllocationService(
                new ArrayList<>(stores.values())
        );
    }

    public void registerStore(DarkStore store) {
        stores.put(store.getStoreId(), store);
        System.out.println("[SYSTEM] Registered store: " + store.getStoreId() +
                " at " + store.getLocation().getLatitude() + ", " +
                store.getLocation().getLongitude());
    }

    public void registerProduct(Product product) {
        products.put(product.getSku(), product);
        System.out.println("[SYSTEM] Registered product: " + product.getSku());
    }

    /**
     * Main order placement flow
     * <p>
     * Flow:
     * 1. Validate order (products, quantities)
     * 2. Allocate items to nearest stores (DIRECT delivery)
     * 3. Remove items from stores (atomic per-store)
     * 4. Dispatch riders in parallel
     * 5. Return fulfillment plan
     */
    public FulfillmentPlan placeOrder(Order order)
            throws FulfillmentFailedException, ProductNotFoundException {

        System.out.println("\n" + "=".repeat(60));
        System.out.println("[ORDER] " + order.getOrderId() +
                " from customer " + order.getCustomerId());
        System.out.println("=".repeat(60));

        // Validate order
        for (OrderItem item : order.getItems()) {
            if (!products.containsKey(item.getSku())) {
                throw new ProductNotFoundException(
                        "Product not found: " + item.getSku()
                );
            }
        }

        // Allocate to nearest stores
        FulfillmentPlan plan = allocationService.allocateOrder(order);

        // Remove items from stores (atomic per-store)
        for (StoreDelivery delivery : plan.getDeliveries()) {
            DarkStore store = stores.get(delivery.getStoreId());

            for (OrderItem item : delivery.getItems()) {
                boolean removed = store.removeStock(item.getSku(),
                        item.getQuantity());
                if (!removed) {
                    throw new FulfillmentFailedException(
                            "Failed to remove item: " + item.getSku()
                    );
                }
            }
        }

        // Update order status
        order.setStatus(Order.OrderStatus.FULFILLED);
        fulfillmentPlans.put(order.getOrderId(), plan);
        plan.setStatus("FULFILLED");

        // Print summary
        System.out.println("\n[SUMMARY] Order " + order.getOrderId());
        System.out.println("  Total deliveries: " + plan.getDeliveries().size());
        System.out.println("  Max ETA: " +
                (plan.getMaxDeliveryETA() / 1000) + " seconds");
        System.out.println("[SUCCESS] Order ready for dispatch!\n");
        System.out.println("=".repeat(60) + "\n");

        return plan;
    }

    public void printInventoryStatus(String storeId) {
        DarkStore store = stores.get(storeId);
        if (store != null) {
            System.out.println("\n[INVENTORY] Store: " + storeId);
            System.out.println("  Location: " + store.getLocation().getLatitude() +
                    ", " + store.getLocation().getLongitude());
            System.out.println("  Capacity: " + store.getMaxCapacity());
        }
    }

    public void printAllStoresStatus() {
        System.out.println("\n[SYSTEM STATUS]");
        for (DarkStore store : stores.values()) {
            System.out.println("  Store: " + store.getStoreId() +
                    " Active: " + store.isActive());
        }
    }
}

class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}

class OutOfStockException extends Exception {
    public OutOfStockException(String msg) {
        super(msg);
    }
}

class InvalidLocationException extends Exception {
    public InvalidLocationException(String msg) {
        super(msg);
    }
}

class FulfillmentFailedException extends Exception {
    public FulfillmentFailedException(String msg) {
        super(msg);
    }
}

public class ZeptoInventorySystemDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   ZEPTO INVENTORY MANAGEMENT SYSTEM");
        System.out.println("   Direct Multi-Store Delivery");
        System.out.println("=".repeat(60));

        // Create inventory manager
        InventoryManager inventory = new InventoryManager();

        // Register products
        Product milk = new Product("SKU-001", "Milk 1L", 60, 1000);
        Product bread = new Product("SKU-002", "Bread", 40, 500);
        Product eggs = new Product("SKU-003", "Eggs 6pc", 50, 300);
        Product butter = new Product("SKU-004", "Butter 100g", 80, 100);

        inventory.registerProduct(milk);
        inventory.registerProduct(bread);
        inventory.registerProduct(eggs);
        inventory.registerProduct(butter);

        // Register dark stores (with GPS coordinates - Bengaluru)
        DarkStore E1 = new DarkStore("E1", new Location(12.9716, 77.5946), 500);
        DarkStore E2 = new DarkStore("E2", new Location(12.9750, 77.5950), 500);
        DarkStore E3 = new DarkStore("E3", new Location(12.9680, 77.5900), 500);

        inventory.registerStore(E1);
        inventory.registerStore(E2);
        inventory.registerStore(E3);

        // Stock stores
        E1.addStock("SKU-001", 20);  // Milk
        E1.addStock("SKU-003", 15);  // Eggs

        E2.addStock("SKU-002", 25);  // Bread
        E2.addStock("SKU-004", 10);  // Butter

        E3.addStock("SKU-001", 10);  // Milk
        E3.addStock("SKU-002", 15);  // Bread


    }
}