package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/**
Problem: Given two strings s and t, determine if they are isomorphic.
An isomorphic transformation is one where each character from s can be mapped uniquely to a character in t, such that replacing s[i] with its mapped value forms t[i].

Constraints:
- All occurrences of a character must be replaced with another character while preserving the order.
- No two characters may map to the same character.

Example:
Input: s = "egg", t = "add" → Output: true
Input: s = "foo", t = "bar" → Output: false
*/
class _205IsomorphicStrings {

    /**
     * 🧠 Algorithm & Approach:
     * - Use two hash maps:
     *     - sourceToTargetMap: Maps each character from s to its corresponding character in t
     *     - targetToSourceMap: Maps each character from t to its corresponding character in s
     * - Iterate through each character in s and t:
     *     - If mapping exists in sToT, check it maps to current character in t
     *     - If mapping exists in tToS, check it maps to current character in s
     *     - If not, establish new mappings in both hash maps
     * - If inconsistency found, return false
     * - Else, return true at the end
     *
     * Time Complexity: O(n) where n is the length of s (or t)
     * Space Complexity: O(1) since the hash maps will store at most 256 characters
     */
    fun isIsomorphic(s: String, t: String): Boolean {
        // Maps to keep character mappings between s -> t and t -> s
        val sourceToTargetMap = HashMap<Char, Char>()
        val targetToSourceMap = HashMap<Char, Char>()

        for (i in s.indices) {
            val chS = s[i]
            val chT = t[i]

            // Check if mapping already exists in sourceToTargetMap and it is consistent
            if (sourceToTargetMap.containsKey(chS)) {
                if (sourceToTargetMap[chS] != chT) return false
            } else {
                sourceToTargetMap[chS] = chT
            }

            // Check if mapping already exists in targetToSourceMap and it is consistent
            if (targetToSourceMap.containsKey(chT)) {
                if (targetToSourceMap[chT] != chS) return false
            } else {
                targetToSourceMap[chT] = chS
            }
        }

        // All characters mapped correctly and consistently
        return true
    }
}

fun main() {
    val solution = _205IsomorphicStrings()

    // Test case 1
    val s1 = "egg"
    val t1 = "add"
    println("Input: s = \"$s1\", t = \"$t1\" → Output: ${solution.isIsomorphic(s1, t1)} (Expected: true)")

    // Test case 2
    val s2 = "foo"
    val t2 = "bar"
    println("Input: s = \"$s2\", t = \"$t2\" → Output: ${solution.isIsomorphic(s2, t2)} (Expected: false)")

    // Test case 3
    val s3 = "paper"
    val t3 = "title"
    println("Input: s = \"$s3\", t = \"$t3\" → Output: ${solution.isIsomorphic(s3, t3)} (Expected: true)")

    // Test case 4
    val s4 = "ab"
    val t4 = "aa"
    println("Input: s = \"$s4\", t = \"$t4\" → Output: ${solution.isIsomorphic(s4, t4)} (Expected: false)")
}
