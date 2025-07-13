package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 13, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 */
class _042TrappingRainWater {

    ///////////////////////////////////////////////////////////////////////////
    // https://youtu.be/1_5VuquLbXg?feature=shared
    // https://youtu.be/UHHp8USwx4M?feature=shared
    // Prefix Max Approach:
    // For each index, the trapped water is:
    // min(maxLeft[i], maxRight[i]) - height[i]
    //
    // 🪜 Steps:
    // 1. Build leftMax[] from left to right.
    // 2. Build rightMax[] from right to left.
    // 3. For each index i:
    //    - waterAtI = min(leftMax[i], rightMax[i]) - height[i]
    //    - Add waterAtI to total
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — One pass for leftMax, one for rightMax, and one to calculate result.
    // Space Complexity: O(n) — Extra space for leftMax and rightMax arrays.
    // (Can be optimized to O(1) space using two pointers approach.)
    ///////////////////////////////////////////////////////////////////////////
    fun trap(height: IntArray): Int {
        val n = height.size
        if (n == 0) return 0

        val leftMax = IntArray(n)
        val rightMax = IntArray(n)

        // Build left max array
        leftMax[0] = height[0]
        for (i in 1 until n) {
            leftMax[i] = maxOf(leftMax[i - 1], height[i])
        }

        // Build right max array
        rightMax[n - 1] = height[n - 1]
        for (i in n - 2 downTo 0) {
            rightMax[i] = maxOf(rightMax[i + 1], height[i])
        }

        // Calculate trapped water
        var water = 0
        for (i in 0 until n) {
            val minHeight = minOf(leftMax[i], rightMax[i])
            if (minHeight > height[i]) {
                water += minHeight - height[i]
            }
        }

        return water
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _042TrappingRainWater()

    // Test Case 1: Standard case
    val height1 = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
    println("Test Case 1: height = ${height1.contentToString()} -> Trapped Water = ${solver.trap(height1)}") // Expected: 6

    // Test Case 2: No valleys
    val height2 = intArrayOf(3, 3, 3, 3)
    println("Test Case 2: height = ${height2.contentToString()} -> Trapped Water = ${solver.trap(height2)}") // Expected: 0

    // Test Case 3: Single valley
    val height3 = intArrayOf(4,2,0,3,2,5)
    println("Test Case 3: height = ${height3.contentToString()} -> Trapped Water = ${solver.trap(height3)}") // Expected: 9

    // Test Case 4: Increasing then decreasing
    val height4 = intArrayOf(1,2,3,4,3,2,1)
    println("Test Case 4: height = ${height4.contentToString()} -> Trapped Water = ${solver.trap(height4)}") // Expected: 0

    // Test Case 5: Empty input
    val height5 = intArrayOf()
    println("Test Case 5: height = ${height5.contentToString()} -> Trapped Water = ${solver.trap(height5)}") // Expected: 0
}
