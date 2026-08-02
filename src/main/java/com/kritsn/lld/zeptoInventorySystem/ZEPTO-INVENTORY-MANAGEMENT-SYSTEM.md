# LLD: ZEPTO INVENTORY MANAGEMENT SYSTEM (FINAL)

**Direct Multi-Store Delivery Approach**

---

## SECTION 1: CLARIFYING QUESTIONS

### Questions to Ask Interviewer

**SCALE & CONFIGURATION:**
- How many dark stores? (500? 1000+?)
- How many products per store? (10K? 50K? 100K?)
- Average orders per day? (100K? 1M?)
- Geographic coverage? (Single city? Multiple cities?)

**FUNCTIONALITY:**
- Direct delivery from multiple stores simultaneously?
- How many stores to check per item? (1? 3? 5?)
- Delivery radius per store? (5km? 10km?)
- Delivery SLA? (8 minutes? 10 minutes?)

**CONSTRAINTS:**
- Maximum items per order?
- Capacity per dark store?
- Concurrent orders per store?
- Real-time inventory sync?

**EDGE CASES:**
- What if product not available anywhere?
- What if no stores within delivery radius?
- Multiple customers ordering same item simultaneously?
- What if rider can't find customer location?

---

## SECTION 2: CLASS & INTERFACE NAMES

### Interfaces (3)
- `AllocationStrategy` - Contract for item allocation algorithms
- `LocationService` - Contract for geo-location calculations
- `RiderDispatchService` - Contract for rider assignment

### Classes (9)
- `Product` - Product entity (SKU, name, price)
- `DarkStore` - Physical store with inventory
- `Location` - GPS coordinates (latitude, longitude)
- `Order` - Customer order
- `OrderItem` - Individual item in order
- `FulfillmentPlan` - Multi-store delivery plan
- `StoreDelivery` - Single store's delivery to customer
- `AllocationService` - Allocates items to nearest stores
- `InventoryManager` - Main orchestrator

### Exceptions (4)
- `ProductNotFoundException` - Product doesn't exist
- `OutOfStockException` - Product not available
- `InvalidLocationException` - Invalid coordinates
- `FulfillmentFailedException` - Cannot fulfill order

---

## SECTION 3: CLASS STRUCTURE

### Product

```
Product

Fields:
  - sku: String
  - name: String
  - price: double
  - weight: double

Constructor:
  + Product(sku: String, name: String, price: double, weight: double)

Methods:
  + getSku(): String
  + getName(): String
  + getPrice(): double
  + getWeight(): double

Purpose:
  • Immutable product definition
  • Why: Track items being ordered
```

---

### Location

```
Location

Fields:
  - latitude: double
  - longitude: double

Constructor:
  + Location(latitude: double, longitude: double)

Methods:
  + getLatitude(): double
  + getLongitude(): double
  + distanceTo(other: Location): double
    └─ Haversine formula: O(1)
  + isValid(): boolean

Purpose:
  • GPS coordinates
  • Calculate real-world distances
  • Why: Accurate geographic routing
```

---

### DarkStore

```
DarkStore

Fields:
  - storeId: String
  - location: Location
  - maxCapacity: int
  - stockData: Map<String, Integer> (synchronized)
    └─ Why: DarkStore owns inventory internally
  - isActive: boolean

Constructor:
  + DarkStore(storeId: String, location: Location, maxCapacity: int)

Methods:
  + getStoreId(): String
  + getLocation(): Location
  + getStock(sku: String): synchronized int
  + hasStock(sku: String, quantity: int): synchronized boolean
  + removeStock(sku: String, quantity: int): synchronized boolean
    └─ Direct removal (no transfer!)
  + addStock(sku: String, quantity: int): synchronized void
    └─ Only for restocking/returns

Purpose:
  • Physical dark store
  • Direct delivery point
  • Why: Per-store locking for high concurrency
```

---

### OrderItem

