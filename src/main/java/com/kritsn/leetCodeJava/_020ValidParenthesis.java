package com.kritsn.leetCodeJava;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 15, 2026
 */
/**
 * Valid Parentheses
 *
 * Given a strings containing just the characters '('`,')'`,'{'`,'}'`,'['and']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */
public class _020ValidParenthesis {
    public boolean validParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if(i>0 && (c == ')' && stack.peek() == '(')
                || (c == ']' && stack.peek() == '[') || ( c== '}' && stack.peek() == '{')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Optimised approach using a map for bracket matching
    public boolean validParenthesisOptimised(String s) {
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
        System.out.println("Input: '" + braces1 + "', Output: " + solver.validParentheses(braces1));
        // Expected: true
        
        String braces2 = "([)]";
        System.out.println("Input: '" + braces2 + "', Output: " + solver.validParentheses(braces2));
        // Expected: false
        
        String braces3 = "{[]}";
        System.out.println("Input: '" + braces3 + "', Output: " + solver.validParentheses(braces3));
        // Expected: true
        
        String braces4 = "({{{{}}}))" ;
        System.out.println("Input: '" + braces4 + "', Output: " + solver.validParentheses(braces4));
        // Expected: false
    }
}
