package com.kritsn.ivs.pricesily;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 04, 2025
 */

public class Main {
    public static void main(String[] args) {
        // Case 1: Base reference, Base object
        A base1 = new A();
        base1.m1A(); // ✅ prints "m1A"
        // base1.m1B(); // ❌ compile error, method not visible

        // Case 2: Child reference, Child object
        B child1 = new B();
        child1.m1A(); // ✅ prints "m1A" (inherited)
        child1.m1B(); // ✅ prints "m1B"

        // Case 3: Base reference, Child object (Upcasting)
        A base2 = new B();
        base2.m1A(); // ✅ prints "m1A"
        // base2.m1B(); // ❌ compile error, only methods of A visible

        // Case 4: Child reference, Base object
        // B child2 = new A(); // ❌ compile-time error

        // Case 5: Explicit downcasting
        try {
            B child3 = (B) new A(); // Compiles, but unsafe
            child3.m1A();
            child3.m1B();
        } catch (ClassCastException e) {
            System.out.println("Runtime error: " + e);
        }

        // Safe downcasting
        A safe = new B();    // upcast
        B childSafe = (B) safe; // downcast (valid since actual object is B)
        childSafe.m1A(); // ✅ "m1A"
        childSafe.m1B(); // ✅ "m1B"
    }

}

class A {
    void m1A() {
        System.out.println("m1A");
    }
}

class B extends A {
    void m1B() {
        System.out.println("m1B");
    }
}