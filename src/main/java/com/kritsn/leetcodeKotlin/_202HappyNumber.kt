package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/*
    Leetcode 202: Happy Number

    Write an algorithm to determine if a number n is a happy number.

    A happy number is a number defined by the following process:
    Starting with any positive integer, replace the number by the sum of the squares of its digits.
    Repeat the process until the number equals 1 (where it will stay),
    or it loops endlessly in a cycle which does not include 1.
    Return true if n is a happy number, and false if not.
*/

class _202HappyNumber {

    /**
     * 🧠 Algorithm & Approach:
     *
     * 1. Create a HashSet to track seen numbers during the transformation process.
     * 2. Continuously replace the number with the sum of the squares of its digits.
     * 3. If we reach 1, return true (it's a happy number).
     * 4. If we see a number we've seen before, a cycle is detected — return false.
     *
     * Time Complexity: O(log n), in practice — depends on how quickly the sequence converges or loops.
     * Space Complexity: O(log n), for the set of seen numbers.
     */
    fun isHappy(n: Int): Boolean {
        var num = n
        val seen = HashSet<Int>()

        while (num != 1 && !seen.contains(num)) {
            seen.add(num)
            num = getDigitSquareSum(num)
        }

        return num == 1
    }

    // Helper function to calculate the sum of squares of digits of a number
    private fun getDigitSquareSum(num: Int): Int {
        var sum = 0
        var current = num
        while (current > 0) {
            val digit = current % 10
            sum += digit * digit
            current /= 10
        }
        return sum
    }
}

// 🧪 Main method with test cases
fun main() {
    val solution = _202HappyNumber()

    println("Test Case 1:")
    println("Input: 19")
    println("Output: ${solution.isHappy(19)}") // Expected: true

    println("\nTest Case 2:")
    println("Input: 2")
    println("Output: ${solution.isHappy(2)}") // Expected: false

    println("\nTest Case 3:")
    println("Input: 7")
    println("Output: ${solution.isHappy(7)}") // Expected: true

    println("\nTest Case 4:")
    println("Input: 4")
    println("Output: ${solution.isHappy(4)}") // Expected: false
}
