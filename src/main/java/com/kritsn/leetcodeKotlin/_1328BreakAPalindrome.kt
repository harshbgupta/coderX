package com.kritsn.leetcodeKotlin

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 15, 2025
 */

class _1328BreakAPalindrome {
    /**
     * Main solution using Greedy Strategy
     *
     * The greedy approach: scan from left to right and make the first
     * possible change that results in the lexicographically smallest string.
     *
     * @param palindrome input palindromic string
     * @return lexicographically smallest non-palindrome string or empty string
     */
    fun breakPalindrome(palindrome: String): String {
        val n = palindrome.length

        // Edge case: single character string cannot be broken
        // Any single character is always a palindrome
        if (n == 1) {
            return ""
        }

        // Convert string to character array for easier manipulation
        val chars = palindrome.toCharArray()

        // Strategy: Find the first character in the first half that is not 'a'
        // and change it to 'a' to get the lexicographically smallest result

        // Only check first half since the string is a palindrome
        // The second half is just a mirror of the first half
        for (i in 0 until n / 2) {
            // If we find a character that is not 'a'
            if (chars[i] != 'a') {
                // Change it to 'a' (smallest possible character)
                // This will break the palindrome and give us the
                // lexicographically smallest possible result
                chars[i] = 'a'
                return String(chars)
            }
        }

        // Special case: If all characters in the first half are 'a'
        // This means the entire string consists of only 'a' characters
        // (since it's a palindrome)
        //
        // Examples: "a", "aa", "aaa", "aaaa", etc.
        //
        // In this case, we cannot change any character in the first half
        // to 'a' (they're already 'a'), so we change the LAST character
        // to 'b' to break the palindrome
        chars[n - 1] = 'b'
        return String(chars)
    }

    /**
     * Alternative solution with explicit case handling for better understanding
     *
     * This version makes the logic more explicit by separating different cases.
     *
     * @param palindrome input palindromic string
     * @return lexicographically smallest non-palindrome string or empty string
     */
    fun breakPalindromeAlternative(palindrome: String): String {
        val n = palindrome.length

        // Case 1: Impossible to break (single character)
        if (n == 1) return ""

        val chars = palindrome.toCharArray()

        // Case 2: Look for first non-'a' character in first half
        for (i in 0 until n / 2) {
            if (chars[i] != 'a') {
                chars[i] = 'a'  // Change to 'a' for lexicographically smallest
                return String(chars)
            }
        }

        // Case 3: All characters are 'a' (entire string is "aaa...a")
        // Change the last character to 'b' to break palindrome
        chars[n - 1] = 'b'
        return String(chars)
    }

    /**
     * Helper method to verify if a string is a palindrome
     * Used for testing purposes
     */
    private fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1

        while (left < right) {
            if (s[left] != s[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _1328BreakAPalindrome()

            // Test Case 1: Mixed characters
            println("=== Test Case 1: Mixed Characters ===")
            val test1 = "abccba"
            val result1 = solution.breakPalindrome(test1)
            println("Input: \"$test1\"")
            println("Output: \"$result1\"")
            println("Expected: \"aaccba\"")
            println("Is result palindrome: ${solution.isPalindrome(result1)}")
            println()

            // Test Case 2: Single character (impossible case)
            println("=== Test Case 2: Single Character ===")
            val test2 = "a"
            val result2 = solution.breakPalindrome(test2)
            println("Input: \"$test2\"")
            println("Output: \"$result2\"")
            println("Expected: \"\" (empty string)")
            println()

            // Test Case 3: All 'a' characters
            println("=== Test Case 3: All 'a' Characters ===")
            val test3 = "aa"
            val result3 = solution.breakPalindrome(test3)
            println("Input: \"$test3\"")
            println("Output: \"$result3\"")
            println("Expected: \"ab\"")
            println("Is result palindrome: ${solution.isPalindrome(result3)}")
            println()

            // Test Case 4: Longer all 'a' string
            println("=== Test Case 4: Longer All 'a' String ===")
            val test4 = "aaaa"
            val result4 = solution.breakPalindrome(test4)
            println("Input: \"$test4\"")
            println("Output: \"$result4\"")
            println("Expected: \"aaab\"")
            println("Is result palindrome: ${solution.isPalindrome(result4)}")
            println()

            // Test Case 5: Complex palindrome
            println("=== Test Case 5: Complex Palindrome ===")
            val test5 = "racecar"
            val result5 = solution.breakPalindrome(test5)
            println("Input: \"$test5\"")
            println("Output: \"$result5\"")
            println("Expected: \"aacecar\"")
            println("Is result palindrome: ${solution.isPalindrome(result5)}")

            // Test alternative method
            println("\\n=== Alternative Method Test ===")
            val altResult = solution.breakPalindromeAlternative(test5)
            println("Alternative method result: \"$altResult\"")
            println("Same as main method: ${result5 == altResult}")
        }
    }
}