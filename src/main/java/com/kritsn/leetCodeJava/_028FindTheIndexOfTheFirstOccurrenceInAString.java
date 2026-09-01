package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 */
public class _028FindTheIndexOfTheFirstOccurrenceInAString {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window Comparison:
    //
    // We check every substring of haystack of length needle.length
    // and compare it with the needle.
    //
    // 🪜 Steps:
    // 1. If needle is empty → return 0.
    // 2. Iterate from i = 0 to haystack.length - needle.length
    // 3. At each step, extract a substring of haystack of length = needle.length.
    // 4. Compare it to needle. If match, return index.
    // 5. If no match found, return -1.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O((n - m + 1) * m), where n = haystack.length, m = needle.length.
    // Space Complexity: O(1) — no extra space except for constant-sized vars.
    ///////////////////////////////////////////////////////////////////////////
    int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int n = haystack.length();
        int m = needle.length();

        // Only iterate till the point where a full needle can fit
        for (int i = 0; i <= n - m; i++) {
            // Extract substring of length m
            String substring = haystack.substring(i, i + m);

            // If it matches needle, return the current index
            if (substring.equals(needle)) return i;
        }

        // If needle is not found
        return -1;
    }

    public static void main(String[] args) {
        _028FindTheIndexOfTheFirstOccurrenceInAString solver = new _028FindTheIndexOfTheFirstOccurrenceInAString();

        // Test Case 1: Needle found at the beginning
        String haystack1 = "sadbutsad";
        String needle1 = "sad";
        System.out.println("Test Case 1: haystack = \"" + haystack1 + "\", needle = \"" + needle1 + "\" -> Result = " + solver.strStr(haystack1, needle1));
        // Expected: 0

        // Test Case 2: Needle not found
        String haystack2 = "leetcode";
        String needle2 = "leeto";
        System.out.println("Test Case 2: haystack = \"" + haystack2 + "\", needle = \"" + needle2 + "\" -> Result = " + solver.strStr(haystack2, needle2));
        // Expected: -1

        // Test Case 3: Needle found in the middle
        String haystack3 = "hello";
        String needle3 = "ll";
        System.out.println("Test Case 3: haystack = \"" + haystack3 + "\", needle = \"" + needle3 + "\" -> Result = " + solver.strStr(haystack3, needle3));
        // Expected: 2

        // Test Case 4: Needle is empty
        String haystack4 = "abc";
        String needle4 = "";
        System.out.println("Test Case 4: haystack = \"" + haystack4 + "\", needle = \"" + needle4 + "\" -> Result = " + solver.strStr(haystack4, needle4));
        // Expected: 0

        // Test Case 5: Haystack is shorter than needle
        String haystack5 = "a";
        String needle5 = "ab";
        System.out.println("Test Case 5: haystack = \"" + haystack5 + "\", needle = \"" + needle5 + "\" -> Result = " + solver.strStr(haystack5, needle5));
        // Expected: -1

        // Test Case 6: Needle is the entire haystack
        String haystack6 = "apple";
        String needle6 = "apple";
        System.out.println("Test Case 6: haystack = \"" + haystack6 + "\", needle = \"" + needle6 + "\" -> Result = " + solver.strStr(haystack6, needle6));
        // Expected: 0

        // Test Case 7: Needle found at the
    }
}
