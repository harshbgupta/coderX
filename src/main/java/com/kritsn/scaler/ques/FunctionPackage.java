package com.kritsn.scaler.ques;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 13, 2025
 */

public class FunctionPackage {

    public static void main(String[] args) {
        Function<String, Integer> function = Integer::parseInt;
        System.out.println("converted to integer: " + function.apply("123"));

        Predicate<Integer> predicate = num -> num % 2 == 0;
        System.out.printf("Is Even number: %b%n", predicate.test(123)); //-> use of printf

        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println(randomSupplier.get()); // e.g., 0.8342

        Consumer<String> consumer = name -> System.out.printf("Hello %s", name);
        consumer.accept( "Kritsn");
    }
}
