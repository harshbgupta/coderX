package com.kritsn.leetCodeJava.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 224. Basic Calculator
 * <p>
 * Given a string expression with digits, '+', '-', '(', ')', and spaces,
 * evaluate it and return the result.
 */
public class _224BasicCalculator {

    /**
     * 🧠 Algorithm & Approach:
     * - Use a stack to store results and signs before '('
     * - Use a `sign` variable to apply + or - to numbers
     * - Loop through characters and:
     * - Build numbers from digits
     * - Update result using sign
     * - Handle '(' by pushing current state
     * - Handle ')' by popping and combining
     * <p>
     * Time Complexity: O(n) — each character processed once
     * Space Complexity: O(n) — for the stack
     */
    int calculate(String s) {
        int result = 0; // final result
        int number = 0; // current number being built
        int sign = 1;   // current sign (+1 or -1)
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // Build the number
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                // Apply the previous number with its sign
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // Push current result and sign to stack
                stack.addLast(result);
                stack.addLast(sign);

                // Reset result and sign for new sub-expression
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Apply any remaining number
                result += sign * number;
                number = 0;

                // Pop sign and result from before '('
                result *= stack.removeLast(); // pop sign
                result += stack.removeLast(); // pop previous result
            }
            // c == ' ' -> Ignore spaces
        }

        // Add any remaining number after loop
        result += sign * number;
        return result;
    }

    public static void main(String[] args) {
        _224BasicCalculator calculator = new _224BasicCalculator();

        String test1 = "1 + 1";
        System.out.println("Test 1: Expected = 2, Output = " + calculator.calculate(test1));

        String test2 = " 2-1 + 2 ";
        System.out.println("Test 2: Expected = 3, Output = " + calculator.calculate(test2));

        String test3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Test 3: Expected = 23, Output = " + calculator.calculate(test3));

        String test4 = "2147483647";
        System.out.println("Test 4: Expected = 2147483647, Output = " + calculator.calculate(test4));

        String test5 = "- (3 + (4 + 5))";
        System.out.println("Test 5: Expected = -12, Output = " + calculator.calculate(test5));

        String test6 = "10 - (2 + 3) + 4";
        System.out.println("Test 6: Expected = 9, Output = " + calculator.calculate(test6));
    }
}
