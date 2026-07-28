package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */
// Step 1: the algorithm interface
interface DiscountStrategy {
    double apply(double price);
}

// Step 2: concrete strategies — each variant is its own class
class FlatDiscount implements DiscountStrategy {
    public double apply(double price) { return price - 100; }
}
class SeasonalDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.8; }
}
class NoDiscount implements DiscountStrategy {
    public double apply(double price) { return price; }
}

// Step 3: context — holds the interface, not any concrete class
class PricingContext {
    private DiscountStrategy strategy;

    PricingContext(DiscountStrategy strategy) { this.strategy = strategy; }

    // Step 4: swap the strategy at runtime
    void setStrategy(DiscountStrategy strategy) { this.strategy = strategy; }

    double getFinalPrice(double price) { return strategy.apply(price); }
}

public class _13StrategyDemo {
    public static void main(String[] args) {
        PricingContext context = new PricingContext(new SeasonalDiscount());
        System.out.println("Seasonal price: " + context.getFinalPrice(1000));

        context.setStrategy(new FlatDiscount()); // swap strategy at runtime
        System.out.println("Flat discount price: " + context.getFinalPrice(1000));
    }
}