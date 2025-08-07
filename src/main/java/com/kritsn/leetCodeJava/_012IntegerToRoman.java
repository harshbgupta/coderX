package com.kritsn.leetCodeJava;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

public class _012IntegerToRoman {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Subtraction Strategy:
    // Start from the highest Roman value and subtract while possible,
    // appending the corresponding symbol to the result.
    //
    // 🪜 Steps:
    // 1. Define a list of Roman symbols and values from largest to smallest.
    // 2. Loop through the values:
    //    - While num >= value, subtract and add the symbol to result.
    // 3. Return the final result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(1) — Number of operations is constant for max 3999.
    // Space Complexity: O(1) — Output size is bounded.
    ///////////////////////////////////////////////////////////////////////////
    private String integerToRoman(int num) {
        Map<Integer, String> values = new LinkedHashMap<>();
        values.put(1000, "M");values.put(900, "CM");
        values.put(500, "D");values.put(400, "CD");
        values.put(100, "C");values.put(90, "XC");
        values.put(50, "L");values.put(40, "XL");
        values.put(10, "X");values.put(9, "IX");
        values.put(5, "V");values.put(4, "IV");
        values.put(1, "I");

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();
            while (num >= value) {
                result.append(symbol);
                num -= value;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        _012IntegerToRoman solution = new _012IntegerToRoman();

        System.out.println(solution.integerToRoman(3));
        System.out.println(solution.integerToRoman(58));
        System.out.println(solution.integerToRoman(1994));
        System.out.println(solution.integerToRoman(3999));
        System.out.println(solution.integerToRoman(4000));
    }
}