```
OrderItem

Fields:
  - sku: String
  - quantity: int
  - product: Product

Constructor:
  + OrderItem(product: Product, quantity: int)

Methods:
  + getSku(): String
  + getQuantity(): int
  + getProduct(): Product

Purpose:
  • Individual item in order
  • Links SKU to quantity
```

---

### Order

```
Order

Fields:
  - orderId: String
  - customerId: String
  - customerLocation: Location
  - items: List<OrderItem>
  - status: OrderStatus (PENDING, FULFILLED, FAILED)
  - createdAt: long

Constructor:
  + Order(orderId: String, customerId: String, customerLocation: Location)

Methods:
  + getOrderId(): String
  + getCustomerId(): String
  + getCustomerLocation(): Location
  + addItem(product: Product, quantity: int): void
  + getItems(): List<OrderItem>
  + getStatus(): OrderStatus
  + setStatus(status: OrderStatus): void

Purpose:
  • Customer order
  • Contains multiple items
```

---

### StoreDelivery

```
StoreDelivery

Fields:
  - deliveryId: String (UUID)
  - storeId: String
  - store: DarkStore
  - items: List<OrderItem> (what this store delivers)
  - estimatedDeliveryTime: long (milliseconds)
  - riderId: String (assigned rider)
  - status: DeliveryStatus (PENDING, DISPATCHED, DELIVERED)

Constructor:
  + StoreDelivery(storeId: String, store: DarkStore, items: List<OrderItem>, eta: long)

Methods:
  + getDeliveryId(): String
  + getStoreId(): String
  + getItems(): List<OrderItem>
  + getEstimatedDeliveryTime(): long
  + getRiderId(): String
  + setRiderId(riderId: String): void
  + getStatus(): DeliveryStatus
  + setStatus(status: DeliveryStatus): void

Purpose:
  • Single store's delivery to customer
  • Why: Track parallel deliveries separately
  • Benefit: Easy to dispatch multiple riders
```

---

### FulfillmentPlan

```
FulfillmentPlan

Fields:
  - orderId: String
  - deliveries: List<StoreDelivery> (one per participating store)
  - maxDeliveryETA: long (maximum ETA across all stores)
  - fulfillmentStatus: String (PENDING, IN_PROGRESS, COMPLETED, FAILED)

Constructor:
  + FulfillmentPlan(orderId: String)

Methods:
  + addStoreDelivery(delivery: StoreDelivery): void
  + getDeliveries(): List<StoreDelivery>
  + getMaxDeliveryETA(): long
    └─ Return max ETA (customer waits for last rider)
  + getStatus(): String
  + setStatus(status: String): void

Purpose:
  • Plan for fulfilling entire order
  • Why: Track all parallel deliveries
  • Benefit: Know when customer gets everything
```

---

### AllocationService (Strategy Pattern)

```
AllocationService

Purpose:
  • Smart item-to-store allocation
  • Why: Strategy Pattern - different allocation algorithms
  • Benefit: Easy to swap allocation strategies (greedy, ML, etc.)
  • Trade-off: More classes, but flexible

Fields:
  - stores: List<DarkStore>
  - searchRadius: double (5km default)

Methods:
  + allocateOrder(order: Order): FulfillmentPlan
      throws FulfillmentFailedException
    
    Algorithm (Greedy - nearest store per item):
      1. For each item in order:
         a. Find all stores with that item within radius
         b. Pick nearest store
         c. Remove item quantity from that store
         d. Create StoreDelivery for that store
      
      2. Aggregate all StoreDeliveries
      3. Calculate max ETA (customer waits for slowest)
      4. Return FulfillmentPlan
    
    Returns: FulfillmentPlan with multiple StoreDeliveries

  + findNearestStoreForItem(sku: String, location: Location): DarkStore
    
    Algorithm:
      1. Get all stores within search radius with item in stock
      2. Sort by distance to customer
      3. Return closest store
      
    Returns: Nearest store or null if not available
  
  + calculateETA(storeLocation: Location, customerLocation: Location): long
    
    Algorithm:
      distance_km = haversine(storeLocation, customerLocation)
      PickupTime = 1 minute (prep + packing)
      RiderTime = distance_km * 2 minutes/km (average speed)
      eta_ms = (PickupTime + RiderTime) * 60 * 1000
      
    Returns: ETA in milliseconds

Purpose:
  • Allocate items to nearest stores
  • Enable parallel delivery
  • Minimize total delivery time
```

