package com.kritsn.leetcodeKotlin.easy
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 10, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element
 * always exists in the array.
 */
fun majorityElement(nums: IntArray): Int {
    var count = 0
    var candidate = 0

    for (num in nums) {
        if (count == 0) {
            // Set the current element as candidate
            candidate = num
        }

        // If the current number matches the candidate, increase the count
        // Else decrease the count
        count += if (num == candidate) 1 else -1
    }

    // Since majority element always exists, return candidate
    return candidate
}

// 🔍 Test the code with multiple cases
fun main() {
    // Test Case 1: Simple case with obvious majority
    val nums1 = intArrayOf(3, 4, 5, 2, 3, 4, 4, 5, 5)
    println("Test Case 1: Input = ${nums1.contentToString()}, Output = ${majorityElement(nums1)}") // Expected: 3

    // Test Case 2: Majority is spread
    val nums2 = intArrayOf(2, 2, 1, 1, 1, 2, 2)
    println("Test Case 2: Input = ${nums2.contentToString()}, Output = ${majorityElement(nums2)}") // Expected: 2

    // Test Case 3: All elements same
    val nums3 = intArrayOf(5, 5, 5, 5)
    println("Test Case 3: Input = ${nums3.contentToString()}, Output = ${majorityElement(nums3)}") // Expected: 5

    // Test Case 4: Single element
    val nums4 = intArrayOf(9)
    println("Test Case 4: Input = ${nums4.contentToString()}, Output = ${majorityElement(nums4)}") // Expected: 9
}
