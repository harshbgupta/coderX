package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 11, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * You are given a 0-indexed array of integers nums of length n.
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 *
 * Return the minimum number of jumps to reach nums[n - 1]. You can assume that it is always reachable.
 */
class _045JumpGameII {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Approach:
    // - Track the farthest point reachable from current index.
    // - Track the end of the current jump range.
    // - Every time we reach the end of the current jump range, we increment the jump count.
    //
    // 🪜 Steps:
    // 1. Initialize variables: jumps = 0, currentEnd = 0, farthest = 0.
    // 2. Traverse from index 0 to n-2 (we never jump from the last index).
    // 3. At each step, update farthest = max(farthest, i + nums[i]).
    // 4. If i == currentEnd:
    //    - Increment jumps and update currentEnd = farthest.
    // 5. Return total jumps.
    ///////////////////////////////////////////////////////////////////////////
    fun jump(nums: IntArray): Int {
        var jumps = 0 // Count of jumps needed
        var currentEnd = 0 // End of the current jump range
        var farthest = 0 // Farthest index we can reach in current scope

        for (i in 0 until nums.lastIndex) {
            // Update the farthest we can reach from current index
            farthest = maxOf(farthest, i + nums[i])

            // If we reached the end of the current jump, increase jump count
            if (i == currentEnd) {
                jumps++
                currentEnd = farthest // Move to the next range
            }
        }

        return jumps
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _045JumpGameII()

    // Test Case 1: Classic jump case
    val nums1 = intArrayOf(2, 3, 1, 1, 4)
    println("Test Case 1: nums = ${nums1.contentToString()} -> Min Jumps = ${solver.jump(nums1)}") // Expected: 2

    // Test Case 2: One jump is enough
    val nums2 = intArrayOf(2, 1)
    println("Test Case 2: nums = ${nums2.contentToString()} -> Min Jumps = ${solver.jump(nums2)}") // Expected: 1

    // Test Case 3: Increasing jumps
    val nums3 = intArrayOf(1, 2, 3, 4, 5)
    println("Test Case 3: nums = ${nums3.contentToString()} -> Min Jumps = ${solver.jump(nums3)}") // Expected: 3

    // Test Case 4: Large jump at the start
    val nums4 = intArrayOf(10, 1, 1, 1, 1)
    println("Test Case 4: nums = ${nums4.contentToString()} -> Min Jumps = ${solver.jump(nums4)}") // Expected: 1

    // Test Case 5: All 1s
    val nums5 = IntArray(6) { 1 }
    println("Test Case 5: nums = ${nums5.contentToString()} -> Min Jumps = ${solver.jump(nums5)}") // Expected: 5
}