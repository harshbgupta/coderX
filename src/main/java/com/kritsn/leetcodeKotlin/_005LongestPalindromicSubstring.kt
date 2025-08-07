package com.kritsn.leetcodeKotlin

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since July 07, 2025
 */
/*
 * Problem: Longest Palindromic Substring
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * A palindrome is a string that reads the same forward and backward.
 */
class _005LongestPalindromicSubstring {

    /**
     * Main solution method using Expand Around Centers algorithm
     *
     * @param s input string to find longest palindrome in
     * @return the longest palindromic substring
     */
    fun longestPalindrome(s: String): String {
        // Edge case: empty or single character string
        if (s.isEmpty()) return ""
        if (s.length == 1) return s

        // Variables to track the longest palindrome found
        var start = 0  // Starting index of longest palindrome
        var maxLength = 1  // Length of longest palindrome (at least 1)

        // Check each possible center position in the string
        for (i in s.indices) {
            // Case 1: Check for odd-length palindromes (center is at index i)
            // Example: "aba" where center is 'b' at index 1
            val oddLength = expandAroundCenter(s, i, i)

            // Case 2: Check for even-length palindromes (center is between i and i+1)
            // Example: "abba" where center is between the two 'b's
            val evenLength = expandAroundCenter(s, i, i + 1)

            // Find the maximum length palindrome from current center
            val currentMaxLength = maxOf(oddLength, evenLength)

            // Update global maximum if we found a longer palindrome
            if (currentMaxLength > maxLength) {
                maxLength = currentMaxLength

                // Calculate the starting position of this palindrome
                // For a palindrome of length L centered at position i:
                // start = i - (L-1)/2
                start = i - (currentMaxLength - 1) / 2
            }
        }

        // Extract and return the longest palindromic substring
        return s.substring(start, start + maxLength)
    }

    /**
     * Helper method to expand around a given center and find palindrome length
     *
     * This method implements the core "Expand Around Centers" logic by starting
     * from a center position and expanding outward while characters match.
     *
     * @param s the input string
     * @param left left pointer (inclusive)
     * @param right right pointer (inclusive)
     * @return length of the palindrome centered at given position
     */
    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        // Initialize pointers at the given center
        var leftPointer = left
        var rightPointer = right

        // Expand outward while:
        // 1. Pointers are within string bounds
        // 2. Characters at both pointers match (palindrome property)
        while (leftPointer >= 0 &&
            rightPointer < s.length &&
            s[leftPointer] == s[rightPointer]) {

            // Move pointers outward to check next pair of characters
            leftPointer--
            rightPointer++
        }

        // Calculate and return the length of palindrome found
        // When loop exits, leftPointer and rightPointer are at positions
        // where characters don't match, so the palindrome length is:
        // rightPointer - leftPointer - 1
        return rightPointer - leftPointer - 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _005LongestPalindromicSubstring()

            // Test Case 1: Multiple palindromes with odd length
            println("=== Test Case 1: Multiple Palindromes ===")
            val test1 = "babad"
            val result1 = solution.longestPalindrome(test1)
            println("Input: \"$test1\"")
            println("Output: \"$result1\"")
            println("Expected: \"bab\" or \"aba\"")
            println()

            // Test Case 2: Even-length palindrome
            println("=== Test Case 2: Even-Length Palindrome ===")
            val test2 = "cbbd"
            val result2 = solution.longestPalindrome(test2)
            println("Input: \"$test2\"")
            println("Output: \"$result2\"")
            println("Expected: \"bb\"")
            println()

            // Test Case 3: Single character
            println("=== Test Case 3: Single Character ===")
            val test3 = "a"
            val result3 = solution.longestPalindrome(test3)
            println("Input: \"$test3\"")
            println("Output: \"$result3\"")
            println("Expected: \"a\"")
            println()

            // Test Case 4: Entire string is palindromic
            println("=== Test Case 4: Entire String Palindromic ===")
            val test4 = "racecar"
            val result4 = solution.longestPalindrome(test4)
            println("Input: \"$test4\"")
            println("Output: \"$result4\"")
            println("Expected: \"racecar\"")
            println()

            // Test Case 5: No palindromes longer than 1
            println("=== Test Case 5: No Long Palindromes ===")
            val test5 = "abcdef"
            val result5 = solution.longestPalindrome(test5)
            println("Input: \"$test5\"")
            println("Output: \"$result5\"")
            println("Expected: Any single character like \"a\"")
        }
    }
}
