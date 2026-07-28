package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */

// Step 1: common interface — both base and decorators implement this
interface PriceCalculator {
    double getPrice();
}

// Step 2: base/core implementation
class BasePrice implements PriceCalculator {
    private final double price;
    BasePrice(double price) { this.price = price; }
    public double getPrice() { return price; }
}

// Step 3: abstract decorator — IS-A PriceCalculator, HAS-A PriceCalculator
abstract class PriceDecorator implements PriceCalculator {
    protected final PriceCalculator wrapped;
    PriceDecorator(PriceCalculator wrapped) { this.wrapped = wrapped; }
}

// Step 4: concrete decorators — each adds its own behavior, then delegates
class TaxDecorator extends PriceDecorator {
    TaxDecorator(PriceCalculator wrapped) { super(wrapped); }
    public double getPrice() { return wrapped.getPrice() * 1.18; } // adds 18% tax
}
class DiscountDecorator extends PriceDecorator {
    DiscountDecorator(PriceCalculator wrapped) { super(wrapped); }
    public double getPrice() { return wrapped.getPrice() * 0.9; } // 10% off
}

public class _07DecoratorDemo {
    public static void main(String[] args) {
        // Step 5: stack decorators — order matters!
        PriceCalculator finalPrice = new DiscountDecorator(new TaxDecorator(new BasePrice(1000)));
        System.out.println("Price: " + finalPrice.getPrice()); // tax first, then discount on top

        PriceCalculator otherOrder = new TaxDecorator(new DiscountDecorator(new BasePrice(1000)));
        System.out.println("Price (different order): " + otherOrder.getPrice()); // discount first, then tax
    }
}
