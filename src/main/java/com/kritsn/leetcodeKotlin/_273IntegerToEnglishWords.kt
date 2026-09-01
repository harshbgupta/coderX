package com.kritsn.leetcodeKotlin

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 17, 2025
 */

class _273IntegerToEnglishWords {

    val belowTwenty = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen")

    val tens = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

    val thousands = arrayOf("", "Thousand", "Million", "Billion")
    fun numberToWords(num: Int): String{
        // Edge case: exact zero
        if (num == 0) return "Zero"

        var number = num            // mutable copy for chunk processing
        var i = 0                   // index into 'thousands'
        var words = ""              // progressively composed sentence

        // Process last 3 digits repeatedly
        while (number > 0) {
            val chunk = number % 1000   // current 3-digit group
            if (chunk != 0) {
                val part = helper(chunk) // e.g., "One Hundred Twenty Three "
                // Prepend scale word and previously built sentence with proper spacing
                words = part + thousands[i] + if (words.isEmpty()) "" else " $words"
            }
            number /= 1000   // drop processed 3 digits
            i++              // move to next scale
        }

        return words.trim()  // tidy any trailing spaces

    }

    // Helper to convert numbers < 1000
    private fun helper(num: Int): String {
        return when {
            num == 0 -> ""
            num < 20 -> belowTwenty[num] + " "
            num < 100 -> tens[num / 10] + " " + helper(num % 10)
            else -> belowTwenty[num / 100] + " Hundred " + helper(num % 100)
        }
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val converter = _273IntegerToEnglishWords()

            // ---- Test Case 1 ----
            val num1 = 123
            println("Input: $num1")
//            println("Output: ${converter.numberToWords(num1)}")
            println("Expected: One Hundred Twenty Three\n")

            // ---- Test Case 2 ----
            val num2 = 12345
            println("Input: $num2")
            println("Output: ${converter.numberToWords(num2)}")
            println("Expected: Twelve Thousand Three Hundred Forty Five\n")

            // ---- Test Case 3 ----
            val num3 = 1234567
            println("Input: $num3")
//            println("Output: ${converter.numberToWords(num3)}")
            println("Expected: One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven\n")

            // ---- Test Case 4 ----
            val num4 = 0
            println("Input: $num4")
//            println("Output: ${converter.numberToWords(num4)}")
            println("Expected: Zero\n")

            // ---- Additional Edge / Large ----
            val num5 = Int.MAX_VALUE // 2147483647
            println("Input: $num5")
//            println("Output: ${converter.numberToWords(num5)}")
            println("Expected: Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven\n")

        }
    }
}