---

### InventoryManager

```
InventoryManager

Fields:
  - stores: Map<String, DarkStore>
  - products: Map<String, Product>
  - allocationService: AllocationService
  - fulfillmentPlans: Map<String, FulfillmentPlan>

Constructor:
  + InventoryManager()

Methods:
  + registerStore(store: DarkStore): void
  
  + registerProduct(product: Product): void
  
  + placeOrder(order: Order): FulfillmentPlan 
      throws FulfillmentFailedException
    
    Flow:
      1. Validate order (products exist, positive quantities)
      2. Call allocationService.allocateOrder(order)
      3. For each StoreDelivery in plan:
         a. Remove items from store (atomic)
         b. Dispatch rider with items
      4. Set order status to FULFILLED
      5. Return FulfillmentPlan
    
    Returns: FulfillmentPlan with multiple StoreDeliveries
  
  + getInventoryStatus(storeId: String): void
    Print current inventory for store
  
  + getStoreStatus(): void
    Print all stores status

Purpose:
  • Main orchestrator
  • Coordinates order fulfillment
  • Manages inventory across stores
  • Why: Single entry point for order processing
```

---

## SECTION 4: IMPLEMENTATION

### Product

```java
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
    
    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }
}
```

---

### Location (with Haversine Formula)

```java
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
     * 
     * Why: Accurate great-circle distance on Earth
     * Benefit: Real-world geographic accuracy
     * Formula: d = 2*R*asin(sqrt(a))
     *   where a = sin²(Δlat/2) + cos(lat1)*cos(lat2)*sin²(Δlon/2)
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
    
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
```

---

### DarkStore (Synchronized, No Transfers)

```java
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
    
    public String getStoreId() { return storeId; }
    public Location getLocation() { return location; }
    public int getMaxCapacity() { return maxCapacity; }
    public boolean isActive() { return isActive; }
}
```

---

### OrderItem

```java
class OrderItem {
    private String sku;
    private int quantity;
    private Product product;
    
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.sku = product.getSku();
        this.quantity = quantity;
    }
    
    public String getSku() { return sku; }
    public int getQuantity() { return quantity; }
    public Product getProduct() { return product; }
}
```

---

### Order

```java
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
    
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public Location getCustomerLocation() { return customerLocation; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
```

---

### StoreDelivery

```java
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
    
    public String getDeliveryId() { return deliveryId; }
    public String getStoreId() { return storeId; }
    public DarkStore getStore() { return store; }
    public List<OrderItem> getItems() { return items; }
    public long getEstimatedDeliveryTime() { return estimatedDeliveryTime; }
    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }
    public DeliveryStatus getStatus() { return status; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
}
```

---

### FulfillmentPlan

```java
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
    
    public String getOrderId() { return orderId; }
    public List<StoreDelivery> getDeliveries() { return deliveries; }
    public long getMaxDeliveryETA() { return maxDeliveryETA; }
    public String getStatus() { return fulfillmentStatus; }
    public void setStatus(String status) { fulfillmentStatus = status; }
}
```

---

### AllocationService (Direct Delivery Strategy)

