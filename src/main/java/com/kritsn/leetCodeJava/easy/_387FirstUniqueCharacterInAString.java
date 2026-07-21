package com.kritsn.leetCodeJava.easy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 15, 2025
 */

/*
Leetcode 387. First Unique Character in a String

Problem Statement:
Given a string s, find the first non-repeating character in it and return its index.
If it does not exist, return -1.

Examples:
Input: "leetcode"      -> Output: 0
Input: "loveleetcode"  -> Output: 2
Input: "aabb"          -> Output: -1

Constraints:
1 <= s.length <= 10^5
s consists of only lowercase English letters.
*/

public class _387FirstUniqueCharacterInAString {

    /**
     * 🧠 Algorithm & Approach:
     * 1. Use a frequency array of size 26 to store counts of each character.
     * 2. First pass: count frequencies of all characters.
     * 3. Second pass: find the first index where frequency == 1.
     * 4. If not found, return -1.
     *
     * Time Complexity: O(n)  -> Two passes over the string of length n.
     * Space Complexity: O(1) -> Since frequency array size is constant (26 letters).
     */
    public int firstUniqChar(String s) {
        // Step 1: Create frequency array for 26 lowercase English letters
        int[] freq = new int[26];

        // Step 2: Count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 3: Find the first index with frequency == 1
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i; // Found the first unique character
            }
        }

        // Step 4: No unique character found
        return -1;
    }

    /**
     * 🧠 Algorithm (LinkedHashMap):
     * 1. Use a LinkedHashMap to store character frequencies in insertion order.
     * 2. Iterate string and update frequency counts.
     * 3. Iterate LinkedHashMap: find first entry with frequency == 1.
     * 4. Return index of that character by scanning the string again.
     *
     * Time Complexity: O(n)  -> Two passes (one to count, one to find index).
     * Space Complexity: O(1) -> At most 26 lowercase letters stored.
     */
    public int firstUniqCharLinkedHashMap(String s) {
        // Step 1: Create LinkedHashMap to maintain order of insertion
        Map<Character, Integer> freqMap = new LinkedHashMap<>();

        // Step 2: Count frequencies
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 3: Find first character with frequency == 1
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                // Step 4: Return its index by scanning string
                return s.indexOf(entry.getKey());
            }
        }

        return -1; // No unique character found
    }


    // Main method with test cases
    public static void main(String[] args) {
        _387FirstUniqueCharacterInAString solver = new _387FirstUniqueCharacterInAString();

        // Test case 1
        String s1 = "leetcode";
        System.out.println("Input: " + s1 + " -> Output: " + solver.firstUniqChar(s1) + " (Expected: 0)");

        // Test case 2
        String s2 = "loveleetcode";
        System.out.println("Input: " + s2 + " -> Output: " + solver.firstUniqChar(s2) + " (Expected: 2)");

        // Test case 3
        String s3 = "aabb";
        System.out.println("Input: " + s3 + " -> Output: " + solver.firstUniqChar(s3) + " (Expected: -1)");

        // Test case 4 (edge case: single character)
        String s4 = "z";
        System.out.println("Input: " + s4 + " -> Output: " + solver.firstUniqChar(s4) + " (Expected: 0)");
    }
}