package com.kritsn.leetcodeKotlin.easy
import java.util.Stack
import kotlin.text.iterator

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
class _020ValidParenthesis {
    private fun validParenthesis1(s: String): Boolean {
        var i = -1
        val stack = CharArray(s.length)
        for (ch in s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack[++i] = ch
            } else {
                if (i >= 0 &&
                    ((ch == ')' && stack[i] == '(')
                            || (ch == '}' && stack[i] == '{')
                            || (ch == ']' && stack[i] == '['))
                ) {
                    i--
                } else {
                    return false
                }
            }
        }
        return i == -1
    }

    private fun validParenthesis2(s: String): Boolean {
        var tempString = ""
        var i = -1;
        for (ch in s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                tempString += ch
                i++
            } else {
                if (i >= 0 &&
                    ((ch == ')' && tempString[i] == '(')
                            || (ch == '}' && tempString[i] == '{')
                            || (ch == ']' && tempString[i] == '['))
                ) {
                    tempString = tempString.replace(tempString[i].toString(), "")
                    i--
                } else {
                    return false
                }
            }
        }
        return tempString.isEmpty()
    }

    //TODO:*****
    private fun validParenthesisOptimised(s: String): Boolean {
        // The ArrayDeque class is a more efficient and preferred stack implementation than the legacy Stack class.
        val stack = Stack<Char>()

        // A map makes the matching logic clean and easily extensible.
        val bracketMap = mapOf(')' to '(', '}' to '{', ']' to '[')

        for (char in s) {
            // If the character is a closing bracket
            if (bracketMap.containsKey(char)) {
                // If the stack is empty or the top element doesn't match, it's invalid.
                if (stack.isEmpty() || stack.pop() != bracketMap[char]) {
                    return false
                }
            } else {
                // If it's an opening bracket, push it onto the stack.
                stack.push(char)
            }
        }

        // A valid string means all brackets have been matched, so the stack must be empty.
        return stack.isEmpty()
    }

    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _020ValidParenthesis()
            val braces1  = "()[]{}"
            val braces2  = "([)]"
            val braces3  = "{[]}"
            val braces4  = "({{{{}}}))"
            println("Input: '$braces1', Output: ${solver.validParenthesisOptimised(braces1)}")       // Expected: true
            println("Input: '$braces2', Output: ${solver.validParenthesisOptimised(braces2)}")         // Expected: false
            println("Input: '$braces3', Output: ${solver.validParenthesisOptimised(braces3)}")         // Expected: true
            println("Input: '$braces4', Output: ${solver.validParenthesisOptimised(braces4)}") // Expected: false
        }

    }
}