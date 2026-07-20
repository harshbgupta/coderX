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
 * Longest Substring Without Repeating Characters
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * This solution uses a string-based sliding window approach. It builds a temporary
 * string and adjusts it when a duplicate character is found.
 * <p>
 * Time Complexity: O(n^2) - We iterate through the string only once.
 * Space Complexity: O(k) - Where k is the number of unique characters in the string (or the size of the character set).
 * This is because the map stores at most k unique characters.
 */
public class _003LongestSubstring {
    private int lengthOfLongestSubstringSol(String s) {
        // `longestSubstringFound` stores the longest valid substring discovered so far.
        String longestSubstringFound = "";
        // `currentSubstring` acts as our "sliding window", holding the current substring without repeats.
        String currentSubstring = "";

        // Iterate through each character of the input string with its index.
        for (char letter : s.toCharArray()) {
            // Check if the current character already exists in our window.
            int duplicateCharIndex = currentSubstring.indexOf(letter);

            if (duplicateCharIndex == -1) {
                // Case 1: The character is NOT in the current substring.
                // We can safely extend our window by appending the new character.
                currentSubstring += letter;
            } else {
                // Case 2: A duplicate character is found.
                // We need to slide the window forward. The new window starts from the
                // character immediately after the *first* occurrence of the duplicate.
                // Then, we append the current character to the end of the new window.
                // For example, if currentSubstring is "abc" and the letter is 'a',
                // the new substring becomes "bc" + "a" -> "bca".
                currentSubstring = currentSubstring.substring(duplicateCharIndex + 1) + letter;
            }

            // After each step, check if the current window is the longest we've seen.
            if (currentSubstring.length() > longestSubstringFound.length()) {
                longestSubstringFound = currentSubstring;
            }
        }

        return longestSubstringFound.length();
    }

    /**
     * Longest Substring Without Repeating Characters
     * Given a string s, find the length of the longest substring without repeating characters.
     * <p>
     * This is an optimized solution using the sliding window technique.
     * <p>
     * Time Complexity: O(n) - We iterate through the string only once.
     * Space Complexity: O(k) - Where k is the number of unique characters in the string (or the size of the character set).
     * This is because the map stores at most k unique characters.
     */
    private int lengthOfLongestSubstringOptimized(String s) {
        // Map to store the most recent index of each character: {character -> index}
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int windowStart = 0;

        // 'windowEnd' is the right pointer of our sliding window
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);

            // Check if the character is already in our map.
            // If it is, its previous occurrence might be inside our current window.
            if (charIndexMap.containsKey(rightChar)) {
                // A duplicate is found. We must shrink the window from the left.
                // Move the start of our window to the position right after the
                // last occurrence of the current character.
                // We use max() to ensure the window's start only moves forward.
                int previousIndex = charIndexMap.get(rightChar);
                windowStart = Math.max(windowStart, previousIndex + 1);
            }

            // Update the character's last seen index to its current position.
            charIndexMap.put(rightChar, windowEnd);

            // Calculate the length of the current valid window and update the max length found so far.
            int currentLength = windowEnd - windowStart + 1;
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        _003LongestSubstring solver = new _003LongestSubstring();
        String string1 = "pwwkew";
        String string2 = "abcabcbb";
        String string3 = "bbbbb";
        String string4 = "";
        String string5 = "abba";

        System.out.println("Input: '" + string1 + "', Output: " + solver.lengthOfLongestSubstringOptimized(string1)); // Expected: 3 ("wke")
        System.out.println("Input: '" + string2 + "', Output: " + solver.lengthOfLongestSubstringOptimized(string2)); // Expected: 3 ("abc")
        System.out.println("Input: '" + string3 + "', Output: " + solver.lengthOfLongestSubstringOptimized(string3)); // Expected: 1 ("b")
        System.out.println("Input: '" + string4 + "', Output: " + solver.lengthOfLongestSubstringOptimized(string4)); // Expected: 0
        System.out.println("Input: '" + string5 + "', Output: " + solver.lengthOfLongestSubstringOptimized(string5)); // Expected: 2 ("ab" or "ba")
    }
}
