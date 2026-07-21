package com.kritsn.leetCodeJava.hard;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _273IntegerToEnglishWords {

    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
            "Seventeen", "Eighteen", "Nineteen"};

    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    String numberToWords(int num) {
        // Edge case: exact zero
        if (num == 0) return "Zero";

        long number = num;          // mutable copy for chunk processing
        int i = 0;                  // index into 'thousands'
        String words = "";          // progressively composed sentence

        // Process last 3 digits repeatedly
        while (number > 0) {
            long chunk = number % 1000; // current 3-digit group
            if (chunk != 0) {
                String part = helper((int) chunk); // e.g., "One Hundred Twenty Three "
                // Prepend scale word and previously built sentence with proper spacing
                words = part + thousands[i] + (words.isEmpty() ? "" : " " + words);
            }
            number /= 1000; // drop processed 3 digits
            i++;             // move to next scale
        }

        return words.trim(); // tidy any trailing spaces
    }

    // Helper to convert numbers < 1000
    private String helper(int num) {
        if (num == 0) return "";
        if (num < 20) return belowTwenty[num] + " ";
        if (num < 100) return tens[num / 10] + " " + helper(num % 10);
        return belowTwenty[num / 100] + " Hundred " + helper(num % 100);
    }

    public static void main(String[] args) {
        _273IntegerToEnglishWords converter = new _273IntegerToEnglishWords();

        // ---- Test Case 1 ----
        int num1 = 123;
        System.out.println("Input: " + num1);
        System.out.println("Output: " + converter.numberToWords(num1));
        System.out.println("Expected: One Hundred Twenty Three\n");

        // ---- Test Case 2 ----
        int num2 = 12345;
        System.out.println("Input: " + num2);
        System.out.println("Output: " + converter.numberToWords(num2));
        System.out.println("Expected: Twelve Thousand Three Hundred Forty Five\n");

        // ---- Test Case 3 ----
        int num3 = 1234567;
        System.out.println("Input: " + num3);
        System.out.println("Output: " + converter.numberToWords(num3));
        System.out.println("Expected: One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven\n");

        // ---- Test Case 4 ----
        int num4 = 0;
        System.out.println("Input: " + num4);
        System.out.println("Output: " + converter.numberToWords(num4));
        System.out.println("Expected: Zero\n");

        // ---- Additional Edge / Large ----
        int num5 = Integer.MAX_VALUE; // 2147483647
        System.out.println("Input: " + num5);
        System.out.println("Output: " + converter.numberToWords(num5));
        System.out.println("Expected: Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven\n");
    }
}
