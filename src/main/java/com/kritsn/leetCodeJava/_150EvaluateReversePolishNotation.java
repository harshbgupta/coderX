package com.kritsn.leetCodeJava;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 150. Evaluate Reverse Polish Notation
 * Given an array of strings tokens representing RPN expression,
 * evaluate it and return the result as an integer.
 */
public class _150EvaluateReversePolishNotation {

    /**
     * 🧠 Algorithm & Approach:
     * - We use a stack to keep intermediate results.
     * - For each token:
     * - If it's a number, push to stack.
     * - If it's an operator, pop two elements, apply the operation, and push result.
     * - Return the final result at the top of the stack.
     * <p>
     * Time Complexity: O(n), where n = number of tokens
     * Space Complexity: O(n), for the stack
     */
    int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int b = stack.removeLast();
                    int a = stack.removeLast();
                    stack.addLast(a + b);
                }
                case "-" -> {
                    int b = stack.removeLast();
                    int a = stack.removeLast();
                    stack.addLast(a - b);
                }
                case "*" -> {
                    int b = stack.removeLast();
                    int a = stack.removeLast();
                    stack.addLast(a * b);
                }
                case "/" -> {
                    int b = stack.removeLast();
                    int a = stack.removeLast();
                    stack.addLast(a / b); // truncates toward 0, same as Kotlin's Int division
                }
                default -> {
                    // Token is a number, convert and push to stack
                    stack.addLast(Integer.parseInt(token));
                }
            }
        }

        return stack.getLast(); // Final result
    }

    public static void main(String[] args) {
        _150EvaluateReversePolishNotation evaluator = new _150EvaluateReversePolishNotation();

        String[] test1 = {"2", "1", "+", "3", "*"};
        System.out.println("Test 1: Expected = 9, Output = " + evaluator.evalRPN(test1)); // (2 + 1) * 3 = 9

        String[] test2 = {"4", "13", "5", "/", "+"};
        System.out.println("Test 2: Expected = 6, Output = " + evaluator.evalRPN(test2)); // 4 + (13 / 5) = 6

        String[] test3 = {"10", "6", "9", "3", "/", "-", "*"};
        System.out.println("Test 3: Expected = 60, Output = " + evaluator.evalRPN(test3)); // 10 * (6 - (9 / 3)) = 60

        String[] test4 = {"3", "-4", "+"};
        System.out.println("Test 4: Expected = -1, Output = " + evaluator.evalRPN(test4)); // 3 + (-4) = -1

        String[] test5 = {"7", "3", "/"};
        System.out.println("Test 5: Expected = 2, Output = " + evaluator.evalRPN(test5)); // 7 / 3 = 2 (truncated)

        String[] test6 = {"7", "-3", "/"};
        System.out.println("Test 6: Expected = -2, Output = " + evaluator.evalRPN(test6)); // 7 / -3 = -2 (truncated)
    }
}
