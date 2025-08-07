package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 12, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 * 1. Each child must have at least one candy.
 * 2. Children with a higher rating get more candies than their neighbors.
 *
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 */
class _135Candy {

    ///////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=IIqVFvKE6RY
    // Greedy Two-Pass Approach:
    // 1. Left-to-Right: If current child has higher rating than left neighbor,
    //    give one more candy than left neighbor.
    // 2. Right-to-Left: If current child has higher rating than right neighbor,
    //    ensure they have more candies than right neighbor.
    //
    // 🪜 Steps:
    // - Initialize candies array with all 1s.
    // - First pass: ratings[i] > ratings[i-1] → candies[i] = candies[i-1] + 1
    // - Second pass: ratings[i] > ratings[i+1] → candies[i] = max(candies[i], candies[i+1] + 1)
    // - Return total candies.
    ///////////////////////////////////////////////////////////////////////////
    fun candy(ratings: IntArray): Int {
        val n = ratings.size
        val candies = IntArray(n) { 1 }

        // Left to right pass
        for (i in 1 until n) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1
            }
        }

        // Right to left pass
        for (i in n - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = maxOf(candies[i], candies[i + 1] + 1)
            }
        }

        return candies.sum()
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _135Candy()

    // Test Case 1: Ascending ratings
    val ratings1 = intArrayOf(1, 2, 3)
    println("Test Case 1: ratings = ${ratings1.contentToString()} -> Min Candies = ${solver.candy(ratings1)}") // Expected: 6

    // Test Case 2: Descending ratings
    val ratings2 = intArrayOf(3, 2, 1)
    println("Test Case 2: ratings = ${ratings2.contentToString()} -> Min Candies = ${solver.candy(ratings2)}") // Expected: 6

    // Test Case 3: Valley shape
    val ratings3 = intArrayOf(1, 0, 2)
    println("Test Case 3: ratings = ${ratings3.contentToString()} -> Min Candies = ${solver.candy(ratings3)}") // Expected: 5

    // Test Case 4: Equal ratings
    val ratings4 = intArrayOf(1, 1, 1)
    println("Test Case 4: ratings = ${ratings4.contentToString()} -> Min Candies = ${solver.candy(ratings4)}") // Expected: 3

    // Test Case 5: Random ratings
    val ratings5 = intArrayOf(1, 3, 4, 5, 2)
    println("Test Case 5: ratings = ${ratings5.contentToString()} -> Min Candies = ${solver.candy(ratings5)}") // Expected: 11
}
