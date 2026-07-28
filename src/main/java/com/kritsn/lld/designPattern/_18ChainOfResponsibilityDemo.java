package com.kritsn.lld.designPattern;

// Step 1: abstract Handler — holds the "next" reference and a template for the loop
abstract class OrderHandler {
    protected OrderHandler next;

    OrderHandler setNext(OrderHandler next) {
        this.next = next;
        return next; // returned so calls can be chained: h1.setNext(h2).setNext(h3)
    }

    abstract void handle(Order order);
}

// Step 2: concrete handlers — each does ONE check, then passes forward
class StockCheckHandler extends OrderHandler {
    void handle(Order order) {
        System.out.println("Checking stock for " + order.skuId + "...");
        if (order.qty > 100) {
            System.out.println("  -> REJECTED: insufficient stock");
            return; // short-circuit — chain stops here
        }
        if (next != null) next.handle(order); // pass forward
    }
}
class FraudCheckHandler extends OrderHandler {
    void handle(Order order) {
        System.out.println("Checking fraud signals for " + order.userId + "...");
        if (next != null) next.handle(order);
    }
}
class PaymentAuthHandler extends OrderHandler {
    void handle(Order order) {
        System.out.println("Authorizing payment of ₹" + order.amount + "...");
        System.out.println("  -> Order approved!");
        // last in chain — nothing to forward to
    }
}

class Order {
    String skuId, userId;
    int qty;
    double amount;
    Order(String skuId, String userId, int qty, double amount) {
        this.skuId = skuId; this.userId = userId; this.qty = qty; this.amount = amount;
    }
}

public class _18ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Step 3: wire the chain together — this is the ONLY place that knows the full sequence
        OrderHandler stockCheck = new StockCheckHandler();
        stockCheck.setNext(new FraudCheckHandler()).setNext(new PaymentAuthHandler());

        // Step 4: client only ever calls the FIRST handler
        Order order = new Order("SKU1", "user1", 2, 999.0);
        stockCheck.handle(order);
    }
}