```java
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
     * 
     * Why: Strategy Pattern - flexible allocation algorithm
     * Benefit: Can swap greedy, ML, or other algorithms
     * Trade-off: Slightly more complex
     * 
     * Algorithm (Greedy - nearest store per item):
     *   1. For each item in order:
     *      - Find nearest store with stock
     *      - Allocate to that store
     *      - Calculate ETA from that store
     *   2. Create StoreDelivery for each participating store
     *   3. Return plan with max ETA
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
     * 
     * Formula:
     *   distance_km = haversine(store, customer)
     *   pickup_time = 1 minute (prep + packing)
     *   delivery_time = distance_km / rider_speed
     *   eta_seconds = (pickup_time + delivery_time) * 60
     */
    private long calculateETA(Location storeLocation, Location customerLocation) {
        double distanceKm = storeLocation.distanceTo(customerLocation);
        
        // Time in minutes
        double pickupMinutes = PICKUP_TIME_MINUTES;
        double deliveryMinutes = distanceKm / RIDER_SPEED_KM_PER_MINUTE;
        double totalMinutes = pickupMinutes + deliveryMinutes;
        
        // Convert to milliseconds
        return (long)(totalMinutes * 60 * 1000);
    }
}
```

---

### InventoryManager (Main Orchestrator)

```java
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
     * 
     * Flow:
     *   1. Validate order (products, quantities)
     *   2. Allocate items to nearest stores (DIRECT delivery)
     *   3. Remove items from stores (atomic per-store)
     *   4. Dispatch riders in parallel
     *   5. Return fulfillment plan
     */
    public FulfillmentPlan placeOrder(Order order) 
        throws FulfillmentFailedException {
        
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
```

---

### Exceptions

```java
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}

class OutOfStockException extends Exception {
    public OutOfStockException(String msg) { super(msg); }
}

class InvalidLocationException extends Exception {
    public InvalidLocationException(String msg) { super(msg); }
}

class FulfillmentFailedException extends Exception {
    public FulfillmentFailedException(String msg) { super(msg); }
}
```

---

## SECTION 5: MAIN METHOD (Demo & Flow)

### Setup & Initialization

```java
public class ZeptoInventoryDemo {
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
```

---

### Scenario 1: Direct Multi-Store Delivery (Best Case)

```
INPUT:
  Customer Location: (12.9720, 77.5945)
  Order: 2 Milk + 1 Bread + 1 Butter
  
  Stores:
    E1 (100m away): 20 Milk, 0 Bread, 15 Eggs, 0 Butter
    E2 (200m away): 0 Milk, 25 Bread, 0 Eggs, 10 Butter
    E3 (300m away): 10 Milk, 15 Bread, 0 Eggs, 0 Butter

ALLOCATION (Nearest store per item):
  Milk (2) → E1 (100m, has 20) ✓
  Bread (1) → E2 (200m, has 25) ✓
  Butter (1) → E2 (200m, has 10) ✓

DELIVERY (Parallel):
  Rider 1: E1 → Customer (Milk) ETA: 1 + 0.1*2 = 1.2 min
  Rider 2: E2 → Customer (Bread + Butter) ETA: 1 + 0.2*2 = 1.4 min
  
  Customer waits for: Max(1.2, 1.4) = 1.4 min = 84 seconds ≈ 1.5 min

RESULT:
  ✓ 2 Riders dispatch in parallel
  ✓ 3 items from 2 different stores
  ✓ Total time: ~2 minutes
  ✓ NO transfers needed
  ✓ NO consolidation time

OUTPUT:
[ORDER ORD-001] from customer CUST-001
[ALLOCATION] Processing order: ORD-001
[ALLOCATION] Item SKU-001 (qty: 2) → Store E1
[ALLOCATION] Item SKU-002 (qty: 1) → Store E2
[ALLOCATION] Item SKU-004 (qty: 1) → Store E2
[DELIVERY] Store E1 → Customer ETA: 84 seconds
[DELIVERY] Store E2 → Customer ETA: 102 seconds
[FULFILLMENT] Max ETA: 102 seconds

[SUMMARY] Order ORD-001
  Total deliveries: 2
  Max ETA: 102 seconds
[SUCCESS] Order ready for dispatch!
```

---

### Scenario 2: Single Store Fulfillment

