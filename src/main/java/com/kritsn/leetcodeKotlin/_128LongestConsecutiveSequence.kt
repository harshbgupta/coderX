package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/**
    Leetcode 128: Longest Consecutive Sequence

    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

    You must write an algorithm that runs in O(n) time.
*/
class _128LongestConsecutiveSequence {

    /**
     * 🧠 Algorithm & Approach:
     *
     * 1. Store all elements in a HashSet for O(1) lookups.
     * 2. Loop through each number in the array.
     *    - Only start counting a new sequence if (num - 1) doesn't exist in the set.
     *    - From there, increment num + 1, num + 2, ... while they exist in the set.
     *    - Track the max sequence length seen so far.
     *
     * Time Complexity: O(n), where n is the number of elements.
     * Space Complexity: O(n), for the HashSet.
     */
    fun longestConsecutive(nums: IntArray): Int {
        // Edge case: if the input array is empty, return 0 immediately
        if (nums.isEmpty()) return 0

        // Convert the array to a HashSet to allow O(1) time complexity for lookups
        val numSet = nums.toHashSet()

        // Variable to keep track of the maximum length of any consecutive sequence found
        var maxLength = 0

        // Iterate through each number in the array
        for (num in nums) {
            // Optimization: Only consider 'num' as the start of a sequence
            // if the number just before it (num - 1) does NOT exist in the set.
            // This ensures we only build sequences from their beginning,
            // avoiding redundant work and keeping time complexity linear.
            if (!numSet.contains(num - 1)) {

                // Start a new sequence from the current number
                var currentNum = num
                var currentStreak = 1 // Initial length of the streak is 1 (currentNum itself)

                // Keep checking for the next consecutive number in the sequence
                // As long as (currentNum + 1) exists in the set, continue the streak
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1 // Move to the next consecutive number
                    currentStreak += 1 // Increase the streak length
                }

                // Update the maximum length found so far, if this streak is longer
                maxLength = maxOf(maxLength, currentStreak)
            }
        }

        // Return the longest consecutive sequence length found
        return maxLength
    }
}

// 🧪 Main method with test cases
fun main() {
    val solution = _128LongestConsecutiveSequence()

    println("Test Case 1:")
    println("Input: [100, 4, 200, 1, 3, 2]")
    println("Output: ${solution.longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2))}") // Expected: 4

    println("\nTest Case 2:")
    println("Input: [0,3,7,2,5,8,4,6,0,1]")
    println("Output: ${solution.longestConsecutive(intArrayOf(0,3,7,2,5,8,4,6,0,1))}") // Expected: 9

    println("\nTest Case 3:")
    println("Input: [9,1,-3,2,4,8,3,-1,6,-2,-4,7]")
    println("Output: ${solution.longestConsecutive(intArrayOf(9,1,-3,2,4,8,3,-1,6,-2,-4,7))}") // Expected: 7

    println("\nTest Case 4:")
    println("Input: [1,2,0,1]")
    println("Output: ${solution.longestConsecutive(intArrayOf(1,2,0,1))}") // Expected: 3
}
