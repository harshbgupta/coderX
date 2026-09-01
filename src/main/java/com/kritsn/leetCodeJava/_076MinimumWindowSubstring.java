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
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s
 * such that every character in t (including duplicates) is included in the window. If there is no such
 * substring, return the empty string "".
 */
public class _076MinimumWindowSubstring {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window + Frequency Maps
    //
    // We keep expanding the right pointer to add characters into the window.
    // Once the window is valid (contains all characters from `t`), we try to
    // contract from the left to find a smaller valid window.
    //
    // 🪜 Steps:
    // 1. Build a frequency map for characters in t.
    // 2. Slide a window using two pointers (left and right).
    // 3. Expand right until the window is valid.
    // 4. Once valid, move left to minimize the window.
    // 5. Track and return the minimum valid window found.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(s.length + t.length) — One pass for building the map and one pass over `s` with two pointers.
    // Space Complexity: O(128) ~ O(1) — Since we only use ASCII characters, the map size is constant.
    ///////////////////////////////////////////////////////////////////////////
    String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>(); // Frequency map for characters in t
        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int count = 0;

        Map<Character, Integer> window = new HashMap<>(); // Current window character counts

        while (right < s.length()) {
            char ch = s.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);

            // If current character is needed and window count does not exceed need count
            if (need.containsKey(ch) && window.get(ch) <= need.get(ch)) {
                count++;
            }

            // Try to shrink the window from the left
            while (count == t.length()) {
                // Update min window if smaller is found
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);

                // If removing character breaks the requirement
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                    count--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        _076MinimumWindowSubstring solver = new _076MinimumWindowSubstring();

        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("Test Case 1: s = \"" + s1 + "\", t = \"" + t1 + "\" -> Output = \"" + solver.minWindow(s1, t1) + "\""); // Expected: "BANC"

        String s2 = "a";
        String t2 = "a";
        System.out.println("Test Case 2: s = \"" + s2 + "\", t = \"" + t2 + "\" -> Output = \"" + solver.minWindow(s2, t2) + "\""); // Expected: "a"

        String s3 = "a";
        String t3 = "aa";
        System.out.println("Test Case 3: s = \"" + s3 + "\", t = \"" + t3 + "\" -> Output = \"" + solver.minWindow(s3, t3) + "\""); // Expected: ""

        String s4 = "ab";
        String t4 = "b";
        System.out.println("Test Case 4: s = \"" + s4 + "\", t = \"" + t4 + "\" -> Output = \"" + solver.minWindow(s4, t4) + "\""); // Expected: "b"

        String s5 = "ab";
        String t5 = "a";
        System.out.println("Test Case 5: s = \"" + s5 + "\", t = \"" + t5 + "\" -> Output = \"" + solver.minWindow(s5, t5) + "\""); // Expected: "a"
    }
}
