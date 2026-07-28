package com.kritsn.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype's core definition: create a new object by copying an existing instance's state, rather than
 * building it from raw inputs. The mechanism (clone(), copy constructor, or a builder seeded from an existing object)
 * doesn't matter — what matters is: does the new object's initial state come from reading another object, instead of
 * the caller supplying every field from scratch?
 */



/**
 * PROTOTYPE PATTERN — full revision notes embedded as comments.
 * <p>
 * DEFINITION: Create new objects by copying (cloning) an existing object,
 * instead of building from scratch.
 * <p>
 * WHY NEEDED: When object creation is expensive (heavy DB lookup, complex
 * computation to initialize), cloning an already-built object is cheaper
 * than rebuilding it from zero every time.
 * <p>
 * REAL-WORLD USE CASES:
 * 1. Cloning a Product template when creating product variants (same base
 * attributes, different size/color) instead of re-fetching/re-computing
 * shared fields.
 * 2. Java's Object.clone() / Cloneable interface — direct language support.
 */
class Product implements Cloneable {
    String sku;
    String name;
    List<String> tags; // mutable field — this is where shallow-vs-deep bites

    Product(String sku, String name, List<String> tags) {
        this.sku = sku;
        this.name = name;
        this.tags = tags;
    }

    /**
     * SHALLOW CLONE via Object.clone().
     * <p>
     * super.clone() copies each field's VALUE. For primitives/Strings this
     * is fine (Strings are immutable). But for a mutable field like `tags`
     * (a List), it copies the REFERENCE, not the contents — so the clone
     * and the original end up pointing at the exact same List object.
     * <p>
     * DANGER: mutating clone.tags also mutates original.tags silently.
     */
    public Product shallowClone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            // Cloneable is a MARKER interface (declares zero methods). If a
            // class doesn't implement it, super.clone() throws this checked
            // exception at RUNTIME, not compile time — one of the well-known
            // design flaws of Cloneable (Josh Bloch, Effective Java, has
            // publicly said he regrets this API design).
            throw new RuntimeException(e);
        }
    }

    /**
     * DEEP CLONE — the fix for the shallow-clone problem.
     * <p>
     * Explicitly rebuilds the mutable `tags` field into a NEW List, so the
     * clone and original no longer share the same underlying object.
     * Anything nested deeper (e.g., if tags held mutable objects instead of
     * Strings) would need the same treatment recursively — "deep enough"
     * copying stops at whatever level actually gets mutated independently.
     */
    public Product deepClone() {
        Product copy = shallowClone();
        copy.tags = new ArrayList<>(this.tags); // new list, contents copied
        return copy;
    }

    /**
     * COPY CONSTRUCTOR — the MODERN, PREFERRED alternative to Cloneable.
     * <p>
     * Effective Java's recommended replacement:
     * - Ordinary constructor, no marker interface required
     * - No checked CloneNotSupportedException to handle
     * - Deep-vs-shallow is explicit and visible right here, field by field
     * — no ambiguity about what super.clone() silently did or didn't copy
     * <p>
     * This is what most real production codebases use instead of clone().
     */
    Product(Product other) {
        this.sku = other.sku;
        this.name = other.name;
        this.tags = new ArrayList<>(other.tags); // deep copy done explicitly
    }

    @Override
    public String toString() {
        return "Product{sku='" + sku + "', name='" + name + "', tags=" + tags + "}";
    }
}

public class _05PrototypeDemo {
    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // DEMO 1: Shallow clone problem — mutation leaks into the original
        // ---------------------------------------------------------------
        Product original = new Product("X123", "Shoe", new ArrayList<>(List.of("running", "black")));

        Product shallow = original.shallowClone();
        shallow.tags.add("on-sale"); // mutates the SAME underlying list

        System.out.println("Original after shallow clone mutation: " + original); // "on-sale" leaked in!
        System.out.println("Shallow copy: " + shallow);

        System.out.println("---");

        // ---------------------------------------------------------------
        // DEMO 2: Deep clone fix — mutation stays isolated
        // ---------------------------------------------------------------
        Product original2 = new Product("X456", "Jacket", new ArrayList<>(List.of("winter")));
        Product deep = original2.deepClone();
        deep.tags.add("waterproof"); // safe — separate list object

        System.out.println("Original after deep clone mutation: " + original2); // unaffected
        System.out.println("Deep copy: " + deep);

        System.out.println("---");

        // ---------------------------------------------------------------
        // DEMO 3: Copy constructor — the preferred modern approach
        // ---------------------------------------------------------------
        Product baseTemplate = new Product("TEMPLATE", "Base Sneaker", new ArrayList<>(List.of("shoe")));

        // Creating SKU size-variants without re-fetching/re-computing shared
        // attributes — mirrors real catalog/SKU variant generation.
        Product variantUK8 = new Product(baseTemplate);
        variantUK8.sku = "TEMPLATE-UK8";
        variantUK8.tags.add("UK8"); // safe — copy constructor already deep-copied tags

        System.out.println("Base template (unaffected): " + baseTemplate);
        System.out.println("UK8 variant: " + variantUK8);
    }
}