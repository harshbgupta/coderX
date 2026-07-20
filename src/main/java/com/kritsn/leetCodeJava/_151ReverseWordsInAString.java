package com.kritsn.leetCodeJava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 */
public class _151ReverseWordsInAString {

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
    String reverseWords(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+")); // Step 1 & 2: trim and split
        Collections.reverse(words);                                 // Step 3: reverse the list
        return words.stream().collect(Collectors.joining(" "));     // Step 4: join with a single space
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _151ReverseWordsInAString solver = new _151ReverseWordsInAString();

        String s1 = "the sky is blue";
        System.out.println("Test Case 1: Input = \"" + s1 + "\" -> Output = \"" + solver.reverseWords(s1) + "\""); // Expected: "blue is sky the"

        String s2 = "  hello world  ";
        System.out.println("Test Case 2: Input = \"" + s2 + "\" -> Output = \"" + solver.reverseWords(s2) + "\""); // Expected: "world hello"

        String s3 = "a good   example";
        System.out.println("Test Case 3: Input = \"" + s3 + "\" -> Output = \"" + solver.reverseWords(s3) + "\""); // Expected: "example good a"

        String s4 = "  Bob    Loves  Alice   ";
        System.out.println("Test Case 4: Input = \"" + s4 + "\" -> Output = \"" + solver.reverseWords(s4) + "\""); // Expected: "Alice Loves Bob"

        String s5 = "Alice does not even like bob";
        System.out.println("Test Case 5: Input = \"" + s5 + "\" -> Output = \"" + solver.reverseWords(s5) + "\""); // Expected: "bob like even not does Alice"
    }
}
