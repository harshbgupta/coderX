package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 12, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the
 * product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
class _238ProductOfArrayExceptSelf {

    ///////////////////////////////////////////////////////////////////////////
    // Two-pass approach:
    // 1. First pass (left to right): Fill result[i] with product of all elements to the left.
    // 2. Second pass (right to left): Multiply result[i] with product of all elements to the right.
    //
    // 🪜 Steps:
    // - Initialize answer[] with all 1s.
    // - Compute left products in first pass.
    // - Compute right products in second pass while updating answer[].
    ///////////////////////////////////////////////////////////////////////////
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val answer = IntArray(n) { 1 }

        var leftProduct = 1
        for (i in 0 until n) {
            // Set current answer as product of all elements to the left of i
            answer[i] = leftProduct
            leftProduct *= nums[i] // Update left product
        }

        var rightProduct = 1
        for (i in n - 1 downTo 0) {
            // Multiply with product of all elements to the right of i
            answer[i] *= rightProduct
            rightProduct *= nums[i] // Update right product
        }

        return answer
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _238ProductOfArrayExceptSelf()

    // Test Case 1: General case
    val nums1 = intArrayOf(1, 2, 3, 4)
    println("Test Case 1: nums = ${nums1.contentToString()} -> answer = ${solver.productExceptSelf(nums1).contentToString()}") // Expected: [24,12,8,6]

    // Test Case 2: Includes a zero
    val nums2 = intArrayOf(1, 2, 0, 4)
    println("Test Case 2: nums = ${nums2.contentToString()} -> answer = ${solver.productExceptSelf(nums2).contentToString()}") // Expected: [0,0,8,0]

    // Test Case 3: All same elements
    val nums3 = intArrayOf(2, 2, 2, 2)
    println("Test Case 3: nums = ${nums3.contentToString()} -> answer = ${solver.productExceptSelf(nums3).contentToString()}") // Expected: [8,8,8,8]

    // Test Case 4: Single element (edge case, but valid assumption that len >= 2 in Leetcode)
    val nums4 = intArrayOf(10, 1)
    println("Test Case 4: nums = ${nums4.contentToString()} -> answer = ${solver.productExceptSelf(nums4).contentToString()}") // Expected: [1,10]

    // Test Case 5: Contains multiple zeros
    val nums5 = intArrayOf(0, 0, 3, 4)
    println("Test Case 5: nums = ${nums5.contentToString()} -> answer = ${solver.productExceptSelf(nums5).contentToString()}") // Expected: [0,0,0,0]
}
