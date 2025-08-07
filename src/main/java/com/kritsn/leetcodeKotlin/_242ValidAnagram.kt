package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/*
Leetcode 242: Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An anagram is a word formed by rearranging the letters of another word using all the original letters exactly once.
*/

class _242ValidAnagram {

    /**
     * 🧠 Algorithm & Approach:
     *
     * 1. If the lengths of the two strings are different, they can't be anagrams.
     * 2. Use a character frequency array of size 26 (for lowercase English letters).
     * 3. Traverse through the first string `s` and increment count of each character.
     * 4. Traverse through the second string `t` and decrement count of each character.
     * 5. If at the end all character frequencies are zero, strings are anagrams.
     *
     * Time Complexity: O(n), where n is the length of the strings.
     * Space Complexity: O(1), because the array size (26) is constant.
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val frequency = IntArray(26)

        for (i in s.indices) {
            frequency[s[i] - 'a']++  // Increment for s
            frequency[t[i] - 'a']--  // Decrement for t
        }

        // If any frequency is not zero, it's not an anagram
        for (count in frequency) {
            if (count != 0) return false
        }

        return true
    }
}

// 🧪 Main method with test cases
fun main() {
    val solution = _242ValidAnagram()

    println("Test Case 1:")
    println("Input: s = 'anagram', t = 'nagaram'")
    println("Output: ${solution.isAnagram("anagram", "nagaram")}") // ✅ true

    println("\nTest Case 2:")
    println("Input: s = 'rat', t = 'car'")
    println("Output: ${solution.isAnagram("rat", "car")}") // ❌ false

    println("\nTest Case 3:")
    println("Input: s = 'listen', t = 'silent'")
    println("Output: ${solution.isAnagram("listen", "silent")}") // ✅ true

    println("\nTest Case 4:")
    println("Input: s = 'aacc', t = 'ccac'")
    println("Output: ${solution.isAnagram("aacc", "ccac")}") // ❌ false
}
