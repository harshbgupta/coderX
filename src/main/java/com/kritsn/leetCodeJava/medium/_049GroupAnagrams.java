package com.kritsn.leetCodeJava.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
    Leetcode 49: Group Anagrams

    Given an array of strings strs, group the anagrams together.
    You can return the answer in any order.
*/
public class _049GroupAnagrams {

    /**
     * 🧠 Algorithm & Approach:
     * <p>
     * 1. Create a hashmap to group strings that are anagrams.
     * - The key will be the sorted version of the string.
     * - The value will be a list of strings that match this sorted key.
     * 2. Iterate over each string in the input array.
     * 3. Sort the characters of the string to generate the key.
     * 4. Put the original string into the appropriate list in the hashmap.
     * 5. Return all values from the hashmap as the result.
     * <p>
     * Time Complexity: O(n * k log k), where n is the number of strings and k is the maximum string length.
     * Space Complexity: O(n * k), for storing grouped anagrams.
     */
    List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupedAnagrams = new HashMap<>();

        for (String word : strs) {
            // Sort characters of the word to use as a key
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedKey = new String(chars);

            // Add word to the corresponding group
            groupedAnagrams.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(word);
        }

        // Return all grouped anagram lists
        return new ArrayList<>(groupedAnagrams.values());
    }

    // 🧪 Main method with test cases
    public static void main(String[] args) {
        _049GroupAnagrams solution = new _049GroupAnagrams();

        System.out.println("Test Case 1:");
        System.out.println("Input: [\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"]");
        System.out.println("Output: " + solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]

        System.out.println("\nTest Case 2:");
        System.out.println("Input: [\"\"]");
        System.out.println("Output: " + solution.groupAnagrams(new String[]{""}));
        // Output: [[""]]

        System.out.println("\nTest Case 3:");
        System.out.println("Input: [\"a\"]");
        System.out.println("Output: " + solution.groupAnagrams(new String[]{"a"}));
        // Output: [["a"]]
    }
}
