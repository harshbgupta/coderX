package com.kritsn.leetcodeKotlin.easy
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/*
    Leetcode 290. Word Pattern

    Given a pattern and a string s, find if s follows the same pattern.

    Follow means:
    - One-to-one mapping between letters in pattern and words in s.
    - Each letter maps to exactly one word.
    - Each word maps to exactly one letter.
*/

class _290WordPattern {

    /**
     * 🧠 Algorithm & Approach:
     *
     * 1. Split the input string `s` into a list of words.
     * 2. Check if the number of characters in the `pattern` equals the number of words.
     * 3. Use two HashMaps to maintain bijective mappings:
     *      - charToWordMap: maps each character from pattern → word in s
     *      - wordToCharMap: maps each word in s → character in pattern
     * 4. For each character and word at the same index:
     *      - If char already mapped, ensure it maps to same word.
     *      - If word already mapped, ensure it maps to same char.
     * 5. If any mismatch is found, return false.
     * 6. If the loop completes without issues, return true.
     *
     * Time Complexity: O(n) where n is the number of words in `s`.
     * Space Complexity: O(n) for storing mappings.
     */
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")

        // If pattern and word count differ, it's not a valid match
        if (pattern.length != words.size) return false

        val charToWordMap = HashMap<Char, String>()
        val wordToCharMap = HashMap<String, Char>()

        for (i in pattern.indices) {
            val char = pattern[i]
            val word = words[i]

            // Check if current pattern character already maps to a word
            if (charToWordMap.containsKey(char)) {
                if (charToWordMap[char] != word) return false
            } else {
                charToWordMap[char] = word
            }

            // Check if current word already maps to a pattern character
            if (wordToCharMap.containsKey(word)) {
                if (wordToCharMap[word] != char) return false
            } else {
                wordToCharMap[word] = char
            }
        }

        return true
    }
}

// 🧪 Main method to test the solution
fun main() {
    val solution = _290WordPattern()

    println("Test Case 1:")
    println("Input: pattern = 'abba', s = 'dog cat cat dog'")
    println("Output: ${solution.wordPattern("abba", "dog cat cat dog")}") // ✅ true

    println("\nTest Case 2:")
    println("Input: pattern = 'abba', s = 'dog cat cat fish'")
    println("Output: ${solution.wordPattern("abba", "dog cat cat fish")}") // ❌ false

    println("\nTest Case 3:")
    println("Input: pattern = 'aaaa', s = 'dog cat cat dog'")
    println("Output: ${solution.wordPattern("aaaa", "dog cat cat dog")}") // ❌ false

    println("\nTest Case 4:")
    println("Input: pattern = 'abba', s = 'dog dog dog dog'")
    println("Output: ${solution.wordPattern("abba", "dog dog dog dog")}") // ❌ false
}

    