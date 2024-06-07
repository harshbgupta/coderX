package com.vertical.leetcode.arrays

import toJsonString

fun main(args: Array<String>) {
    toJsonString(lengthOfLongestSubstring("pwwkew"))
}

/**
 * Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 */
private fun lengthOfLongestSubstring(s: String): Int {
    var ans = ""
    var tempAns = ""
    s.forEachIndexed { index, letter ->
        val duplicateCharIndex = tempAns.indexOf(letter)
        if (duplicateCharIndex == -1) {
            tempAns += letter
            if (tempAns.length > ans.length) {
                ans = tempAns
            }
        } else {
            tempAns = tempAns.substring(duplicateCharIndex + 1, tempAns.length) + letter
        }
    }
//    println("ans $ans")
    return ans.length
}