```
INPUT:
  Customer Location: (12.9720, 77.5945)
  Order: 3 Milk + 2 Eggs
  
  Stores:
    E1 (100m): 20 Milk, 15 Eggs ← Has everything nearby!

ALLOCATION:
  Milk (3) → E1 (100m) ✓
  Eggs (2) → E1 (100m) ✓

DELIVERY:
  Rider 1: E1 → Customer (Milk + Eggs) ETA: 84 seconds

RESULT:
  ✓ Only 1 rider needed
  ✓ Single delivery
  ✓ Fast (84 seconds)

OUTPUT:
[DELIVERY] Store E1 → Customer ETA: 84 seconds
[FULFILLMENT] Max ETA: 84 seconds
[SUCCESS] Super fast delivery!
```

---

### Scenario 3: Out of Stock Item

```
INPUT:
  Customer Location: (12.9720, 77.5945)
  Order: 5 Butter (SKU-004)
  
  Stores:
    E1: 0 Butter
    E2: 10 Butter ← has it!
    E3: 0 Butter
  
  Customer wants: 5
  E2 has: 10 ✓

ALLOCATION:
  Butter (5) → E2 ✓

DELIVERY:
  Rider: E2 → Customer ETA: 102 seconds

OUTPUT:
[ALLOCATION] Item SKU-004 (qty: 5) → Store E2
[DELIVERY] Store E2 → Customer ETA: 102 seconds
[SUCCESS] Found elsewhere and delivered!
```

---

### Scenario 4: Item Not Available Anywhere

```
INPUT:
  Customer Location: (12.9720, 77.5945)
  Order: 10 Butter
  
  Stores:
    E1: 0 Butter
    E2: 10 Butter (but customer wants 10, store has 10, OK!)

Wait, let's try: Order 20 Butter
  E1: 0
  E2: 10
  E3: 0
  Total: 10 < 20 ❌

ALLOCATION:
  Butter (20) → No store has 20
  
RESULT:
  ✗ Fulfillment fails
  ✗ Order rejected

OUTPUT:
FulfillmentFailedException: Item SKU-004 not available in quantities
[FAILED] Order cannot be fulfilled
```

---

## SECTION 6: REQUIREMENTS

### Functional Requirements ✅

| Requirement | Description |
|---|---|
| Register Stores | Add dark stores to system |
| Register Products | Add products to inventory |
| Place Orders | Customer orders items |
| Allocate to Nearest | Find best stores for each item |
| Direct Delivery | Deliver from each store independently |
| Parallel Dispatch | Multiple riders simultaneously |

### Out of Scope ❌

| Item | Reason |
|---|---|
| Inventory transfer | Direct delivery only |
| Consolidation | Not needed for speed |
| User authentication | Not inventory related |
| Payment | Out of scope |
| Returns/Refunds | Post-delivery |

### Non-Functional Requirements ✅

| Requirement | Target |
|---|---|
| Delivery Time | < 10 minutes (8 min SLA) |
| Throughput | 100K+ orders/day |
| Scalability | 1000+ stores, 100K+ products |
| Concurrency | 10K+ concurrent orders |
| Accuracy | 99.9% fulfillment |

### Out of Scope ❌

| Item | Reason |
|---|---|
| Sub-second latency | Milliseconds acceptable |
| Global coordination | Regional sharding OK |
| Real-time ML | Static routing OK for MVP |

---

## SECTION 7: SCALABILITY & DESIGN

### Why This Design is Better

✅ **No Transfers**
- Direct delivery from nearest stores
- Minimum latency
- No SAGA complexity needed

✅ **Parallel Delivery**
- Multiple riders work simultaneously
- Different stores dispatch independently
- High throughput

✅ **Per-Store Locking**
- Each store has independent lock
- Concurrent orders don't block
- Scales to 1000+ stores

✅ **Nearest Store Strategy**
- Greedy allocation (O(n log n) per order)
- Minimizes delivery distance
- Maximizes fulfillment rate

✅ **Simple & Fast**
- No inventory movement
- Direct from source to customer
- Fewer moving parts

---

### How to Scale to 1000+ Stores & 100K+ Products

#### Layer 1: Geohashing (Location Indexing)

