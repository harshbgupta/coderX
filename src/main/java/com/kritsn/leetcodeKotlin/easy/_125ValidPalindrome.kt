package com.kritsn.leetcodeKotlin.easy
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
class _125ValidPalindrome {

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
    fun isPalindrome(s: String): Boolean {
        // Step 1: Clean the string
        val cleaned = s.filter { it.isLetterOrDigit() }.lowercase()

        // Step 2: Use two-pointer approach to check palindrome
        var left = 0
        var right = cleaned.length - 1

        while (left < right) {
            if (cleaned[left] != cleaned[right]) {
                return false // Mismatch found
            }
            left++
            right--
        }

        return true // All characters matched
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _125ValidPalindrome()

    val s1 = "A man, a plan, a canal: Panama"
    println("Test Case 1: s = \"$s1\" -> Is Palindrome = ${solver.isPalindrome(s1)}") // Expected: true

    val s2 = "race a car"
    println("Test Case 2: s = \"$s2\" -> Is Palindrome = ${solver.isPalindrome(s2)}") // Expected: false

    val s3 = " "
    println("Test Case 3: s = \"$s3\" -> Is Palindrome = ${solver.isPalindrome(s3)}") // Expected: true

    val s4 = ".,"
    println("Test Case 4: s = \"$s4\" -> Is Palindrome = ${solver.isPalindrome(s4)}") // Expected: true

    val s5 = "No lemon, no melon"
    println("Test Case 5: s = \"$s5\" -> Is Palindrome = ${solver.isPalindrome(s5)}") // Expected: true
}
