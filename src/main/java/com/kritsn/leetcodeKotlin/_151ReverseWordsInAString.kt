package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 */
class _151ReverseWordsInString {

    ///////////////////////////////////////////////////////////////////////////
    // Clean Split + Reverse Join Approach:
    //
    // We clean the input string and:
    // - Split it into words using a regex for multiple spaces.
    // - Remove any empty tokens that may exist.
    // - Reverse the list of words.
    // - Join them with a single space.
    //
    // 🪜 Steps:
    // 1. Trim the string to remove leading/trailing spaces.
    // 2. Split the string using regex "\\s+" which captures one or more spaces.
    // 3. Reverse the list of words.
    // 4. Join the words with a single space.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — where n is the length of the string.
    // Space Complexity: O(n) — for storing the split words and building the result.
    ///////////////////////////////////////////////////////////////////////////
    fun reverseWords(s: String): String {
        return s.trim()                                // Step 1: Trim leading/trailing spaces
            .split("\\s+".toRegex())                   // Step 2: Split by one or more spaces
            .reversed()                                // Step 3: Reverse the list
            .joinToString(" ")                         // Step 4: Join with a single space
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _151ReverseWordsInString()

    val s1 = "the sky is blue"
    println("Test Case 1: Input = \"$s1\" -> Output = \"${solver.reverseWords(s1)}\"") // Expected: "blue is sky the"

    val s2 = "  hello world  "
    println("Test Case 2: Input = \"$s2\" -> Output = \"${solver.reverseWords(s2)}\"") // Expected: "world hello"

    val s3 = "a good   example"
    println("Test Case 3: Input = \"$s3\" -> Output = \"${solver.reverseWords(s3)}\"") // Expected: "example good a"

    val s4 = "  Bob    Loves  Alice   "
    println("Test Case 4: Input = \"$s4\" -> Output = \"${solver.reverseWords(s4)}\"") // Expected: "Alice Loves Bob"

    val s5 = "Alice does not even like bob"
    println("Test Case 5: Input = \"$s5\" -> Output = \"${solver.reverseWords(s5)}\"") // Expected: "bob like even not does Alice"
}
