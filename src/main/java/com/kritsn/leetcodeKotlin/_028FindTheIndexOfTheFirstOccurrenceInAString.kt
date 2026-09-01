package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 */
class _028FindTheIndexOfTheFirstOccurrenceInAString {

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
    fun strStr(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0

        val n = haystack.length
        val m = needle.length

        // Only iterate till the point where a full needle can fit
        for (i in 0..n - m) {
            // Extract substring of length m
            val substring = haystack.substring(i, i + m)

            // If it matches needle, return the current index
            if (substring == needle) return i
        }

        // If needle is not found
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _028FindTheIndexOfTheFirstOccurrenceInAString()

            // Test Case 1: Needle found at the beginning
            val haystack1 = "sadbutsad"
            val needle1 = "sad"
            println(
                "Test Case 1: haystack = \"$haystack1\", needle = \"$needle1\" -> Result = ${
                    solver.strStr(
                        haystack1,
                        needle1
                    )
                }"
            )
            // Expected: 0

            // Test Case 2: Needle not found
            val haystack2 = "leetcode"
            val needle2 = "leeto"
            println(
                "Test Case 2: haystack = \"$haystack2\", needle = \"$needle2\" -> Result = ${
                    solver.strStr(
                        haystack2,
                        needle2
                    )
                }"
            )
            // Expected: -1

            // Test Case 3: Needle found in the middle
            val haystack3 = "hello"
            val needle3 = "ll"
            println(
                "Test Case 3: haystack = \"$haystack3\", needle = \"$needle3\" -> Result = ${
                    solver.strStr(
                        haystack3,
                        needle3
                    )
                }"
            )
            // Expected: 2

            // Test Case 4: Needle is empty
            val haystack4 = "abc"
            val needle4 = ""
            println(
                "Test Case 4: haystack = \"$haystack4\", needle = \"$needle4\" -> Result = ${
                    solver.strStr(
                        haystack4,
                        needle4
                    )
                }"
            )
            // Expected: 0

            // Test Case 5: Haystack is shorter than needle
            val haystack5 = "a"
            val needle5 = "ab"
            println(
                "Test Case 5: haystack = \"$haystack5\", needle = \"$needle5\" -> Result = ${
                    solver.strStr(
                        haystack5,
                        needle5
                    )
                }"
            )
            // Expected: -1

            // Test Case 6: Needle is the entire haystack
            val haystack6 = "apple"
            val needle6 = "apple"
            println(
                "Test Case 6: haystack = \"$haystack6\", needle = \"$needle6\" -> Result = ${
                    solver.strStr(
                        haystack6,
                        needle6
                    )
                }"
            )
            // Expected: 0

            // Test Case 7: Needle found at the
        }
    }

}