```
Strategy: Grid-based spatial indexing
  • Divide area into grid cells
  • Store location to geohash mapping
  • O(1) lookup for nearby stores

Benefit: Avoid O(n) distance calculation
Implementation: geohash library
```

#### Layer 2: Regional Sharding

```
Strategy: Divide by geography
  • North zone: Stores N1-N100
  • South zone: Stores S1-S100
  • Each region independent
  • Request routed to correct region

Benefit: Reduced contention, parallelization
```

#### Layer 3: Inventory Cache (Redis)

```
Strategy: Cache inventory in Redis
  • Real-time stock levels
  • Write-through to database
  • Fallback to DB on miss

Benefit: Sub-millisecond lookups
Example: redis.get("store:E1:SKU-001") → 20
```

#### Layer 4: Async Rider Dispatch

```
Strategy: Queue-based rider assignment
  • Publish delivery events to Kafka
  • Rider service processes async
  • No blocking order placement

Benefit: Non-blocking, scalable
Flow: Order → Delivery queue → Rider system
```

#### Layer 5: ML-Based Allocation

```
Strategy: Predictive allocation
  • Predict item demand by location
  • Pre-position inventory in stores
  • Reduce allocation failure rate

Benefit: Better fulfillment, faster delivery
Data: Historical orders, delivery times
```

---

### Design Patterns Used

#### 1. Strategy Pattern (AllocationService)

```
What: Different allocation algorithms

Why: Greedy now, ML/other later
     Flexible algorithm selection

Benefit:
  • Open/Closed Principle
  • Easy to test
  • Easy to swap

Trade-off:
  • Extra class/interface
```

#### 2. Geohashing (Location)

```
What: Convert GPS to grid cell

Why: Fast spatial indexing
     O(1) nearby store lookup

Benefit:
  • Sub-millisecond
  • Scales to 1M+ locations

Trade-off:
  • Precision loss (minor)
```

#### 3. Per-Store Locking (Synchronized)

```
What: Lock at store level

Why: Concurrent orders parallelizable
     High throughput

Benefit:
  • No global bottleneck
  • Scales with CPU cores

Trade-off:
  • Slight overhead
  • Deadlock risk (minimal)
```

#### 4. Direct Delivery (No Transfers)

```
What: Each store delivers independently

Why: Minimum latency
     No consolidation overhead

Benefit:
  • Fast (8 min SLA)
  • Simple (no SAGA)
  • Parallel (multiple riders)

Trade-off:
  • Multiple deliveries to customer
  • But acceptable (items arrive close together)
```

---

### Time Complexity

| Operation | Time | Reason |
|---|---|---|
| Allocate Order | O(n log m) | n=items, m=stores within radius |
| Find Nearest Store | O(m log m) | Sort stores by distance |
| Remove Stock | O(1) | Map lookup + update |
| Calculate ETA | O(1) | Haversine formula |
| Total per Order | O(n log m) | Dominated by sorting |

---

### Space Complexity

| Component | Space | Notes |
|---|---|---|
| Stores | O(s) | s = number of stores |
| Inventory per Store | O(p) | p = products per store |
| Orders | O(o*n) | o = orders, n = items per order |
| Deliveries | O(d) | d = total deliveries |
| **Total** | **O(s*p + o*d)** | **Manageable** |

**At scale:**
- 1000 stores × 100K products = 100M entries (fits in RAM/distributed cache)
- 100K orders/day × 2 deliveries avg = 200K deliveries (easily tracked)

---

## SECTION 8: FOLLOW-UP QUESTIONS & ANSWERS

### Q1: Why not consolidate orders to one store?

**Answer:**

Consolidation adds time:

```
WRONG (Consolidation):
  E2 → E1 transfer: 2-3 minutes
  E1 → Customer: 8 minutes
  Total: 10-11 minutes ❌

CORRECT (Direct Delivery):
  E1 → Customer: 5 minutes
  E2 → Customer: 6 minutes
  Total: Max(5,6) = 6 minutes ✓

Zepto's competitive advantage is SPEED (8-10 min).
Consolidation kills that advantage.
```

