package com.kritsn.lld.designPattern;
import java.util.HashMap;
import java.util.Map;

// Step 2: Flyweight — holds only INTRINSIC (shared) state, immutable
class AttributeDefinition {
    private final String name;      // e.g., "Color"
    private final String dataType;  // e.g., "STRING"

    AttributeDefinition(String name, String dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    // extrinsic state ("value") is passed in, NOT stored in this shared object
    void printAttribute(String value) {
        System.out.println(name + " (" + dataType + "): " + value);
    }
}

// Step 3: Factory — returns shared instances instead of creating duplicates
class AttributeDefinitionFactory {
    private static final Map<String, AttributeDefinition> cache = new HashMap<>();

    static AttributeDefinition get(String name, String dataType) {
        String key = name + ":" + dataType;
        return cache.computeIfAbsent(key, k -> {
            System.out.println("Creating NEW AttributeDefinition for: " + key);
            return new AttributeDefinition(name, dataType);
        });
    }
}

// Step 4: extrinsic state lives here, per-product, referencing the shared flyweight
class ProductAttributeValue {
    private final AttributeDefinition definition; // shared reference, not a copy
    private final String value;                    // unique per product

    ProductAttributeValue(AttributeDefinition definition, String value) {
        this.definition = definition;
        this.value = value;
    }

    void print() { definition.printAttribute(value); }
}

public class _11FlyweightDemo {
    public static void main(String[] args) {
        // 1 million products could all share this SAME AttributeDefinition object
        AttributeDefinition colorDef = AttributeDefinitionFactory.get("Color", "STRING");

        ProductAttributeValue p1Color = new ProductAttributeValue(colorDef, "Black");
        ProductAttributeValue p2Color = new ProductAttributeValue(
                AttributeDefinitionFactory.get("Color", "STRING"), "Red"); // same definition, reused from cache

        p1Color.print();
        p2Color.print();

        System.out.println("Same definition instance shared? " +
                (colorDef == AttributeDefinitionFactory.get("Color", "STRING"))); // true — no new object created
    }
}