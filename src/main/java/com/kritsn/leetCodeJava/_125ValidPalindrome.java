package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
public class _125ValidPalindrome {

    ///////////////////////////////////////////////////////////////////////////
    // Two-Pointer + Filtering Approach:
    //
    // Normalize string:
    // - Convert to lowercase
    // - Remove non-alphanumeric characters
    //
    // Then use two pointers (start and end) to compare characters.
    //
    // 🪜 Steps:
    // 1. Clean the input string by filtering only alphanumeric characters and lowercase it.
    // 2. Use two pointers to scan from both ends.
    // 3. Compare characters — if mismatch return false.
    // 4. If loop completes, return true.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — one pass to clean the string, one pass for comparison.
    // Space Complexity: O(n) — for the cleaned string.
    ///////////////////////////////////////////////////////////////////////////
    boolean isPalindrome(String s) {
        // Step 1: Clean the string
        StringBuilder cleanedBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) cleanedBuilder.append(Character.toLowerCase(c));
        }
        String cleaned = cleanedBuilder.toString();

        // Step 2: Use two-pointer approach to check palindrome
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false; // Mismatch found
            }
            left++;
            right--;
        }

        return true; // All characters matched
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _125ValidPalindrome solver = new _125ValidPalindrome();

        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("Test Case 1: s = \"" + s1 + "\" -> Is Palindrome = " + solver.isPalindrome(s1)); // Expected: true

        String s2 = "race a car";
        System.out.println("Test Case 2: s = \"" + s2 + "\" -> Is Palindrome = " + solver.isPalindrome(s2)); // Expected: false

        String s3 = " ";
        System.out.println("Test Case 3: s = \"" + s3 + "\" -> Is Palindrome = " + solver.isPalindrome(s3)); // Expected: true

        String s4 = ".,";
        System.out.println("Test Case 4: s = \"" + s4 + "\" -> Is Palindrome = " + solver.isPalindrome(s4)); // Expected: true

        String s5 = "No lemon, no melon";
        System.out.println("Test Case 5: s = \"" + s5 + "\" -> Is Palindrome = " + solver.isPalindrome(s5)); // Expected: true
    }
}
