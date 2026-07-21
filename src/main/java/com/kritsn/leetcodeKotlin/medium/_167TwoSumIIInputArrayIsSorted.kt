package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 15, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 *
 * Return the indices of the two numbers (1-based), [index1, index2], as an array.
 *
 * The solution must use only constant extra space.
 */
class _167TwoSumII {

    ///////////////////////////////////////////////////////////////////////////
    // Two-Pointer Technique:
    //
    // Since the array is sorted, we can use left and right pointers.
    // - Move the pointers based on the current sum.
    // - If current sum < target → move left pointer right
    // - If current sum > target → move right pointer left
    // - If equal → return indices (1-based)
    //
    // 🪜 Steps:
    // 1. Set left = 0, right = numbers.size - 1
    // 2. While left < right:
    //    - sum = numbers[left] + numbers[right]
    //    - Adjust pointers accordingly
    // 3. Return result when found
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — single pass using two pointers
    // Space Complexity: O(1) — constant space
    ///////////////////////////////////////////////////////////////////////////
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1

        while (left < right) {
            val sum = numbers[left] + numbers[right]

            when {
                sum == target -> return intArrayOf(left + 1, right + 1) // Return 1-based index
                sum < target -> left++  // Move left forward to increase sum
                else -> right--         // Move right backward to decrease sum
            }
        }

        return intArrayOf() // Should never reach here as one solution is guaranteed
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _167TwoSumII()

    val nums1 = intArrayOf(2, 7, 11, 15)
    val target1 = 9
    println("Test Case 1: nums = ${nums1.contentToString()}, target = $target1 -> Output = ${solver.twoSum(nums1, target1).contentToString()}") // Expected: [1, 2]

    val nums2 = intArrayOf(2, 3, 4)
    val target2 = 6
    println("Test Case 2: nums = ${nums2.contentToString()}, target = $target2 -> Output = ${solver.twoSum(nums2, target2).contentToString()}") // Expected: [1, 3]

    val nums3 = intArrayOf(-1, 0)
    val target3 = -1
    println("Test Case 3: nums = ${nums3.contentToString()}, target = $target3 -> Output = ${solver.twoSum(nums3, target3).contentToString()}") // Expected: [1, 2]

    val nums4 = intArrayOf(1, 3, 4, 6, 8, 10, 12)
    val target4 = 14
    println("Test Case 4: nums = ${nums4.contentToString()}, target = $target4 -> Output = ${solver.twoSum(nums4, target4).contentToString()}") // Expected: [3, 6]
}
