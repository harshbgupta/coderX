package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 15, 2026
 */
/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 */
public class _014LongestCommonPrefix {

    ///////////////////////////////////////////////////////////////////////////
    // Horizontal Scanning Approach:
    //
    // We take the first string as the initial prefix and compare it with each
    // subsequent string. While the current string doesn't start with the prefix,
    // we trim the prefix by one character from the end. This continues until the
    // prefix matches or becomes empty.
    //
    // 🪜 Steps:
    // 1. Handle edge case of an empty array -> return "".
    // 2. Initialize `prefix` with the first string.
    // 3. For every other string in the array:
    //    a. While that string does NOT start with `prefix`, remove the last
    //       character from `prefix`.
    //    b. If `prefix` becomes empty, break early and return "".
    // 4. Return `prefix` as the longest common prefix once all strings processed.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(N * L) — where N = number of strings, L = length of
    // the shortest string. In the worst case we may compare each character once.
    // Space Complexity: O(1) — only a few extra variables are used.
    ///////////////////////////////////////////////////////////////////////////

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String cur = strs[i];
            while (!cur.startsWith(prefix)) {
                if (prefix.isEmpty()) {
                    return "";
                }
                prefix =  prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
    public static void main(String[] args) {
        _014LongestCommonPrefix solver = new _014LongestCommonPrefix();

        // Test Case 1: Common prefix "fl"
        String[] case1 = {"flower", "flow", "flight"};
        System.out.println("Test Case 1: Input = " + java.util.Arrays.toString(case1)
                + ", Output = \"" + solver.longestCommonPrefix(case1) + "\"");
        // Expected: "fl"

        // Test Case 2: No common prefix
        String[] case2 = {"dog", "racecar", "car"};
        System.out.println("Test Case 2: Input = " + java.util.Arrays.toString(case2)
                + ", Output = \"" + solver.longestCommonPrefix(case2) + "\"");
        // Expected: ""

        // Test Case 3: Common prefix "inters"
        String[] case3 = {"interspecies", "interstellar", "interstate"};
        System.out.println("Test Case 3: Input = " + java.util.Arrays.toString(case3)
                + ", Output = \"" + solver.longestCommonPrefix(case3) + "\"");
        // Expected: "inters"

        // Test Case 4: Single element
        String[] case4 = {"a"};
        System.out.println("Test Case 4: Input = " + java.util.Arrays.toString(case4)
                + ", Output = \"" + solver.longestCommonPrefix(case4) + "\"");
        // Expected: "a"

        // Test Case 5: Empty strings
        String[] case5 = {"", ""};
        System.out.println("Test Case 5: Input = " + java.util.Arrays.toString(case5)
                + ", Output = \"" + solver.longestCommonPrefix(case5) + "\"");
        // Expected: ""
    }
}
