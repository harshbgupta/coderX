package com.vertical.leetcode.arrays

import toJsonString

fun main(args: Array<String>) {
    println("Result: " + toJsonString(validParenthesis2("({{{{}}}))")))
}


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