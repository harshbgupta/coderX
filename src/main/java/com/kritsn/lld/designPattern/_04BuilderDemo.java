package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */

public class _04BuilderDemo {
    public static void main(String[] args) {
        ProductSku basic = new ProductSku.Builder("X123").build();
        System.out.println(basic);

        // fully customized — sku still mandatory, rest chained optionally
        ProductSku full = new ProductSku.Builder("X456")
                .name("Running Shoe")
                .price(2999.0)
                .color("Black")
                .size("UK9")
                .build();
        System.out.println(full);

        // below one won't compile / won't run — no way to skip sku
//         Product invalid = new Product.Builder().build(); // <-- no no-arg constructor exists
    }
}

class ProductSku {
    private final String sku;       // mandatory
    private final String name;      // optional
    private final double price;     // optional
    private final String color;     // optional
    private final String size;      // optional

    private ProductSku(Builder b) {
        this.sku = b.sku;
        this.name = b.name;
        this.price = b.price;
        this.color = b.color;
        this.size = b.size;
    }

    public static class Builder {
        // mandatory field passed into constructor — enforced at compile time
        private final String sku;

        // optional fields default to sensible values
        private String name = "Unnamed Product";
        private double price = 0.0;
        private String color;
        private String size;

        // constructor takes ONLY the mandatory field
        public Builder(String sku) {
            if (sku == null || sku.isBlank()) {
                throw new IllegalArgumentException("sku is mandatory");
            }
            this.sku = sku;
        }

        public Builder name(String name) { this.name = name; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder color(String color) { this.color = color; return this; }
        public Builder size(String size) { this.size = size; return this; }

        public ProductSku build() {
            return new ProductSku(this);
        }
    }

    @Override
    public String toString() {
        return "Product{sku='" + sku + "', name='" + name + "', price=" + price
                + ", color='" + color + "', size='" + size + "'}";
    }
}