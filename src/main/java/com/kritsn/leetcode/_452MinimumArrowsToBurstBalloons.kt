package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 18, 2025
///////////////////////////////////////////////////////////////////////////

/**
 *
 * 📄 Problem Statement:
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented
 * as a 2D integer array `points` where `points[i] = [xstart, xend]` denotes a balloon whose horizontal diameter stretches
 * between xstart and xend. You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number
 * of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array `points`, return the minimum number of arrows that must be shot to burst all balloons.
 */

class _452MinimumArrowsToBurstBalloons {

    /**
     * https://www.youtube.com/watch?v=Z9o-lqwgSWA
     * 🧠 Algorithm & Approach:
     * - Sort the intervals by their ending x-coordinate.
     * - Place the first arrow at the end of the first balloon.
     * - For each balloon:
     *    - If it starts after the last arrow position, shoot a new arrow and update the position.
     *    - Else, it is already burst by the existing arrow.
     *
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(1) - in-place sorting and constant extra space
     */
    fun findMinArrowShots(points: Array<IntArray>): Int {
        if (points.isEmpty()) return 0

        // Sort balloons by their end point
        points.sortBy { it[1] }

        var arrows = 1
        var endPoint = points[0][1]  // place the first arrow at the end of the first balloon

        for (i in 1 until points.size) {
            // If current balloon starts after the last arrow position, it needs a new arrow
            if (points[i][0] > endPoint) {
                arrows++
                endPoint = points[i][1]
            }
            // else, this balloon is already burst by the previous arrow
        }

        return arrows
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _452MinimumArrowsToBurstBalloons()

            val test1 = arrayOf(intArrayOf(10,16), intArrayOf(2,8), intArrayOf(1,6), intArrayOf(7,12))
            println("Test 1 Input: ${test1.contentDeepToString()}")
            println("Minimum arrows needed: ${solver.findMinArrowShots(test1)}") // Expected: 2

            val test2 = arrayOf(intArrayOf(1,2), intArrayOf(3,4), intArrayOf(5,6), intArrayOf(7,8))
            println("\nTest 2 Input: ${test2.contentDeepToString()}")
            println("Minimum arrows needed: ${solver.findMinArrowShots(test2)}") // Expected: 4

            val test3 = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(4,5))
            println("\nTest 3 Input: ${test3.contentDeepToString()}")
            println("Minimum arrows needed: ${solver.findMinArrowShots(test3)}") // Expected: 2
        }
    }
}
