package com.kritsn.leetcode

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since July 07, 2025
 */

fun main() {
    val string1 = "babad"
    val string2 = "cbbd"

    println("Longest Palindrome in $string1 is ${_005LongestPalindromicSubstring().longestPalindrome(string1)}")
    println("Longest Palindrome in $string2 is ${_005LongestPalindromicSubstring().longestPalindrome(string2)}")
}
/**
 * LeetCode 5: Longest Palindromic Substring
 *
 * Finds the longest palindromic substring in a given string `s`.
 * This solution uses the "Expand Around Center" approach.
 */
class _005LongestPalindromicSubstring {
    /**
     * Given a string s, return the longest palindromic substring in s.
     *
     * @param s The input string.
     * @return The longest substring of `s` that is a palindrome.
     */
    fun longestPalindrome(s: String): String {
        // A string with less than 2 characters is its own longest palindrome.
        if (s.length < 2) {
            return s
        }

        var start = 0
        var end = 0

        for (i in s.indices) {
            // Case 1: Find the longest odd-length palindrome with center at `i`.
            // Example: "racecar", center is 'e'.
            val len1 = expandAroundCenter(s, i, i)

            // Case 2: Find the longest even-length palindrome with center between `i` and `i+1`.
            // Example: "aabbaa", center is between the two 'b's.
            val len2 = expandAroundCenter(s, i, i + 1)

            val maxLen = maxOf(len1, len2)

            // If we found a new longest palindrome, update its start and end indices.
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2
                end = i + maxLen / 2
            }
        }

        // The +1 is necessary because Kotlin's substring `endIndex` is exclusive.
        return s.substring(start, end + 1)
    }

    /**
     * A helper function that finds the length of a palindrome by expanding from a given center.
     * The center is defined by the initial `left` and `right` pointers.
     *
     * @param s The input string.
     * @param left The initial left pointer of the center.
     * @param right The initial right pointer of the center.
     * @return The length of the palindrome found.
     */
    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var l = left
        var r = right

        // Expand outwards as long as the pointers are in bounds and the characters match.
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--
            r++
        }

        // The length of the palindrome is the distance between the final pointers.
        // Example: For "aba", l ends at -1, r ends at 3. Length = 3 - (-1) - 1 = 3.
        return r - l - 1
    }
}