package com.kritsn.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */
// Step 1: shared interface for both leaf and composite
interface CatalogComponent {
    double getTotalPrice();
    String getName();
}

// Step 2: leaf — base case, direct value, no recursion
class ProductLeaf implements CatalogComponent {
    private final String name;
    private final double price;

    ProductLeaf(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getTotalPrice() { return price; }
    public String getName() { return name; }
}

// Step 3: composite — holds children of the SAME interface type, recurses
class Category implements CatalogComponent {
    private final String name;
    private final List<CatalogComponent> children = new ArrayList<>();

    Category(String name) { this.name = name; }

    void add(CatalogComponent child) { children.add(child); }

    public double getTotalPrice() {
        return children.stream()
                .mapToDouble(CatalogComponent::getTotalPrice) // recursion happens here
                .sum();
    }
    public String getName() { return name; }
}

public class _10CompositeDemo {
    public static void main(String[] args) {
        // Step 4: build a tree — caller never distinguishes leaf vs composite
        Category electronics = new Category("Electronics");
        Category mobiles = new Category("Mobiles");

        mobiles.add(new ProductLeaf("Android Phone", 15000));
        mobiles.add(new ProductLeaf("iPhone", 70000));

        electronics.add(mobiles);
        electronics.add(new ProductLeaf("Headphones", 2000));

        // works identically whether called on a leaf or the whole tree
        System.out.println("Total price under Electronics: " + electronics.getTotalPrice());
        System.out.println("Total price under Mobiles only: " + mobiles.getTotalPrice());
    }
}