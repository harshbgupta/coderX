package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 242: Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An anagram is a word formed by rearranging the letters of another word using all the original letters exactly once.
*/
public class _242ValidAnagram {

    /**
     * 🧠 Algorithm & Approach:
     * <p>
     * 1. If the lengths of the two strings are different, they can't be anagrams.
     * 2. Use a character frequency array of size 26 (for lowercase English letters).
     * 3. Traverse through the first string `s` and increment count of each character.
     * 4. Traverse through the second string `t` and decrement count of each character.
     * 5. If at the end all character frequencies are zero, strings are anagrams.
     * <p>
     * Time Complexity: O(n), where n is the length of the strings.
     * Space Complexity: O(1), because the array size (26) is constant.
     */
    boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] frequency = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;  // Increment for s
            frequency[t.charAt(i) - 'a']--;  // Decrement for t
        }

        // If any frequency is not zero, it's not an anagram
        for (int count : frequency) {
            if (count != 0) return false;
        }

        return true;
    }

    // 🧪 Main method with test cases
    public static void main(String[] args) {
        _242ValidAnagram solution = new _242ValidAnagram();

        System.out.println("Test Case 1:");
        System.out.println("Input: s = 'anagram', t = 'nagaram'");
        System.out.println("Output: " + solution.isAnagram("anagram", "nagaram")); // ✅ true

        System.out.println("\nTest Case 2:");
        System.out.println("Input: s = 'rat', t = 'car'");
        System.out.println("Output: " + solution.isAnagram("rat", "car")); // ❌ false

        System.out.println("\nTest Case 3:");
        System.out.println("Input: s = 'listen', t = 'silent'");
        System.out.println("Output: " + solution.isAnagram("listen", "silent")); // ✅ true

        System.out.println("\nTest Case 4:");
        System.out.println("Input: s = 'aacc', t = 'ccac'");
        System.out.println("Output: " + solution.isAnagram("aacc", "ccac")); // ❌ false
    }
}
