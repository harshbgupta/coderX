package com.kritsn.leetCodeJava.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
    Leetcode 290. Word Pattern

    Given a pattern and a string s, find if s follows the same pattern.

    Follow means:
    - One-to-one mapping between letters in pattern and words in s.
    - Each letter maps to exactly one word.
    - Each word maps to exactly one letter.
*/
public class _290WordPattern {

    /**
     * 🧠 Algorithm & Approach:
     * <p>
     * 1. Split the input string `s` into a list of words.
     * 2. Check if the number of characters in the `pattern` equals the number of words.
     * 3. Use two HashMaps to maintain bijective mappings:
     * - charToWordMap: maps each character from pattern → word in s
     * - wordToCharMap: maps each word in s → character in pattern
     * 4. For each character and word at the same index:
     * - If char already mapped, ensure it maps to same word.
     * - If word already mapped, ensure it maps to same char.
     * 5. If any mismatch is found, return false.
     * 6. If the loop completes without issues, return true.
     * <p>
     * Time Complexity: O(n) where n is the number of words in `s`.
     * Space Complexity: O(n) for storing mappings.
     */
    boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        // If pattern and word count differ, it's not a valid match
        if (pattern.length() != words.length) return false;

        Map<Character, String> charToWordMap = new HashMap<>();
        Map<String, Character> wordToCharMap = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            // Check if current pattern character already maps to a word
            if (charToWordMap.containsKey(ch)) {
                if (!charToWordMap.get(ch).equals(word)) return false;
            } else {
                charToWordMap.put(ch, word);
            }

            // Check if current word already maps to a pattern character
            if (wordToCharMap.containsKey(word)) {
                if (wordToCharMap.get(word) != ch) return false;
            } else {
                wordToCharMap.put(word, ch);
            }
        }

        return true;
    }

    // 🧪 Main method to test the solution
    public static void main(String[] args) {
        _290WordPattern solution = new _290WordPattern();

        System.out.println("Test Case 1:");
        System.out.println("Input: pattern = 'abba', s = 'dog cat cat dog'");
        System.out.println("Output: " + solution.wordPattern("abba", "dog cat cat dog")); // ✅ true

        System.out.println("\nTest Case 2:");
        System.out.println("Input: pattern = 'abba', s = 'dog cat cat fish'");
        System.out.println("Output: " + solution.wordPattern("abba", "dog cat cat fish")); // ❌ false

        System.out.println("\nTest Case 3:");
        System.out.println("Input: pattern = 'aaaa', s = 'dog cat cat dog'");
        System.out.println("Output: " + solution.wordPattern("aaaa", "dog cat cat dog")); // ❌ false

        System.out.println("\nTest Case 4:");
        System.out.println("Input: pattern = 'abba', s = 'dog dog dog dog'");
        System.out.println("Output: " + solution.wordPattern("abba", "dog dog dog dog")); // ❌ false
    }
}
