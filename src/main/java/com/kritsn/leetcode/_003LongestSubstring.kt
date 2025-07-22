package com.kritsn.leetcode

import kotlin.math.max

/**
 * Longest Substring Without Repeating Characters
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * This solution uses a string-based sliding window approach. It builds a temporary
 * string and adjusts it when a duplicate character is found.
 *
 *
 * Time Complexity: O(n2) - We iterate through the string only once.
 * Space Complexity: O(k) - Where k is the number of unique characters in the string (or the size of the character set).
 *                        This is because the map stores at most k unique characters.
 *
 * @param s The input string.
 * @return The length of the longest substring without repeating characters.
 */
class _003LongestSubstring {
    private fun lengthOfLongestSubstringSol(s: String): Int {
        // `longestSubstringFound` stores the longest valid substring discovered so far.
        var longestSubstringFound = ""
        // `currentSubstring` acts as our "sliding window", holding the current substring without repeats.
        var currentSubstring = ""

        // Iterate through each character of the input string with its index.
        s.forEach { letter ->
            // Check if the current character already exists in our window.
            val duplicateCharIndex = currentSubstring.indexOf(letter)

            if (duplicateCharIndex == -1) {
                // Case 1: The character is NOT in the current substring.
                // We can safely extend our window by appending the new character.
                currentSubstring += letter
            } else {
                // Case 2: A duplicate character is found.
                // We need to slide the window forward. The new window starts from the
                // character immediately after the *first* occurrence of the duplicate.
                // Then, we append the current character to the end of the new window.
                // For example, if currentSubstring is "abc" and the letter is 'a',
                // the new substring becomes "bc" + "a" -> "bca".
                currentSubstring = currentSubstring.substring(duplicateCharIndex + 1) + letter
            }

            // After each step, check if the current window is the longest we've seen.
            if (currentSubstring.length > longestSubstringFound.length) {
                longestSubstringFound = currentSubstring
            }
        }

        return longestSubstringFound.length
    }


    /**
     * Longest Substring Without Repeating Characters
     * Given a string s, find the length of the longest substring without repeating characters.
     *
     * This is an optimized solution using the sliding window technique.
     *
     * Time Complexity: O(n) - We iterate through the string only once.
     * Space Complexity: O(k) - Where k is the number of unique characters in the string (or the size of the character set).
     *                        This is because the map stores at most k unique characters.
     */
    private fun lengthOfLongestSubstringOptimized(s: String): Int {
        // Map to store the most recent index of each character: {character -> index}
        val charIndexMap = HashMap<Char, Int>()
        var maxLength = 0
        var windowStart = 0

        // 'windowEnd' is the right pointer of our sliding window
        for (windowEnd in s.indices) {
            val rightChar = s[windowEnd]

            // Check if the character is already in our map.
            // If it is, its previous occurrence might be inside our current window.
            if (charIndexMap.containsKey(rightChar)) {
                // A duplicate is found. We must shrink the window from the left.
                // Move the start of our window to the position right after the
                // last occurrence of the current character.
                // We use max() to ensure the window's start only moves forward.
                val previousIndex = charIndexMap[rightChar]!!
                windowStart = max(windowStart, previousIndex + 1)
            }

            // Update the character's last seen index to its current position.
            charIndexMap[rightChar] = windowEnd

            // Calculate the length of the current valid window and update the max length found so far.
            val currentLength = windowEnd - windowStart + 1
            maxLength = max(maxLength, currentLength)
        }

        return maxLength
    }

    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _003LongestSubstring()
            val string1 = "pwwkew"
            val string2 = "abcabcbb"
            val string3 = "bbbbb"
            val string4 = ""
            val string5 = "abba"

            println("Input: '$string1', Output: ${solver.lengthOfLongestSubstringOptimized(string1)}") // Expected: 3 ("wke")
            println("Input: '$string2', Output: ${solver.lengthOfLongestSubstringOptimized(string2)}") // Expected: 3 ("abc")
            println("Input: '$string3', Output: ${solver.lengthOfLongestSubstringOptimized(string3)}") // Expected: 1 ("b")
            println("Input: '$string4', Output: ${solver.lengthOfLongestSubstringOptimized(string4)}") // Expected: 0
            println("Input: '$string5', Output: ${solver.lengthOfLongestSubstringOptimized(string5)}") // Expected: 2 ("ab" or "ba")
        }
    }
}