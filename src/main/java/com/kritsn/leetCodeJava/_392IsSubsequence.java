package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative
 * positions of the remaining characters.
 */
public class _392IsSubsequence {

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
    boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true; // Empty string is always a subsequence

        int i = 0; // Pointer for string s

        for (char c : t.toCharArray()) {
            // If current characters match, move pointer in s
            if (i < s.length() && s.charAt(i) == c) {
                i++;
            }

            // If all characters from s have matched
            if (i == s.length()) {
                return true;
            }
        }

        // Not all characters of s were found in t in order
        return false;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _392IsSubsequence solver = new _392IsSubsequence();

        String s1 = "abc";
        String t1 = "ahbgdc";
        System.out.println("Test Case 1: s = \"" + s1 + "\", t = \"" + t1 + "\" -> Is Subsequence = " + solver.isSubsequence(s1, t1)); // Expected: true

        String s2 = "axc";
        String t2 = "ahbgdc";
        System.out.println("Test Case 2: s = \"" + s2 + "\", t = \"" + t2 + "\" -> Is Subsequence = " + solver.isSubsequence(s2, t2)); // Expected: false

        String s3 = "";
        String t3 = "ahbgdc";
        System.out.println("Test Case 3: s = \"" + s3 + "\", t = \"" + t3 + "\" -> Is Subsequence = " + solver.isSubsequence(s3, t3)); // Expected: true

        String s4 = "abc";
        String t4 = "abc";
        System.out.println("Test Case 4: s = \"" + s4 + "\", t = \"" + t4 + "\" -> Is Subsequence = " + solver.isSubsequence(s4, t4)); // Expected: true

        String s5 = "aec";
        String t5 = "abcde";
        System.out.println("Test Case 5: s = \"" + s5 + "\", t = \"" + t5 + "\" -> Is Subsequence = " + solver.isSubsequence(s5, t5)); // Expected: false
    }
}
