package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 13, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 */
class _014LongestCommonPrefix {

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
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""

        var prefix = strs[0] // Start with the first string as our initial prefix

        // Iterate through the remaining strings
        for (i in 1 until strs.size) {
            val current = strs[i]

            // While current string doesn't start with the prefix, trim the prefix
            while (!current.startsWith(prefix)) {
                if (prefix.isEmpty()) return "" // No common prefix possible
                prefix = prefix.dropLast(1)     // Remove last character
            }
        }
        return prefix // Longest common prefix after processing all strings
    }

    companion object{

        @JvmStatic
        // 🔍 Main method with clearly labeled test cases
        fun main(args: Array<String>) {
            val solver = _014LongestCommonPrefix()

            val case1 = arrayOf("flower", "flow", "flight")
            println("Test Case 1: Input = ${case1.contentToString()}, Output = \"${solver.longestCommonPrefix(case1)}\"") // Expected: "fl"

            val case2 = arrayOf("dog", "racecar", "car")
            println("Test Case 2: Input = ${case2.contentToString()}, Output = \"${solver.longestCommonPrefix(case2)}\"") // Expected: ""

            val case3 = arrayOf("interspecies", "interstellar", "interstate")
            println("Test Case 3: Input = ${case3.contentToString()}, Output = \"${solver.longestCommonPrefix(case3)}\"") // Expected: "inters"

            val case4 = arrayOf("a")
            println("Test Case 4: Input = ${case4.contentToString()}, Output = \"${solver.longestCommonPrefix(case4)}\"") // Expected: "a"

            val case5 = arrayOf("","")
            println("Test Case 5: Input = ${case5.contentToString()}, Output = \"${solver.longestCommonPrefix(case5)}\"") // Expected: ""
        }

    }
}
