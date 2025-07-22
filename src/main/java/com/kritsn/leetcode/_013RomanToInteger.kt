package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 13, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Given a Roman numeral, convert it to an integer.
 */
class _013RomanToInteger {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Left-to-Right Scan:
    // We traverse the string from left to right.
    // - If current symbol < next symbol: subtract its value.
    // - Else: add its value.
    //
    // 🪜 Steps:
    // 1. Create a map of Roman symbol to integer value.
    // 2. Loop through each character:
    //    - If current < next → subtract
    //    - Else → add
    // 3. Return the final result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — where n is the length of the Roman string.
    // Space Complexity: O(1) — fixed map of 7 Roman symbols.
    ///////////////////////////////////////////////////////////////////////////
    fun romanToInt(s: String): Int {
        val romanMap = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )

        var result = 0

        for (i in s.indices) {
            val currentVal = romanMap[s[i]] ?: 0
            val nextVal = if (i + 1 < s.length) romanMap[s[i + 1]] ?: 0 else 0

            // If the current value is less than next, subtract it
            if (currentVal < nextVal) {
                result -= currentVal
            } else {
                result += currentVal
            }
        }

        return result
    }

    companion object{

        // 🔍 Main method with clearly labeled test cases
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _013RomanToInteger()

            val s1 = "III"
            println("Test Case 1: Roman = \"$s1\" -> Integer = ${solver.romanToInt(s1)}") // Expected: 3

            val s2 = "IV"
            println("Test Case 2: Roman = \"$s2\" -> Integer = ${solver.romanToInt(s2)}") // Expected: 4

            val s3 = "IX"
            println("Test Case 3: Roman = \"$s3\" -> Integer = ${solver.romanToInt(s3)}") // Expected: 9

            val s4 = "LVIII"
            println("Test Case 4: Roman = \"$s4\" -> Integer = ${solver.romanToInt(s4)}") // Expected: 58

            val s5 = "MCMXCIV"
            println("Test Case 5: Roman = \"$s5\" -> Integer = ${solver.romanToInt(s5)}") // Expected: 1994
        }

    }
}
