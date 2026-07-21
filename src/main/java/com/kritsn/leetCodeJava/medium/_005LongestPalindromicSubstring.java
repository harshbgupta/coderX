package com.kritsn.leetCodeJava.medium;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
 * Problem: Longest Palindromic Substring
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * A palindrome is a string that reads the same forward and backward.
 */
public class _005LongestPalindromicSubstring {

    /**
     * Main solution method using Expand Around Centers algorithm
     *
     * @param s input string to find longest palindrome in
     * @return the longest palindromic substring
     */
    String longestPalindrome(String s) {
        // Edge case: empty or single character string
        if (s.isEmpty()) return "";
        if (s.length() == 1) return s;

        // Variables to track the longest palindrome found
        int start = 0; // Starting index of longest palindrome
        int maxLength = 1; // Length of longest palindrome (at least 1)

        // Check each possible center position in the string
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Check for odd-length palindromes (center is at index i)
            // Example: "aba" where center is 'b' at index 1
            int oddLength = expandAroundCenter(s, i, i);

            // Case 2: Check for even-length palindromes (center is between i and i+1)
            // Example: "abba" where center is between the two 'b's
            int evenLength = expandAroundCenter(s, i, i + 1);

            // Find the maximum length palindrome from current center
            int currentMaxLength = Math.max(oddLength, evenLength);

            // Update global maximum if we found a longer palindrome
            if (currentMaxLength > maxLength) {
                maxLength = currentMaxLength;

                // Calculate the starting position of this palindrome
                // For a palindrome of length L centered at position i:
                // start = i - (L-1)/2
                start = i - (currentMaxLength - 1) / 2;
            }
        }

        // Extract and return the longest palindromic substring
        return s.substring(start, start + maxLength);
    }

    /**
     * Helper method to expand around a given center and find palindrome length
     * <p>
     * This method implements the core "Expand Around Centers" logic by starting
     * from a center position and expanding outward while characters match.
     *
     * @param s     the input string
     * @param left  left pointer (inclusive)
     * @param right right pointer (inclusive)
     * @return length of the palindrome centered at given position
     */
    private int expandAroundCenter(String s, int left, int right) {
        // Initialize pointers at the given center
        int leftPointer = left;
        int rightPointer = right;

        // Expand outward while:
        // 1. Pointers are within string bounds
        // 2. Characters at both pointers match (palindrome property)
        while (leftPointer >= 0 && rightPointer < s.length() && s.charAt(leftPointer) == s.charAt(rightPointer)) {
            // Move pointers outward to check next pair of characters
            leftPointer--;
            rightPointer++;
        }

        // Calculate and return the length of palindrome found
        // When loop exits, leftPointer and rightPointer are at positions
        // where characters don't match, so the palindrome length is:
        // rightPointer - leftPointer - 1
        return rightPointer - leftPointer - 1;
    }

    public static void main(String[] args) {
        _005LongestPalindromicSubstring solution = new _005LongestPalindromicSubstring();

        // Test Case 1: Multiple palindromes with odd length
        System.out.println("=== Test Case 1: Multiple Palindromes ===");
        String test1 = "babad";
        String result1 = solution.longestPalindrome(test1);
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Output: \"" + result1 + "\"");
        System.out.println("Expected: \"bab\" or \"aba\"");
        System.out.println();

        // Test Case 2: Even-length palindrome
        System.out.println("=== Test Case 2: Even-Length Palindrome ===");
        String test2 = "cbbd";
        String result2 = solution.longestPalindrome(test2);
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Output: \"" + result2 + "\"");
        System.out.println("Expected: \"bb\"");
        System.out.println();

        // Test Case 3: Single character
        System.out.println("=== Test Case 3: Single Character ===");
        String test3 = "a";
        String result3 = solution.longestPalindrome(test3);
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Output: \"" + result3 + "\"");
        System.out.println("Expected: \"a\"");
        System.out.println();

        // Test Case 4: Entire string is palindromic
        System.out.println("=== Test Case 4: Entire String Palindromic ===");
        String test4 = "racecar";
        String result4 = solution.longestPalindrome(test4);
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Output: \"" + result4 + "\"");
        System.out.println("Expected: \"racecar\"");
        System.out.println();

        // Test Case 5: No palindromes longer than 1
        System.out.println("=== Test Case 5: No Long Palindromes ===");
        String test5 = "abcdef";
        String result5 = solution.longestPalindrome(test5);
        System.out.println("Input: \"" + test5 + "\"");
        System.out.println("Output: \"" + result5 + "\"");
        System.out.println("Expected: Any single character like \"a\"");
    }
}
