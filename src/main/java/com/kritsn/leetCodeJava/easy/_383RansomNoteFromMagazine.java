package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Problem: Given two strings ransomNote and magazine, return true if ransomNote can be constructed from magazine.
 * Each letter in magazine can only be used once.
 */
public class _383RansomNoteFromMagazine {

    /**
     * 🧠 Algorithm & Explanation:
     * -----------------------------------------
     * Goal: Check if the ransomNote can be built using characters from magazine.
     * Each letter in magazine can be used only once.
     * <p>
     * 🔍 Approach:
     * 1. Create a frequency counter (array of size 26) to count occurrences of each character in magazine.
     * 2. Traverse the ransomNote string:
     * - For each character, decrement its count in the frequency array.
     * - If at any point the count goes below 0, return false (not enough letters).
     * 3. If all characters in ransomNote are found with sufficient count, return true.
     * <p>
     * ✅ This approach avoids using maps and leverages fixed space for better performance.
     * <p>
     * Time Complexity: O(m + n), where m = length of ransomNote and n = length of magazine
     * Space Complexity: O(1), because the size of the character set (a-z) is fixed
     */
    boolean canConstruct(String ransomNote, String magazine) {
        // Frequency map for characters in magazine
        int[] magazineCount = new int[26];

        // Populate the frequency map with magazine letters
        for (char c : magazine.toCharArray()) {
            // Increment count for this character
            magazineCount[c - 'a']++;
        }

        // Check each character in ransomNote
        for (char c : ransomNote.toCharArray()) {
            // Decrement count as we use a character
            magazineCount[c - 'a']--;

            // If count goes below 0, magazine doesn't have enough of this character
            if (magazineCount[c - 'a'] < 0) return false;
        }

        // All characters were successfully matched
        return true;
    }

    public static void main(String[] args) {
        _383RansomNoteFromMagazine solution = new _383RansomNoteFromMagazine();

        // Test Case 1: Should return true
        String ransomNote1 = "a";
        String magazine1 = "bca";
        System.out.println("Can construct '" + ransomNote1 + "' from '" + magazine1 + "'? -> " + solution.canConstruct(ransomNote1, magazine1)); // true

        // Test Case 2: Should return false
        String ransomNote2 = "aa";
        String magazine2 = "ab";
        System.out.println("Can construct '" + ransomNote2 + "' from '" + magazine2 + "'? -> " + solution.canConstruct(ransomNote2, magazine2)); // false

        // Test Case 3: Should return true
        String ransomNote3 = "abc";
        String magazine3 = "cbade";
        System.out.println("Can construct '" + ransomNote3 + "' from '" + magazine3 + "'? -> " + solution.canConstruct(ransomNote3, magazine3)); // true

        // Test Case 4: Should return false (not enough c's)
        String ransomNote4 = "aabbcc";
        String magazine4 = "aabbbc";
        System.out.println("Can construct '" + ransomNote4 + "' from '" + magazine4 + "'? -> " + solution.canConstruct(ransomNote4, magazine4)); // false
    }
}
