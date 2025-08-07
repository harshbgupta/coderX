package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 11, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 */
class _055JumpGame {

    ///////////////////////////////////////////////////////////////////////////
    // We track the farthest index we can reach as we iterate through the array.
    // If at any point our current index is greater than the farthest reachable,
    // it means we’re stuck and return false.
    //
    // 🪜 Steps:
    // 1. Initialize maxReachable = 0.
    // 2. For each index i in nums:
    //    - If i > maxReachable => we can’t reach this point => return false.
    //    - Update maxReachable = max(maxReachable, i + nums[i])
    // 3. If we finish iterating, return true.
    ///////////////////////////////////////////////////////////////////////////
    fun canJump(nums: IntArray): Boolean {
        var maxReachable = 0 // Track the farthest index we can reach

        for (i in nums.indices) {
            if (i > maxReachable) {
                // We are at an index that is not reachable
                return false
            }
            // Update the farthest index we can reach so far
            maxReachable = maxOf(maxReachable, i + nums[i])
        }

        // If loop completes, we can reach the last index
        return true
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _055JumpGame()

    // Test Case 1: Can jump over zero safely
    val nums1 = intArrayOf(2, 3, 1, 1, 4)
    println("Test Case 1: nums = ${nums1.contentToString()} -> Can reach end = ${solver.canJump(nums1)}") // Expected: true

    // Test Case 2: Stuck at a zero
    val nums2 = intArrayOf(3, 2, 1, 0, 4)
    println("Test Case 2: nums = ${nums2.contentToString()} -> Can reach end = ${solver.canJump(nums2)}") // Expected: false

    // Test Case 3: Single element array
    val nums3 = intArrayOf(0)
    println("Test Case 3: nums = ${nums3.contentToString()} -> Can reach end = ${solver.canJump(nums3)}") // Expected: true

    // Test Case 4: All large jumps
    val nums4 = intArrayOf(5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0)
    println("Test Case 4: nums = ${nums4.contentToString()} -> Can reach end = ${solver.canJump(nums4)}") // Expected: true

    // Test Case 5: Just enough jump
    val nums5 = intArrayOf(2, 0, 0)
    println("Test Case 5: nums = ${nums5.contentToString()} -> Can reach end = ${solver.canJump(nums5)}") // Expected: true
}
