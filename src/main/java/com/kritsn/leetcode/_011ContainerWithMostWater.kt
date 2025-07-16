package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 15, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * You are given an integer array height of length n.
 * There are n vertical lines such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 */
class _011ContainerWithMostWater {

    ///////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=EbkMABpP52U
    // Two-Pointer Optimization:
    //
    // We scan from both ends toward the center:
    // - Calculate area between current pointers.
    // - Move the pointer pointing to the shorter line inward.
    //
    // 🪜 Steps:
    // 1. Initialize two pointers: left = 0, right = height.size - 1
    // 2. While left < right:
    //    - width = right - left
    //    - height = min(height[left], height[right])
    //    - area = width * height
    //    - update maxArea
    //    - move the pointer pointing to smaller height
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — Single pass through the array.
    // Space Complexity: O(1) — Constant space.
    ///////////////////////////////////////////////////////////////////////////
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var maxArea = 0

        while (left < right) {
            // Calculate width and height of current container
            val width = right - left
            val containerHeight = minOf(height[left], height[right])
            val area = width * containerHeight

            // Update the maximum area if needed
            maxArea = maxOf(maxArea, area)

            // Move the pointer with the smaller height inward
            if (height[left] < height[right]) {
                // need to find bigger left wall
                left++
            } else {
                // need to find bigger right wall
                right--
            }
        }

        return maxArea
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _011ContainerWithMostWater()

    val h1 = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
    println("Test Case 1: height = ${h1.contentToString()} -> Max Water = ${solver.maxArea(h1)}") // Expected: 49

    val h2 = intArrayOf(1, 1)
    println("Test Case 2: height = ${h2.contentToString()} -> Max Water = ${solver.maxArea(h2)}") // Expected: 1

    val h3 = intArrayOf(4, 3, 2, 1, 4)
    println("Test Case 3: height = ${h3.contentToString()} -> Max Water = ${solver.maxArea(h3)}") // Expected: 16

    val h4 = intArrayOf(1, 2, 1)
    println("Test Case 4: height = ${h4.contentToString()} -> Max Water = ${solver.maxArea(h4)}") // Expected: 2
}
