package com.kritsn.leetCodeJava;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Problem: Given two strings s and t, determine if they are isomorphic.
 * An isomorphic transformation is one where each character from s can be mapped uniquely to a character in t, such that replacing s[i] with its mapped value forms t[i].
 * <p>
 * Constraints:
 * - All occurrences of a character must be replaced with another character while preserving the order.
 * - No two characters may map to the same character.
 * <p>
 * Example:
 * Input: s = "egg", t = "add" → Output: true
 * Input: s = "foo", t = "bar" → Output: false
 */
public class _205IsomorphicStrings {

    /**
     * 🧠 Algorithm & Approach:
     * - Use two hash maps:
     * - sourceToTargetMap: Maps each character from s to its corresponding character in t
     * - targetToSourceMap: Maps each character from t to its corresponding character in s
     * - Iterate through each character in s and t:
     * - If mapping exists in sToT, check it maps to current character in t
     * - If mapping exists in tToS, check it maps to current character in s
     * - If not, establish new mappings in both hash maps
     * - If inconsistency found, return false
     * - Else, return true at the end
     * <p>
     * Time Complexity: O(n) where n is the length of s (or t)
     * Space Complexity: O(1) since the hash maps will store at most 256 characters
     */
    boolean isIsomorphic(String s, String t) {
        // Maps to keep character mappings between s -> t and t -> s
        Map<Character, Character> sourceToTargetMap = new HashMap<>();
        Map<Character, Character> targetToSourceMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char chS = s.charAt(i);
            char chT = t.charAt(i);

            // Check if mapping already exists in sourceToTargetMap and it is consistent
            if (sourceToTargetMap.containsKey(chS)) {
                if (sourceToTargetMap.get(chS) != chT) return false;
            } else {
                sourceToTargetMap.put(chS, chT);
            }

            // Check if mapping already exists in targetToSourceMap and it is consistent
            if (targetToSourceMap.containsKey(chT)) {
                if (targetToSourceMap.get(chT) != chS) return false;
            } else {
                targetToSourceMap.put(chT, chS);
            }
        }

        // All characters mapped correctly and consistently
        return true;
    }

    public static void main(String[] args) {
        _205IsomorphicStrings solution = new _205IsomorphicStrings();

        // Test case 1
        String s1 = "egg";
        String t1 = "add";
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\" → Output: " + solution.isIsomorphic(s1, t1) + " (Expected: true)");

        // Test case 2
        String s2 = "foo";
        String t2 = "bar";
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\" → Output: " + solution.isIsomorphic(s2, t2) + " (Expected: false)");

        // Test case 3
        String s3 = "paper";
        String t3 = "title";
        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\" → Output: " + solution.isIsomorphic(s3, t3) + " (Expected: true)");

        // Test case 4
        String s4 = "ab";
        String t4 = "aa";
        System.out.println("Input: s = \"" + s4 + "\", t = \"" + t4 + "\" → Output: " + solution.isIsomorphic(s4, t4) + " (Expected: false)");
    }
}