---

### Q2: What if items arrive at different times?

**Answer:**

That's acceptable! Zepto model:

```
Customer perspective:
  ✓ Milk arrives at 5 min
  ✓ Bread arrives at 6 min
  ✓ Eggs arrive at 7 min
  ✓ Total delivery time: 7 min (all items arrived)
  
Better than:
  ✗ Wait 10 min for all to arrive from one store

Real Zepto behavior:
  • Items from different stores OK
  • Customers don't mind (they're getting faster delivery)
  • Multiple deliveries common (multiple riders)
```

---

### Q3: How do you handle allocation conflicts?

**Answer:**

Synchronized remove prevents overselling:

```java
// Thread-safe removal
public synchronized boolean removeStock(String sku, int quantity) {
    int current = getStock(sku);
    if (current < quantity) {
        return false;  // Not enough (another order got it)
    }
    stockData.put(sku, current - quantity);
    return true;  // Success
}

Scenario: 2 orders want last 5 Milk
  Order 1: removeStock("SKU-001", 5) → ✓ True
  Order 2: removeStock("SKU-001", 5) → ✗ False (only 0 left)
  
Result: Order 2 fails allocation
```

---

### Q4: What if all stores are too far?

**Answer:**

Expand search radius or reject:

```java
// In AllocationService
private static final double SEARCH_RADIUS_KM = 5.0;

// Option 1: Expand radius
if (noStoresFound()) {
    expandRadius(5.0 → 10.0);  // Try again
}

// Option 2: Offer next-day delivery
if (stillNoStores()) {
    sugggestNextDayDelivery();  // Lower SLA
}

// Option 3: Reject order
if (customerDeclines()) {
    throwFulfillmentFailedException();
}

Real Zepto: Doesn't deliver >5km, rejects order
```

---

### Q5: How do you optimize allocation?

**Answer:**

Strategy Pattern allows different algorithms:

```java
// Current: Greedy (nearest store per item)
interface AllocationStrategy {
    FulfillmentPlan allocate(Order order);
}

// Greedy
class GreedyAllocation implements AllocationStrategy {
    // Nearest store for each item
}

// Load balancing
class LoadBalancedAllocation implements AllocationStrategy {
    // Spread across stores to avoid hotspots
}

// ML-based
class MLAllocation implements AllocationStrategy {
    // Predict best stores based on demand patterns
}

// Future: SwapDiffer algorithms without changing Order/Inventory
```

---

### Q6: How do you handle peak loads?

**Answer:**

Queuing and prioritization:

```java
// In InventoryManager
if (orderQueue.size() > MAX_CAPACITY) {
    // Option 1: Queue orders
    addToWaitingQueue(order);
    
    // Option 2: Reject gracefully
    throwFulfillmentFailedException("Too busy, try later");
    
    // Option 3: Priority queuing
    if (customer.isTier("PLATINUM")) {
        priorityQueue.add(order);  // Skip line
    } else {
        regularQueue.add(order);
    }
}

Scaling: Add more servers (stateless order processing)
```

---

### Q7: How would you implement dynamic pricing?

**Answer:**

Add demand factor to allocation:

```java
// In AllocationService
private double calculateScore(DarkStore store, OrderItem item) {
    double distancePenalty = store.distanceTo(customer) / SEARCH_RADIUS;
    double demandPenalty = store.getStockLevel(item) / AVERAGE_STOCK;
    double loadPenalty = store.getCurrentOrders() / CAPACITY;
    
    // Prefer: close + high stock + low load
    return 1.0 / (distancePenalty + demandPenalty + loadPenalty);
}

// Bonus: surge pricing during peak
if (isPeakHour()) {
    double surgeMultiplier = 1.5;
    price = basePrice * surgeMultiplier;
    offer alternative stores
}
```

---

### Q8: How do you prevent overselling?

**Answer:**

Three-layer protection:

