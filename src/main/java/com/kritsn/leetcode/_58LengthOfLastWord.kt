package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 13, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 *
 * A word is a maximal substring consisting of non-space characters only.
 */
class _058LengthOfLastWord {

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
    fun lengthOfLastWord(s: String): Int {
        var i = s.length - 1             // Step 1: Start from end of string
        var length = 0                   // To hold length of last word

        // Step 2: Skip all trailing spaces
        while (i >= 0 && s[i] == ' ') {
            i--
        }

        // Step 3: Count non-space characters until next space or start of string
        while (i >= 0 && s[i] != ' ') {
            length++
            i--
        }

        return length                    // Step 4: Return the final count
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _058LengthOfLastWord()

    val s1 = "Hello World"
    println("Test Case 1: Input = \"$s1\", Output = ${solver.lengthOfLastWord(s1)}") // Expected: 5

    val s2 = "   fly me   to   the moon  "
    println("Test Case 2: Input = \"$s2\", Output = ${solver.lengthOfLastWord(s2)}") // Expected: 4

    val s3 = "luffy is still joyboy"
    println("Test Case 3: Input = \"$s3\", Output = ${solver.lengthOfLastWord(s3)}") // Expected: 6

    val s4 = "singleword"
    println("Test Case 4: Input = \"$s4\", Output = ${solver.lengthOfLastWord(s4)}") // Expected: 10

    val s5 = "a "
    println("Test Case 5: Input = \"$s5\", Output = ${solver.lengthOfLastWord(s5)}") // Expected: 1
}
