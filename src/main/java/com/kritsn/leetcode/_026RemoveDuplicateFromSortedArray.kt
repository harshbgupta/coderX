package com.kritsn.leetcode

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since July 09, 2025
 */
fun main() {
    val solution = _026RemoveDuplicateFromSortedArray()
    val testCases = listOf(
        intArrayOf(1, 1, 2),
        intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4),
        intArrayOf(),
        intArrayOf(1, 2, 3, 4)
    )

    testCases.forEach { nums ->
        val originalArray = nums.contentToString()
        val k = solution.removeDuplicates(nums)
        val modifiedArray = nums.slice(0 until k).joinToString(", ")

        println("Original: $originalArray Result: k = $k, Modified Nums: [$modifiedArray]")
        println("-------------------")
    }
}

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same. Then return the number of
 * unique elements in nums.
 *
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were
 * present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 */
class _026RemoveDuplicateFromSortedArray {

    /**
     * Removes duplicates from a sorted array in-place.
     *
     * @param nums The input array, sorted in non-decreasing order.
     * @return The number of unique elements (k). The first k elements of nums will be the unique elements.
     */
    fun removeDuplicates(nums: IntArray): Int {
        // If the array is empty, there are no unique elements.
        if (nums.size == 0) {
            return 0
        }

        // 'insertIndex' is the slow-runner pointer. It marks the position
        // for the next unique element. It starts at 1 because the first
        // element is always unique and in its correct place.
        var insertIndex = 1

        // We iterate through the array with a fast-runner 'i' starting from the second element.
        for (i in 1..<nums.size) {
            // Compare the current element with the last unique element found.
            // The last unique element is at nums[insertIndex - 1].
            if (nums[i] != nums[insertIndex - 1]) {
                // If they are different, we have found a new unique element.
                // Place it at the 'insertIndex' position.
                nums[insertIndex] = nums[i]
                // Move the insertIndex forward to mark the new boundary of the unique subarray.
                insertIndex++
            }
            // If nums[i] is a duplicate, we do nothing and just let 'i' increment,
            // effectively skipping the duplicate.
        }

        // 'insertIndex' now represents the total number of unique elements.
        return insertIndex
    }
}