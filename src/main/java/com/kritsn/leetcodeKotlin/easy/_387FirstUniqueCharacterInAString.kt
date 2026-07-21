package com.kritsn.leetcodeKotlin.easy

import com.kritsn.leetCodeJava.easy._387FirstUniqueCharacterInAString
import kotlin.collections.iterator
import kotlin.text.iterator

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 15, 2025
 */

class _387FirstUniqueCharacterInAString {
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
    // Approach 1: Using frequency array
    fun firstUniqChar(s: String): Int {
        // Step 1: Frequency array for 26 lowercase English letters
        val freq = IntArray(26)

        // Step 2: Count frequency of each character
        for (c in s) {
            freq[c - 'a']++
        }

        // Step 3: Find the first index with frequency == 1
        for (i in s.indices) {
            if (freq[s[i] - 'a'] == 1) {
                return i
            }
        }

        // Step 4: No unique character found
        return -1
    }

    /**
     * 🧠 Algorithm (LinkedHashMap equivalent):
     * 1. Use a LinkedHashMap to store character frequencies in insertion order.
     * 2. Iterate string and update frequency counts.
     * 3. Iterate LinkedHashMap: find first entry with frequency == 1.
     * 4. Return index of that character by scanning the string again.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) -> At most 26 lowercase letters stored.
     */
    fun firstUniqCharLinkedHashMap(s: String): Int {
        // Step 1: LinkedHashMap in Kotlin
        val freqMap = LinkedHashMap<Char, Int>()

        // Step 2: Count frequencies
        for (c in s) {
            freqMap[c] = freqMap.getOrDefault(c, 0) + 1
        }

        // Step 3: Find first character with frequency == 1
        for ((ch, count) in freqMap) {
            if (count == 1) {
                // Step 4: Return its index
                return s.indexOf(ch)
            }
        }
        return -1
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _387FirstUniqueCharacterInAString()

            val s1 = "leetcode"
            println("Input: $s1 -> Output: ${solver.firstUniqChar(s1)} (Expected: 0)")
            println("Input: $s1 -> Output: ${solver.firstUniqCharLinkedHashMap(s1)} (Expected: 0)")

            val s2 = "loveleetcode"
            println("Input: $s2 -> Output: ${solver.firstUniqChar(s2)} (Expected: 2)")
            println("Input: $s2 -> Output: ${solver.firstUniqCharLinkedHashMap(s2)} (Expected: 2)")

            val s3 = "aabb"
            println("Input: $s3 -> Output: ${solver.firstUniqChar(s3)} (Expected: -1)")
            println("Input: $s3 -> Output: ${solver.firstUniqCharLinkedHashMap(s3)} (Expected: -1)")

            val s4 = "z"
            println("Input: $s4 -> Output: ${solver.firstUniqChar(s4)} (Expected: 0)")
            println("Input: $s4 -> Output: ${solver.firstUniqCharLinkedHashMap(s4)} (Expected: 0)")
        }
    }
}