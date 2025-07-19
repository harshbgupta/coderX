package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 19, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 150. Evaluate Reverse Polish Notation
 * Given an array of strings tokens representing RPN expression,
 * evaluate it and return the result as an integer.
 */

class _150EvaluateReversePolishNotation {

    /**
     * 🧠 Algorithm & Approach:
     * - We use a stack to keep intermediate results.
     * - For each token:
     *     - If it's a number, push to stack.
     *     - If it's an operator, pop two elements, apply the operation, and push result.
     * - Return the final result at the top of the stack.
     *
     * Time Complexity: O(n), where n = number of tokens
     * Space Complexity: O(n), for the stack
     */
    fun evalRPN(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()

        for (token in tokens) {
            when (token) {
                "+" -> {
                    val b = stack.removeLast()
                    val a = stack.removeLast()
                    stack.addLast(a + b)
                }
                "-" -> {
                    val b = stack.removeLast()
                    val a = stack.removeLast()
                    stack.addLast(a - b)
                }
                "*" -> {
                    val b = stack.removeLast()
                    val a = stack.removeLast()
                    stack.addLast(a * b)
                }
                "/" -> {
                    val b = stack.removeLast()
                    val a = stack.removeLast()
                    stack.addLast(a / b) // truncates toward 0 by default in Kotlin
                }
                else -> {
                    // Token is a number, convert and push to stack
                    stack.addLast(token.toInt())
                }
            }
        }

        return stack.last() // Final result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val evaluator = _150EvaluateReversePolishNotation()

            val test1 = arrayOf("2", "1", "+", "3", "*")
            println("Test 1: Expected = 9, Output = ${evaluator.evalRPN(test1)}") // (2 + 1) * 3 = 9

            val test2 = arrayOf("4", "13", "5", "/", "+")
            println("Test 2: Expected = 6, Output = ${evaluator.evalRPN(test2)}") // 4 + (13 / 5) = 6

            val test3 = arrayOf("10", "6", "9", "3", "/", "-", "*")
            println("Test 3: Expected = 60, Output = ${evaluator.evalRPN(test3)}") // 10 * (6 - (9 / 3)) = 60

            val test4 = arrayOf("3", "-4", "+")
            println("Test 4: Expected = -1, Output = ${evaluator.evalRPN(test4)}") // 3 + (-4) = -1

            val test5 = arrayOf("7", "3", "/")
            println("Test 5: Expected = 2, Output = ${evaluator.evalRPN(test5)}") // 7 / 3 = 2 (truncated)

            val test6 = arrayOf("7", "-3", "/")
            println("Test 6: Expected = -2, Output = ${evaluator.evalRPN(test6)}") // 7 / -3 = -2 (truncated)
        }
    }
}