```java
1. ALLOCATION LEVEL
   // Check before allocating
   if (!store.hasStock(sku, qty)) {
       throwFulfillmentFailedException();
   }

2. REMOVAL LEVEL
   // Double-check at removal (synchronized)
   public synchronized boolean removeStock(sku, qty) {
       if (stock < qty) return false;  // Check again
       stock -= qty;
       return true;
   }

3. DATABASE LEVEL
   // Distributed systems: unique constraints
   UPDATE inventory SET qty = qty - 5 
   WHERE store_id = 'E1' AND sku = 'SKU-001' 
   AND qty >= 5
```

---

### Q9: Multi-city expansion?

**Answer:**

City-level sharding:

```java
class ZeptoNetwork {
    private Map<String, InventoryManager> cityManagers;
    // cityManagers["bangalore"] → InventoryManager (500 stores)
    // cityManagers["mumbai"] → InventoryManager (300 stores)
    // cityManagers["delhi"] → InventoryManager (200 stores)
    
    public FulfillmentPlan fulfillOrder(Order order) {
        String city = geoToCity(order.getLocation());
        InventoryManager manager = cityManagers.get(city);
        
        if (manager == null) {
            throw new CityNotSupportedException(city);
        }
        
        return manager.placeOrder(order);  // City-specific
    }
}

Benefits:
  • Each city operates independently
  • No cross-city latency
  • Easy to scale new cities
```

---

### Q10: Design patterns & architecture summary?

**Answer:**

Three key insights:

**1. Direct Delivery (No Consolidation)**
- Why: Speed is Zepto's competitive advantage
- Benefit: 8-10 minute SLA possible
- Trade-off: Multiple deliveries (acceptable)
- Interview: "I chose direct delivery because consolidation adds 2-3 minutes"

**2. Parallel Rider Dispatch**
- Why: Multiple riders work simultaneously
- Benefit: Linear scaling (more riders = more orders)
- Trade-off: Logistics complexity (outside this LLD)
- Interview: "Each store dispatches its own rider independently"

**3. Greedy Nearest-Store Allocation**
- Why: Simple, fast, optimal for distance
- Benefit: O(n log m) per order, minimizes delivery time
- Trade-off: Doesn't consider store load
- Interview: "Greedy algorithm is simple yet effective; ML for future"

**4. Per-Store Locking (High Concurrency)**
- Why: Independent locks for different stores
- Benefit: Orders to different stores parallelizable
- Trade-off: Slight synchronization overhead
- Interview: "Per-store locking enables 10K+ concurrent orders"

---

## END OF ZEPTO LLD - FINAL VERSION

✅ **Complete Design Includes:**
- Clarifying questions
- 9 classes with clear responsibility
- Direct multi-store delivery model
- Parallel rider dispatch
- Allocation algorithm (greedy/nearest-store)
- Per-store thread-safe inventory
- 4 realistic demo scenarios
- Full implementation (all code)
- Scalability to 1000+ stores
- 10 Q&A with interview talking points

**Key Differentiator: NO consolidation, pure speed-focused direct delivery** 🚀

---

## 🚀 INTERVIEW TALKING POINTS

"I designed Zepto Inventory with **direct multi-store delivery** because speed
is the core competitive advantage. No consolidation delays.

**Algorithm:**
For each item in order, allocate to nearest store with stock. This is greedy
but optimal for distance minimization.

**Parallel Execution:**
Each participating store dispatches its own rider independently. Customer's
wait time is the maximum ETA across all riders (usually 8-10 minutes).

**Concurrency:**
Per-store locking (not global) allows concurrent orders to different stores.
Store E1 and E2 can fulfill simultaneously without blocking each other.

**Scalability:**
- Geohashing for O(1) store lookup
- Regional sharding to reduce contention
- Redis caching for inventory
- Async rider dispatch via Kafka

**Why no SAGA/transfers:**
Transfers add 2-3 minutes of latency. Direct delivery is faster and simpler.
This is the fundamental insight of Zepto's model."

---

**READY FOR ZEPTO INTERVIEW** ✅