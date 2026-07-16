package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 13, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D, and M.
 *
 * Given an integer, convert it to a Roman numeral.
 *
 * Constraints: 1 <= num <= 3999
 */
class _012IntegerToRoman {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Subtraction Strategy:
    // Start from the highest Roman value and subtract while possible,
    // appending the corresponding symbol to the result.
    //
    // 🪜 Steps:
    // 1. Define a list of Roman symbols and values from largest to smallest.
    // 2. Loop through the values:
    //    - While num >= value, subtract and add the symbol to result.
    // 3. Return the final result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(1) — Number of operations is constant for max 3999.
    // Space Complexity: O(1) — Output size is bounded.
    ///////////////////////////////////////////////////////////////////////////
    fun intToRoman(num: Int): String {
        val values = listOf(
            1000 to "M", 900 to "CM",
            500 to "D", 400 to "CD",
            100 to "C", 90 to "XC",
            50 to "L", 40 to "XL",
            10 to "X", 9 to "IX",
            5 to "V", 4 to "IV",
            1 to "I"
        )

        var n = num
        val sb = StringBuilder()

        for ((value, symbol) in values) {
            while (n >= value) {
                sb.append(symbol)  // Append Roman symbol to result
                n -= value         // Subtract value from remaining number
            }
        }

        return sb.toString()
    }

    companion object {
        // 🔍 Main method with clearly labeled test cases
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _012IntegerToRoman()

            val n1 = 3
            println("Test Case 1: Integer = $n1 -> Roman = ${solver.intToRoman(n1)}") // Expected: III

            val n2 = 4
            println("Test Case 2: Integer = $n2 -> Roman = ${solver.intToRoman(n2)}") // Expected: IV

            val n3 = 9
            println("Test Case 3: Integer = $n3 -> Roman = ${solver.intToRoman(n3)}") // Expected: IX

            val n4 = 58
            println("Test Case 4: Integer = $n4 -> Roman = ${solver.intToRoman(n4)}") // Expected: LVIII

            val n5 = 1994
            println("Test Case 5: Integer = $n5 -> Roman = ${solver.intToRoman(n5)}") // Expected: MCMXCIV
        }
    }

}

