package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _1328BreakAPalindrome {
    /**
     * Main solution using Greedy Strategy
     * <p>
     * The greedy approach: scan from left to right and make the first
     * possible change that results in the lexicographically smallest string.
     *
     * @param palindrome input palindromic string
     * @return lexicographically smallest non-palindrome string or empty string
     */
    String breakPalindrome(String palindrome) {
        int n = palindrome.length();

        // Edge case: single character string cannot be broken
        // Any single character is always a palindrome
        if (n == 1) {
            return "";
        }

        // Convert string to character array for easier manipulation
        char[] chars = palindrome.toCharArray();

        // Strategy: Find the first character in the first half that is not 'a'
        // and change it to 'a' to get the lexicographically smallest result

        // Only check first half since the string is a palindrome
        // The second half is just a mirror of the first half
        for (int i = 0; i < n / 2; i++) {
            // If we find a character that is not 'a'
            if (chars[i] != 'a') {
                // Change it to 'a' (smallest possible character)
                // This will break the palindrome and give us the
                // lexicographically smallest possible result
                chars[i] = 'a';
                return new String(chars);
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
        chars[n - 1] = 'b';
        return new String(chars);
    }

    /**
     * Alternative solution with explicit case handling for better understanding
     * <p>
     * This version makes the logic more explicit by separating different cases.
     *
     * @param palindrome input palindromic string
     * @return lexicographically smallest non-palindrome string or empty string
     */
    String breakPalindromeAlternative(String palindrome) {
        int n = palindrome.length();

        // Case 1: Impossible to break (single character)
        if (n == 1) return "";

        char[] chars = palindrome.toCharArray();

        // Case 2: Look for first non-'a' character in first half
        for (int i = 0; i < n / 2; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a'; // Change to 'a' for lexicographically smallest
                return new String(chars);
            }
        }

        // Case 3: All characters are 'a' (entire string is "aaa...a")
        // Change the last character to 'b' to break palindrome
        chars[n - 1] = 'b';
        return new String(chars);
    }

    /**
     * Helper method to verify if a string is a palindrome
     * Used for testing purposes
     */
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        _1328BreakAPalindrome solution = new _1328BreakAPalindrome();

        // Test Case 1: Mixed characters
        System.out.println("=== Test Case 1: Mixed Characters ===");
        String test1 = "abccba";
        String result1 = solution.breakPalindrome(test1);
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Output: \"" + result1 + "\"");
        System.out.println("Expected: \"aaccba\"");
        System.out.println("Is result palindrome: " + solution.isPalindrome(result1));
        System.out.println();

        // Test Case 2: Single character (impossible case)
        System.out.println("=== Test Case 2: Single Character ===");
        String test2 = "a";
        String result2 = solution.breakPalindrome(test2);
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Output: \"" + result2 + "\"");
        System.out.println("Expected: \"\" (empty string)");
        System.out.println();

        // Test Case 3: All 'a' characters
        System.out.println("=== Test Case 3: All 'a' Characters ===");
        String test3 = "aa";
        String result3 = solution.breakPalindrome(test3);
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Output: \"" + result3 + "\"");
        System.out.println("Expected: \"ab\"");
        System.out.println("Is result palindrome: " + solution.isPalindrome(result3));
        System.out.println();

        // Test Case 4: Longer all 'a' string
        System.out.println("=== Test Case 4: Longer All 'a' String ===");
        String test4 = "aaaa";
        String result4 = solution.breakPalindrome(test4);
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Output: \"" + result4 + "\"");
        System.out.println("Expected: \"aaab\"");
        System.out.println("Is result palindrome: " + solution.isPalindrome(result4));
        System.out.println();

        // Test Case 5: Complex palindrome
        System.out.println("=== Test Case 5: Complex Palindrome ===");
        String test5 = "racecar";
        String result5 = solution.breakPalindrome(test5);
        System.out.println("Input: \"" + test5 + "\"");
        System.out.println("Output: \"" + result5 + "\"");
        System.out.println("Expected: \"aacecar\"");
        System.out.println("Is result palindrome: " + solution.isPalindrome(result5));

        // Test alternative method
        System.out.println("\n=== Alternative Method Test ===");
        String altResult = solution.breakPalindromeAlternative(test5);
        System.out.println("Alternative method result: \"" + altResult + "\"");
        System.out.println("Same as main method: " + result5.equals(altResult));
    }
}
