package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 19, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 224. Basic Calculator
 *
 * Given a string expression with digits, '+', '-', '(', ')', and spaces,
 * evaluate it and return the result.
 */

class _224BasicCalculator {

    /**
     * 🧠 Algorithm & Approach:
     * - Use a stack to store results and signs before '('
     * - Use a `sign` variable to apply + or - to numbers
     * - Loop through characters and:
     *     - Build numbers from digits
     *     - Update result using sign
     *     - Handle '(' by pushing current state
     *     - Handle ')' by popping and combining
     *
     * Time Complexity: O(n) — each character processed once
     * Space Complexity: O(n) — for the stack
     */
    fun calculate(s: String): Int {
        var result = 0  // final result
        var number = 0  // current number being built
        var sign = 1    // current sign (+1 or -1)
        val stack = ArrayDeque<Int>()

        for (i in s.indices) {
            val c = s[i]

            when {
                c.isDigit() -> {
                    // Build the number
                    number = number * 10 + (c - '0')
                }

                c == '+' -> {
                    // Apply the previous number with its sign
                    result += sign * number
                    number = 0
                    sign = 1
                }

                c == '-' -> {
                    result += sign * number
                    number = 0
                    sign = -1
                }

                c == '(' -> {
                    // Push current result and sign to stack
                    stack.addLast(result)
                    stack.addLast(sign)

                    // Reset result and sign for new sub-expression
                    result = 0
                    sign = 1
                }

                c == ')' -> {
                    // Apply any remaining number
                    result += sign * number
                    number = 0

                    // Pop sign and result from before '('
                    result *= stack.removeLast() // pop sign
                    result += stack.removeLast() // pop previous result
                }

                c == ' ' -> {
                    // Ignore spaces
                }
            }
        }

        // Add any remaining number after loop
        result += sign * number
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val calculator = _224BasicCalculator()

            val test1 = "1 + 1"
            println("Test 1: Expected = 2, Output = ${calculator.calculate(test1)}")

            val test2 = " 2-1 + 2 "
            println("Test 2: Expected = 3, Output = ${calculator.calculate(test2)}")

            val test3 = "(1+(4+5+2)-3)+(6+8)"
            println("Test 3: Expected = 23, Output = ${calculator.calculate(test3)}")

            val test4 = "2147483647"
            println("Test 4: Expected = 2147483647, Output = ${calculator.calculate(test4)}")

            val test5 = "- (3 + (4 + 5))"
            println("Test 5: Expected = -12, Output = ${calculator.calculate(test5)}")

            val test6 = "10 - (2 + 3) + 4"
            println("Test 6: Expected = 9, Output = ${calculator.calculate(test6)}")
        }
    }
}
