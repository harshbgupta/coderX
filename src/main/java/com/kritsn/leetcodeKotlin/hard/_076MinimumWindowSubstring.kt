package com.kritsn.leetcodeKotlin.hard
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s
 * such that every character in t (including duplicates) is included in the window. If there is no such
 * substring, return the empty string "".
 */
class _076MinimumWindowSubstring {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window + Frequency Maps
    //
    // We keep expanding the right pointer to add characters into the window.
    // Once the window is valid (contains all characters from `t`), we try to
    // contract from the left to find a smaller valid window.
    //
    // 🪜 Steps:
    // 1. Build a frequency map for characters in t.
    // 2. Slide a window using two pointers (left and right).
    // 3. Expand right until the window is valid.
    // 4. Once valid, move left to minimize the window.
    // 5. Track and return the minimum valid window found.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(s.length + t.length) — One pass for building the map and one pass over `s` with two pointers.
    // Space Complexity: O(128) ~ O(1) — Since we only use ASCII characters, the map size is constant.
    ///////////////////////////////////////////////////////////////////////////
    fun minWindow(s: String, t: String): String {
        if (s.isEmpty() || t.isEmpty()) return ""

        val need = mutableMapOf<Char, Int>() // Frequency map for characters in t
        for (ch in t) {
            need[ch] = need.getOrDefault(ch, 0) + 1
        }

        var left = 0
        var right = 0
        var minLen = Int.MAX_VALUE
        var minStart = 0
        var count = 0

        val window = mutableMapOf<Char, Int>() // Current window character counts

        while (right < s.length) {
            val ch = s[right]
            window[ch] = window.getOrDefault(ch, 0) + 1

            // If current character is needed and window count does not exceed need count
            if (need.containsKey(ch) && window[ch]!! <= need[ch]!!) {
                count++
            }

            // Try to shrink the window from the left
            while (count == t.length) {
                // Update min window if smaller is found
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1
                    minStart = left
                }

                val leftChar = s[left]
                window[leftChar] = window[leftChar]!! - 1

                // If removing character breaks the requirement
                if (need.containsKey(leftChar) && window[leftChar]!! < need[leftChar]!!) {
                    count--
                }

                left++
            }

            right++
        }

        return if (minLen == Int.MAX_VALUE) "" else s.substring(minStart, minStart + minLen)
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _076MinimumWindowSubstring()

            val s1 = "ADOBECODEBANC"
            val t1 = "ABC"
            println("Test Case 1: s = \"$s1\", t = \"$t1\" -> Output = \"${solver.minWindow(s1, t1)}\"") // Expected: "BANC"

            val s2 = "a"
            val t2 = "a"
            println("Test Case 2: s = \"$s2\", t = \"$t2\" -> Output = \"${solver.minWindow(s2, t2)}\"") // Expected: "a"

            val s3 = "a"
            val t3 = "aa"
            println("Test Case 3: s = \"$s3\", t = \"$t3\" -> Output = \"${solver.minWindow(s3, t3)}\"") // Expected: ""

            val s4 = "ab"
            val t4 = "b"
            println("Test Case 4: s = \"$s4\", t = \"$t4\" -> Output = \"${solver.minWindow(s4, t4)}\"") // Expected: "b"

            val s5 = "ab"
            val t5 = "a"
            println("Test Case 5: s = \"$s5\", t = \"$t5\" -> Output = \"${solver.minWindow(s5, t5)}\"") // Expected: "a"
        }

    }
}