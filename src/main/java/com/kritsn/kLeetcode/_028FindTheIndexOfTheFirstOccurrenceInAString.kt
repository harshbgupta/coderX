package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 */
class _028FindIndexOfFirstOccurrence {

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
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _028FindIndexOfFirstOccurrence()

    val haystack1 = "sadbutsad"
    val needle1 = "sad"
    println("Test Case 1: haystack=\"$haystack1\", needle=\"$needle1\" -> Index = ${solver.strStr(haystack1, needle1)}") // Expected: 0

    val haystack2 = "leetcode"
    val needle2 = "leeto"
    println("Test Case 2: haystack=\"$haystack2\", needle=\"$needle2\" -> Index = ${solver.strStr(haystack2, needle2)}") // Expected: -1

    val haystack3 = "hello"
    val needle3 = "ll"
    println("Test Case 3: haystack=\"$haystack3\", needle=\"$needle3\" -> Index = ${solver.strStr(haystack3, needle3)}") // Expected: 2

    val haystack4 = "a"
    val needle4 = "a"
    println("Test Case 4: haystack=\"$haystack4\", needle=\"$needle4\" -> Index = ${solver.strStr(haystack4, needle4)}") // Expected: 0

    val haystack5 = "mississippi"
    val needle5 = "issip"
    println("Test Case 5: haystack=\"$haystack5\", needle=\"$needle5\" -> Index = ${solver.strStr(haystack5, needle5)}") // Expected: 4
}
