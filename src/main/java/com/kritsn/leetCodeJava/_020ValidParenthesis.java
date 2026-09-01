package com.kritsn.leetCodeJava;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Valid Parentheses
 * <p>
 * Given a strings containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */
public class _020ValidParenthesis {
    private boolean validParenthesis1(String s) {
        int i = -1;
        char[] stack = new char[s.length()];
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack[++i] = ch;
            } else {
                if (i >= 0 &&
                        ((ch == ')' && stack[i] == '(')
                                || (ch == '}' && stack[i] == '{')
                                || (ch == ']' && stack[i] == '['))) {
                    i--;
                } else {
                    return false;
                }
            }
        }
        return i == -1;
    }

    private boolean validParenthesis2(String s) {
        StringBuilder tempString = new StringBuilder();
        int i = -1;
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                tempString.append(ch);
                i++;
            } else {
                if (i >= 0 &&
                        ((ch == ')' && tempString.charAt(i) == '(')
                                || (ch == '}' && tempString.charAt(i) == '{')
                                || (ch == ']' && tempString.charAt(i) == '['))) {
                    tempString.deleteCharAt(i);
                    i--;
                } else {
                    return false;
                }
            }
        }
        return tempString.isEmpty();
    }

    //TODO:*****
    private boolean validParenthesisOptimised(String s) {
        // The ArrayDeque class is a more efficient and preferred stack implementation than the legacy Stack class.
        Stack<Character> stack = new Stack<>();

        // A map makes the matching logic clean and easily extensible.
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        for (char c : s.toCharArray()) {
            // If the character is a closing bracket
            if (bracketMap.containsKey(c)) {
                // If the stack is empty or the top element doesn't match, it's invalid.
                if (stack.isEmpty() || stack.pop() != bracketMap.get(c)) {
                    return false;
                }
            } else {
                // If it's an opening bracket, push it onto the stack.
                stack.push(c);
            }
        }

        // A valid string means all brackets have been matched, so the stack must be empty.
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        _020ValidParenthesis solver = new _020ValidParenthesis();
        String braces1 = "()[]{}";
        String braces2 = "([)]";
        String braces3 = "{[]}";
        String braces4 = "({{{{}}}))";
        System.out.println("Input: '" + braces1 + "', Output: " + solver.validParenthesisOptimised(braces1)); // Expected: true
        System.out.println("Input: '" + braces2 + "', Output: " + solver.validParenthesisOptimised(braces2)); // Expected: false
        System.out.println("Input: '" + braces3 + "', Output: " + solver.validParenthesisOptimised(braces3)); // Expected: true
        System.out.println("Input: '" + braces4 + "', Output: " + solver.validParenthesisOptimised(braces4)); // Expected: false
    }
}
