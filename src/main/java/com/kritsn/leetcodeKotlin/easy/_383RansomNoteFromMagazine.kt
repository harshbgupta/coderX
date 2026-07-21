package com.kritsn.leetcodeKotlin.easy
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Problem: Given two strings ransomNote and magazine, return true if ransomNote can be constructed from magazine.
 * Each letter in magazine can only be used once.
 */

class _383RansomNoteFromMagazine {

    /**
     * 🧠 Algorithm & Explanation:
     * -----------------------------------------
     * Goal: Check if the ransomNote can be built using characters from magazine.
     * Each letter in magazine can be used only once.
     *
     * 🔍 Approach:
     * 1. Create a frequency counter (array of size 26) to count occurrences of each character in magazine.
     * 2. Traverse the ransomNote string:
     *    - For each character, decrement its count in the frequency array.
     *    - If at any point the count goes below 0, return false (not enough letters).
     * 3. If all characters in ransomNote are found with sufficient count, return true.
     *
     * ✅ This approach avoids using maps and leverages fixed space for better performance.
     *
     * Time Complexity: O(m + n), where m = length of ransomNote and n = length of magazine
     * Space Complexity: O(1), because the size of the character set (a-z) is fixed
     */
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        // Frequency map for characters in magazine
        val magazineCount = IntArray(26)

        // Populate the frequency map with magazine letters
        for (char in magazine) {
            // Increment count for this character
            magazineCount[char - 'a']++ //
        }

        // Check each character in ransomNote
        for (char in ransomNote) {
            // Decrement count as we use a character
            magazineCount[char - 'a']--

            // If count goes below 0, magazine doesn't have enough of this character
            if (magazineCount[char - 'a'] < 0) return false
        }

        // All characters were successfully matched
        return true
    }
}

fun main() {
    val solution = _383RansomNoteFromMagazine()

    // Test Case 1: Should return true
    val ransomNote1 = "a"
    val magazine1 = "bca"
    println("Can construct '$ransomNote1' from '$magazine1'? -> ${solution.canConstruct(ransomNote1, magazine1)}") // true

    // Test Case 2: Should return false
    val ransomNote2 = "aa"
    val magazine2 = "ab"
    println("Can construct '$ransomNote2' from '$magazine2'? -> ${solution.canConstruct(ransomNote2, magazine2)}") // false

    // Test Case 3: Should return true
    val ransomNote3 = "abc"
    val magazine3 = "cbade"
    println("Can construct '$ransomNote3' from '$magazine3'? -> ${solution.canConstruct(ransomNote3, magazine3)}") // true

    // Test Case 4: Should return false (not enough c's)
    val ransomNote4 = "aabbcc"
    val magazine4 = "aabbbc"
    println("Can construct '$ransomNote4' from '$magazine4'? -> ${solution.canConstruct(ransomNote4, magazine4)}") // false
}
