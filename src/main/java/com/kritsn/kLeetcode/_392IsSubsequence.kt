package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 15, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative
 * positions of the remaining characters.
 */
class _392IsSubsequence {

    ///////////////////////////////////////////////////////////////////////////
    // Two-Pointer Matching:
    //
    // We scan both strings:
    // - If current characters match → move both pointers.
    // - Else → move only pointer in t.
    //
    // 🪜 Steps:
    // 1. Initialize pointer `i = 0` for string `s`.
    // 2. Iterate through string `t`:
    //    - If `s[i] == t[j]`, move pointer `i`.
    // 3. If `i == s.length`, then `s` is subsequence of `t`.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n), where n = t.length
    // Space Complexity: O(1) — constant extra space.
    ///////////////////////////////////////////////////////////////////////////
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true // Empty string is always a subsequence

        var i = 0 // Pointer for string s

        for (char in t) {
            // If current characters match, move pointer in s
            if (i < s.length && s[i] == char) {
                i++
            }

            // If all characters from s have matched
            if (i == s.length) {
                return true
            }
        }

        // Not all characters of s were found in t in order
        return false
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _392IsSubsequence()

    val s1 = "abc"
    val t1 = "ahbgdc"
    println("Test Case 1: s = \"$s1\", t = \"$t1\" -> Is Subsequence = ${solver.isSubsequence(s1, t1)}") // Expected: true

    val s2 = "axc"
    val t2 = "ahbgdc"
    println("Test Case 2: s = \"$s2\", t = \"$t2\" -> Is Subsequence = ${solver.isSubsequence(s2, t2)}") // Expected: false

    val s3 = ""
    val t3 = "ahbgdc"
    println("Test Case 3: s = \"$s3\", t = \"$t3\" -> Is Subsequence = ${solver.isSubsequence(s3, t3)}") // Expected: true

    val s4 = "abc"
    val t4 = "abc"
    println("Test Case 4: s = \"$s4\", t = \"$t4\" -> Is Subsequence = ${solver.isSubsequence(s4, t4)}") // Expected: true

    val s5 = "aec"
    val t5 = "abcde"
    println("Test Case 5: s = \"$s5\", t = \"$t5\" -> Is Subsequence = ${solver.isSubsequence(s5, t5)}") // Expected: false
}
