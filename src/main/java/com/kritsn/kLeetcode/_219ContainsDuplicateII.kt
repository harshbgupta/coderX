package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/*
    Leetcode 219: Contains Duplicate II

    Given an integer array nums and an integer k, return true if there are two distinct indices i and j
    such that nums[i] == nums[j] and abs(i - j) <= k.
*/

class _219ContainsDuplicateII {

    /**
     * 🧠 Algorithm & Approach:
     *
     * 1. Use a HashSet to store at most k recent elements (sliding window).
     * 2. As we iterate through the array:
     *    - If the element already exists in the set, it means a duplicate is found within k distance.
     *    - Add current element to the set.
     *    - Remove the (i - k)th element if window exceeds size k.
     *
     * Time Complexity: O(n), where n is the length of the array.
     * Space Complexity: O(k), for storing up to k elements in the HashSet.
     */
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val windowSet = HashSet<Int>()

        for (i in nums.indices) {
            if (windowSet.contains(nums[i])) {
                return true
            }

            windowSet.add(nums[i])

            // Maintain sliding window of size at most k
            if (windowSet.size > k) {
                windowSet.remove(nums[i - k])
            }
        }

        return false
    }
}

// 🧪 Main method with test cases
fun main() {
    val solution = _219ContainsDuplicateII()

    println("Test Case 1:")
    println("Input: nums = [1,2,3,1], k = 3")
    println("Output: ${solution.containsNearbyDuplicate(intArrayOf(1, 2, 3, 1), 3)}") // Expected: true

    println("\nTest Case 2:")
    println("Input: nums = [1,0,1,1], k = 1")
    println("Output: ${solution.containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1)}") // Expected: true

    println("\nTest Case 3:")
    println("Input: nums = [1,2,3,1,2,3], k = 2")
    println("Output: ${solution.containsNearbyDuplicate(intArrayOf(1, 2, 3, 1, 2, 3), 2)}") // Expected: false

    println("\nTest Case 4:")
    println("Input: nums = [99,99], k = 2")
    println("Output: ${solution.containsNearbyDuplicate(intArrayOf(99, 99), 2)}") // Expected: true
}
