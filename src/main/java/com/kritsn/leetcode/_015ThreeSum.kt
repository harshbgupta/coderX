package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 15, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * The solution set must not contain duplicate triplets.
 */
class _015ThreeSum {

    ///////////////////////////////////////////////////////////////////////////
    // Sort + Two-Pointer Approach:
    //
    // After sorting, we fix one element and search the remaining array with two
    // pointers (left / right) to find complementary pairs that sum to zero.
    //
    // 🪜 Steps:
    // 1. Sort nums.
    // 2. For each index i (0..n-3):
    //    a. Skip duplicates for nums[i].
    //    b. Initialize left = i+1 and right = n-1.
    //    c. While left < right:
    //       - Compute sum = nums[i] + nums[left] + nums[right].
    //       - If sum == 0 -> store triplet, move both pointers (skip duplicates).
    //       - If sum < 0  -> left++ (need a larger sum).
    //       - If sum > 0  -> right-- (need a smaller sum).
    // 3. Return collected triplets.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n^2) — for each i we scan the rest with two pointers.
    // Space Complexity: O(1) extra — apart from output list (sorting is in place).
    ///////////////////////////////////////////////////////////////////////////
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (nums.size < 3) return result

        nums.sort()                           // Step 1: sort in ascending order

        for (i in 0 until nums.size - 2) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue

            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        // Found a valid triplet
                        result.add(listOf(nums[i], nums[left], nums[right]))

                        // Skip duplicates for left pointer
                        while (left < right && nums[left] == nums[left + 1]) left++
                        // Skip duplicates for right pointer
                        while (left < right && nums[right] == nums[right - 1]) right--

                        left++
                        right--
                    }
                    sum < 0  -> left++   // Need a larger sum
                    else      -> right-- // Need a smaller sum
                }
            }
        }
        return result
    }


    fun threeSum1(nums_input: IntArray): List<List<Int>> {
        var nums = nums_input.sorted()
        val size = nums.size
        var res: MutableList<List<Int>> = mutableListOf()

        for (i in 0 until size) {
            if (i > 0 && nums[i] == nums[i-1]) continue
            val target = nums[i] * (-1)
            var left = i+1
            var right = size - 1
            while (left < right) {
                when {
                    left > 0 && left-1 != i && nums[left] == nums[left-1] -> left++
                    right < size - 1 && nums[right] == nums[right+1] -> right--
                    nums[left] + nums[right] == target -> {
                        res.add(listOf(nums[i], nums[left], nums[right]))
                        left++
                        right--
                    }
                    nums[left] + nums[right] < target -> left++
                    else -> right--
                }
            }
        }

        return res
    }

    companion object{
        @JvmStatic
        // 🔍 Main method with clearly labeled test cases
        fun main(args: Array<String>) {
            val solver = _015ThreeSum()

            val nums1 = intArrayOf(-1, 0, 1, 2, -1, -4)
            println("Test Case 1: nums = ${nums1.contentToString()} -> Triplets = ${solver.threeSum1(nums1)}")
            // Expected: [[-1, -1, 2], [-1, 0, 1]]

            val nums2 = intArrayOf(0, 1, 1)
            println("Test Case 2: nums = ${nums2.contentToString()} -> Triplets = ${solver.threeSum(nums2)}")
            // Expected: []

            val nums3 = intArrayOf(0, 0, 0)
            println("Test Case 3: nums = ${nums3.contentToString()} -> Triplets = ${solver.threeSum1(nums3)}")
            // Expected: [[0, 0, 0]]

            val nums4 = intArrayOf(-2, 0, 1, 1, 2)
            println("Test Case 4: nums = ${nums4.contentToString()} -> Triplets = ${solver.threeSum(nums4)}")
            // Expected: [[-2, 0, 2], [-2, 1, 1]]
        }
    }
}
