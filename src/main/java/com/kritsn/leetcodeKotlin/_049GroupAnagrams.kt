package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/*
    Leetcode 49: Group Anagrams

    Given an array of strings strs, group the anagrams together.
    You can return the answer in any order.
*/
class _049GroupAnagrams {

    /**
     * 🧠 Algorithm & Approach:
     *
     * 1. Create a hashmap to group strings that are anagrams.
     *    - The key will be the sorted version of the string.
     *    - The value will be a list of strings that match this sorted key.
     * 2. Iterate over each string in the input array.
     * 3. Sort the characters of the string to generate the key.
     * 4. Put the original string into the appropriate list in the hashmap.
     * 5. Return all values from the hashmap as the result.
     *
     * Time Complexity: O(n * k log k), where n is the number of strings and k is the maximum string length.
     * Space Complexity: O(n * k), for storing grouped anagrams.
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val groupedAnagrams = HashMap<String, MutableList<String>>()

        for (word in strs) {
            // Sort characters of the word to use as a key
            val sortedKey = word.toCharArray().sorted().joinToString("")

            // Add word to the corresponding group
            groupedAnagrams.computeIfAbsent(sortedKey) { mutableListOf() }.add(word)
        }

        // Return all grouped anagram lists
        return groupedAnagrams.values.toList()
    }

    companion object {
        @JvmStatic

// 🧪 Main method with test cases
        fun main(array: Array<String>) {
            val solution = _049GroupAnagrams()

            println("Test Case 1:")
            println("Input: [\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"]")
            println("Output: ${solution.groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"))}")
            // Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]

            println("\nTest Case 2:")
            println("Input: [\"\"]")
            println("Output: ${solution.groupAnagrams(arrayOf(""))}")
            // Output: [[""]]

            println("\nTest Case 3:")
            println("Input: [\"a\"]")
            println("Output: ${solution.groupAnagrams(arrayOf("a"))}")
            // Output: [["a"]]
        }

    }
}