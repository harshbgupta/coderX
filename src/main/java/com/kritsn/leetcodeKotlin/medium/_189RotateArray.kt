package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 11, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */

fun rotate(nums: IntArray, k: Int) {
    val n = nums.size // i.e. nums = [1, 2, 3, 4, 5, 6, 7],  k = 12 and n = nums.length = 7
    val steps = k % n // Normalize k to be within array bounds // steps = 12 % 7 = 5

    // Helper function to reverse a portion of the array in-place
    fun reverse(start: Int, end: Int) {
        var left = start
        var right = end
        while (left < right) {
            // Swap elements at left and right indices
            val temp = nums[left]
            nums[left] = nums[right]
            nums[right] = temp
            left++
            right--
        }
    }

    // Step 1: Reverse the entire array
    reverse(0, n - 1) // nums = [7, 6, 5, 4, 3, 2, 1] after reverse

    // Step 2: Reverse the first k elements
    reverse(0, steps - 1) // nums = [3, 4, 5, 6, 7, 2, 1] after again reverse from 0 to( 5-1 = 4) subarray

    // Step 3: Reverse the remaining elements
    reverse(steps, n - 1) // nums = [3, 4, 5, 6, 7, 1, 2] after again reverse from 5 to( 7-1 = 6) subarray
}

// 🔍 Main method with clearly labeled test cases and expected outputs
fun main() {
    // Test Case 1: Standard rotation
    val nums1 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    rotate(nums1, 3)
    println("Test Case 1: After rotating [1,2,3,4,5,6,7] by 3 -> ${nums1.contentToString()}") // Expected: [5, 6, 7, 1, 2, 3, 4]

    // Test Case 2: Rotation by 0 (no change)
    val nums2 = intArrayOf(1, 2, 3)
    rotate(nums2, 0)
    println("Test Case 2: After rotating [1,2,3] by 0 -> ${nums2.contentToString()}") // Expected: [1, 2, 3]

    // Test Case 3: Rotation by array size (same as no rotation)
    val nums3 = intArrayOf(1, 2, 3, 4)
    rotate(nums3, 4)
    println("Test Case 3: After rotating [1,2,3,4] by 4 -> ${nums3.contentToString()}") // Expected: [1, 2, 3, 4]

    // Test Case 4: Rotation greater than array size
    val nums4 = intArrayOf(1, 2)
    rotate(nums4, 5)
    println("Test Case 4: After rotating [1,2] by 5 -> ${nums4.contentToString()}") // Expected: [2, 1]

    // Test Case 5: Single element
    val nums5 = intArrayOf(10)
    rotate(nums5, 100)
    println("Test Case 5: After rotating [10] by 100 -> ${nums5.contentToString()}") // Expected: [10]
}
