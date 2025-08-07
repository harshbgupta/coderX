package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 15, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 */
class _209MinimumSizeSubarraySum {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window Approach:
    //
    // Use two pointers to maintain a dynamic window [start, end].
    // Expand the window by moving 'end' until sum ≥ target.
    // Then, try to shrink it from the left to find the smallest valid window.
    //
    // 🪜 Steps:
    // 1. Initialize sum = 0, start = 0, minLen = Int.MAX_VALUE.
    // 2. Move 'end' from 0 to n-1:
    //    - Add nums[end] to sum.
    //    - While sum >= target:
    //        - Update minLen = min(minLen, end - start + 1).
    //        - Subtract nums[start] from sum and increment start.
    // 3. If minLen was not updated, return 0. Else return minLen.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — each element is visited at most twice.
    // Space Complexity: O(1) — constant extra space.
    ///////////////////////////////////////////////////////////////////////////
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var start = 0
        var sum = 0
        var minLen = Int.MAX_VALUE

        for (end in nums.indices) {
            sum += nums[end] // Add current element to sum

            // Shrink window from the left while sum is valid
            while (sum >= target) {
                minLen = minOf(minLen, end - start + 1) // Update minimum length
                sum -= nums[start] // Remove the leftmost element
                start++ // Move window start
            }
        }

        return if (minLen == Int.MAX_VALUE) 0 else minLen
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _209MinimumSizeSubarraySum()

    val target1 = 7
    val nums1 = intArrayOf(2, 3, 1, 2, 4, 3)
    println("Test Case 1: target = $target1, nums = ${nums1.contentToString()} -> Min Length = ${solver.minSubArrayLen(target1, nums1)}") // Expected: 2

    val target2 = 4
    val nums2 = intArrayOf(1, 4, 4)
    println("Test Case 2: target = $target2, nums = ${nums2.contentToString()} -> Min Length = ${solver.minSubArrayLen(target2, nums2)}") // Expected: 1

    val target3 = 11
    val nums3 = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)
    println("Test Case 3: target = $target3, nums = ${nums3.contentToString()} -> Min Length = ${solver.minSubArrayLen(target3, nums3)}") // Expected: 0

    val target4 = 15
    val nums4 = intArrayOf(1, 2, 3, 4, 5)
    println("Test Case 4: target = $target4, nums = ${nums4.contentToString()} -> Min Length = ${solver.minSubArrayLen(target4, nums4)}") // Expected: 5

    val target5 = 100
    val nums5 = intArrayOf(1, 2, 3, 4, 5)
    println("Test Case 5: target = $target5, nums = ${nums5.contentToString()} -> Min Length = ${solver.minSubArrayLen(target5, nums5)}") // Expected: 0
}
