package com.kritsn.leetcodeKotlin.medium

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 18, 2025
 */
/**
 * LeetCode 1004. Max Consecutive Ones III
 *
 * Problem:
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1s
 * if you can flip at most k 0s.
 *
 * Examples:
 * 1) nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 -> 6
 * 2) nums = [0,0,1,1,0,0,1,1,1,0], k = 3 -> 10
 *
 * Constraints:
 * - 1 <= nums.size <= 1e5
 * - nums[i] ∈ {0,1}
 * - 0 <= k <= nums.size
 */
class _1004MaxConsecutiveOnesIII {

    /**
     * Sliding Window with two pointers.
     *
     * Time: O(n)
     * Space: O(1)
     */
    fun longestOnes(nums: IntArray, k: Int): Int {
        var left = 0
        var zeroCount = 0
        var best = 0

        for (right in nums.indices) {
            if (nums[right] == 0) zeroCount++

            while (zeroCount > k) {
                if (nums[left] == 0) zeroCount--
                left++
            }
            best = maxOf(best, right - left + 1)
        }
        return best
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _1004MaxConsecutiveOnesIII()
            println("Case 1: " + solver.longestOnes(intArrayOf(1,1,1,0,0,0,1,1,1,1,0), 2) + " (expected 6)")
            println("Case 2: " + solver.longestOnes(intArrayOf(0,0,1,1,0,0,1,1,1,0), 3) + " (expected 10)")
            println("All ones, k=0: " + solver.longestOnes(intArrayOf(1,1,1,1), 0) + " (expected 4)")
            println("All zeros, k=3: " + solver.longestOnes(intArrayOf(0,0,0,0,0), 3) + " (expected 3)")
            println("Single elem [0], k=1: " + solver.longestOnes(intArrayOf(0), 1) + " (expected 1)")
        }
    }
}