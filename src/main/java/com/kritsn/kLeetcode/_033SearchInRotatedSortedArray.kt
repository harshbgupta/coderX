package com.kritsn.kLeetcode

/** NOTE:::
 * 1. if you see search and sorted go for Binary search
 * 2. If Time complexity is O(logN) then it's very high chance the ans will be done by Binary search
 */

/**
 * Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums. You must write an algorithm with O(log n) runtime complexity.
 */
class _033SearchInRotatedSortedArray {



    /*
here we can use leaner search (just out a for loop and search for ele), but in this case time complexity will be O(N) But
here we see the `search` and `sorted` always go for BINARY search
Interview will always ask for O(logN) time complexity
*/

    /**
     * Search in Rotated Sorted Array
     *
     * Given a rotated sorted array and a target, finds the index of the target.
     * This solution uses a modified binary search to achieve O(log n) time complexity.
     *
     * Time Complexity: O(log n) - Standard for binary search as we halve the search space in each step.
     * Space Complexity: O(1) - We only use a few variables to keep track of pointers.
     *
     * @param nums The rotated sorted integer array.
     * @param target The integer to search for.
     * @return The index of the target if found, otherwise -1.
     */
    fun solutionWithLogNTimeComplexity(nums: IntArray, target: Int): Int {
        // Initialize pointers for the start and end of the array.
        var left = 0
        var right = nums.size - 1

        // Standard binary search loop.
        while (left <= right) {
            // Calculate the middle index to avoid potential integer overflow.
            val mid = left + (right - left) / 2

            // Case 1: The middle element is the target. We found it!
            if (nums[mid] == target) {
                return mid
            }

            // Case 2: The left half of the array (from left to mid) is sorted.
            // This is true if the first element is less than or equal to the middle element.
            if (nums[left] <= nums[mid]) {
                // Now, check if the target is within the bounds of this sorted left half.
                if (target >= nums[left] && target < nums[mid]) {
                    // If it is, we can discard the right half of the search space.
                    right = mid - 1
                } else {
                    // Otherwise, the target must be in the unsorted right half.
                    left = mid + 1
                }
            }
            // Case 3: The right half of the array (from mid to right) must be sorted.
            else {
                // Now, check if the target is within the bounds of this sorted right half.
                if (target > nums[mid] && target <= nums[right]) {
                    // If it is, we can discard the left half of the search space.
                    left = mid + 1
                } else {
                    // Otherwise, the target must be in the unsorted left half.
                    right = mid - 1
                }
            }
        }

        // If the loop finishes without finding the target, it's not in the array.
        return -1
    }

    companion object{
        @JvmStatic
        fun main(array: Array<String>) {
            val solution = _033SearchInRotatedSortedArray()
            println("--- Testing Search in Rotated Sorted Array ---")

            // Standard case: Target is in the right half (after the pivot)
            val nums1 = intArrayOf(6, 7, 8, 9, 0, 1, 2, 3, 4, 5)
            val target1 = 8
            println(
                "Input: nums=${nums1.contentToString()}, target=$target1, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums1,
                        target1
                    )
                }"
            ) // Expected: 4

            // Standard case: Target is in the left half (before the pivot)
            val nums2 = intArrayOf(8, 9, 10, 11, 12, 15, 0, 1)
            val target2 = 15
            println(
                "Input: nums=${nums2.contentToString()}, target=$target2, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums2,
                        target2
                    )
                }"
            ) // Expected: 5

            // Target not found
            val nums3 = intArrayOf(4, 5, 6, 7, 0, 1, 2)
            val target3 = 3
            println(
                "Input: nums=${nums3.contentToString()}, target=$target3, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums3,
                        target3
                    )
                }"
            ) // Expected: -1

            // Edge case: No rotation (a standard sorted array)
            val nums4 = intArrayOf(1, 2, 3, 4, 5, 6)
            val target4 = 4
            println(
                "Input: nums=${nums4.contentToString()}, target=$target4, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums4,
                        target4
                    )
                }"
            ) // Expected: 3

            // Edge case: Target is the first element
            val nums5 = intArrayOf(5, 1, 3)
            val target5 = 5
            println(
                "Input: nums=${nums5.contentToString()}, target=$target5, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums5,
                        target5
                    )
                }"
            ) // Expected: 0

            // Edge case: Single element array, target found
            val nums6 = intArrayOf(1)
            val target6 = 1
            println(
                "Input: nums=${nums6.contentToString()}, target=$target6, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums6,
                        target6
                    )
                }"
            ) // Expected: 0

            // Edge case: Single element array, target not found
            val nums7 = intArrayOf(1)
            val target7 = 0
            println(
                "Input: nums=${nums7.contentToString()}, target=$target7, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums7,
                        target7
                    )
                }"
            ) // Expected: -1

            // Edge case: Empty array
            val nums8 = intArrayOf()
            val target8 = 5
            println(
                "Input: nums=${nums8.contentToString()}, target=$target8, Output: ${
                    solution.solutionWithLogNTimeComplexity(
                        nums8,
                        target8
                    )
                }"
            ) // Expected: -1
        }
    }
}