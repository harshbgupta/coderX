package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * <p>
 * A word is a maximal substring consisting of non-space characters only.
 */
public class _058LengthOfLastWord {

    ///////////////////////////////////////////////////////////////////////////
    // Reverse Traversal Approach:
    //
    // We start from the end of the string and skip any trailing spaces.
    // Then, we count the length of the last sequence of non-space characters.
    //
    // 🪜 Steps:
    // 1. Initialize an index pointing to the last character of the string.
    // 2. Move backwards while the character is a space.
    // 3. Once a non-space is found, begin counting until we hit a space again.
    // 4. Return the count.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — worst case we scan entire string.
    // Space Complexity: O(1) — constant extra memory used.
    ///////////////////////////////////////////////////////////////////////////
    int lengthOfLastWord(String s) {
        int i = s.length() - 1; // Step 1: Start from end of string
        int length = 0;         // To hold length of last word

        // Step 2: Skip all trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Step 3: Count non-space characters until next space or start of string
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length; // Step 4: Return the final count
    }

    public static void main(String[] args) {
        _058LengthOfLastWord solver = new _058LengthOfLastWord();

        String s1 = "Hello World";
        System.out.println("Test Case 1: Input = \"" + s1 + "\", Output = " + solver.lengthOfLastWord(s1)); // Expected: 5

        String s2 = "   fly me   to   the moon  ";
        System.out.println("Test Case 2: Input = \"" + s2 + "\", Output = " + solver.lengthOfLastWord(s2)); // Expected: 4

        String s3 = "luffy is still joyboy";
        System.out.println("Test Case 3: Input = \"" + s3 + "\", Output = " + solver.lengthOfLastWord(s3)); // Expected: 6

        String s4 = "singleword";
        System.out.println("Test Case 4: Input = \"" + s4 + "\", Output = " + solver.lengthOfLastWord(s4)); // Expected: 10

        String s5 = "a ";
        System.out.println("Test Case 5: Input = \"" + s5 + "\", Output = " + solver.lengthOfLastWord(s5)); // Expected: 1
    }
}
