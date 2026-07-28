package com.kritsn.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

// ===== CartMemento.java (same package as ShoppingCart) =====
// package-private class — no "public" modifier, so nothing outside this package can even see this class exists
class CartMemento {
    final List<String> items;       // package-private field — no "private"
    final double totalPrice;
    final String appliedCoupon;

    // package-private constructor — only classes in the SAME package can call `new CartMemento(...)`
    CartMemento(List<String> items, double totalPrice, String appliedCoupon) {
        this.items = new ArrayList<>(items); // still a defensive copy — same reasoning as before
        this.totalPrice = totalPrice;
        this.appliedCoupon = appliedCoupon;
    }
}

// ===== ShoppingCart.java (same package as CartMemento) =====
class ShoppingCart {
    private final List<String> items = new ArrayList<>();
    private double totalPrice = 0.0;
    private String appliedCoupon = null;

    void addItem(String item, double price) {
        items.add(item);
        totalPrice += price;
    }

    void applyCouponCode(String couponCode) {
        System.out.println("Applying coupon: " + couponCode);
        appliedCoupon = couponCode;
        totalPrice = totalPrice * 0.7;
    }

    void printState() {
        System.out.println("Items: " + items + " | Total: ₹" + totalPrice + " | Coupon: " + appliedCoupon);
    }

    // Originator creates a Memento — legal, same package as CartMemento
    CartMemento save() {
        return new CartMemento(this.items, this.totalPrice, this.appliedCoupon);
    }

    // Originator reads a Memento's fields directly — legal, package-private access, same package
    void restore(CartMemento memento) {
        this.items.clear();
        this.items.addAll(memento.items);
        this.totalPrice = memento.totalPrice;
        this.appliedCoupon = memento.appliedCoupon;
    }
}

// ===== CheckoutSession.java (same package too, in this example) =====
class CheckoutSession {
    private CartMemento checkpoint;

    void createCheckpoint(ShoppingCart cart) {
        checkpoint = cart.save(); // CheckoutSession can HOLD a CartMemento...
        System.out.println("Checkpoint created.");
    }

    void rollbackTo(ShoppingCart cart) {
        if (checkpoint == null) {
            System.out.println("No checkpoint to roll back to!");
            return;
        }
        cart.restore(checkpoint); // ...and hand it back, but never reads checkpoint.items/totalPrice itself
        System.out.println("Rolled back to checkpoint.");
    }
}

public class _21MementoPatternDemo2 {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        CheckoutSession session = new CheckoutSession();

        cart.addItem("Shoe", 2999.0);
        cart.addItem("Jacket", 4999.0);
        cart.printState();

        session.createCheckpoint(cart);

        cart.applyCouponCode("FESTIVE30");
        cart.printState();

        session.rollbackTo(cart);
        cart.printState();
    }
}