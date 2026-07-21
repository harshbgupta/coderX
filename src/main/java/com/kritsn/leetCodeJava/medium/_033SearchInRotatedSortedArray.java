package com.kritsn.leetCodeJava.medium;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/** NOTE:::
 * 1. if you see search and sorted go for Binary search
 * 2. If Time complexity is O(logN) then it's very high chance the ans will be done by Binary search
 */

/**
 * Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 &lt;= k &lt; nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums. You must write an algorithm with O(log n) runtime complexity.
 */
public class _033SearchInRotatedSortedArray {

    /*
    here we can use leaner search (just out a for loop and search for ele), but in this case time complexity will be O(N) But
    here we see the `search` and `sorted` always go for BINARY search
    Interview will always ask for O(logN) time complexity
    */

    /**
     * Search in Rotated Sorted Array
     * <p>
     * Given a rotated sorted array and a target, finds the index of the target.
     * This solution uses a modified binary search to achieve O(log n) time complexity.
     * <p>
     * Time Complexity: O(log n) - Standard for binary search as we halve the search space in each step.
     * Space Complexity: O(1) - We only use a few variables to keep track of pointers.
     *
     * @param nums   The rotated sorted integer array.
     * @param target The integer to search for.
     * @return The index of the target if found, otherwise -1.
     */
    int solutionWithLogNTimeComplexity(int[] nums, int target) {
        // Initialize pointers for the start and end of the array.
        int left = 0;
        int right = nums.length - 1;

        // Standard binary search loop.
        while (left <= right) {
            // Calculate the middle index to avoid potential integer overflow.
            int mid = left + (right - left) / 2;

            // Case 1: The middle element is the target. We found it!
            if (nums[mid] == target) {
                return mid;
            }

            // Case 2: The left half of the array (from left to mid) is sorted.
            // This is true if the first element is less than or equal to the middle element.
            if (nums[left] <= nums[mid]) {
                // Now, check if the target is within the bounds of this sorted left half.
                if (target >= nums[left] && target < nums[mid]) {
                    // If it is, we can discard the right half of the search space.
                    right = mid - 1;
                } else {
                    // Otherwise, the target must be in the unsorted right half.
                    left = mid + 1;
                }
            }
            // Case 3: The right half of the array (from mid to right) must be sorted.
            else {
                // Now, check if the target is within the bounds of this sorted right half.
                if (target > nums[mid] && target <= nums[right]) {
                    // If it is, we can discard the left half of the search space.
                    left = mid + 1;
                } else {
                    // Otherwise, the target must be in the unsorted left half.
                    right = mid - 1;
                }
            }
        }

        // If the loop finishes without finding the target, it's not in the array.
        return -1;
    }

    public static void main(String[] args) {
        _033SearchInRotatedSortedArray solution = new _033SearchInRotatedSortedArray();
        System.out.println("--- Testing Search in Rotated Sorted Array ---");

        // Standard case: Target is in the right half (after the pivot)
        int[] nums1 = {6, 7, 8, 9, 0, 1, 2, 3, 4, 5};
        int target1 = 8;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums1) + ", target=" + target1 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums1, target1)); // Expected: 4

        // Standard case: Target is in the left half (before the pivot)
        int[] nums2 = {8, 9, 10, 11, 12, 15, 0, 1};
        int target2 = 15;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums2) + ", target=" + target2 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums2, target2)); // Expected: 5

        // Target not found
        int[] nums3 = {4, 5, 6, 7, 0, 1, 2};
        int target3 = 3;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums3) + ", target=" + target3 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums3, target3)); // Expected: -1

        // Edge case: No rotation (a standard sorted array)
        int[] nums4 = {1, 2, 3, 4, 5, 6};
        int target4 = 4;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums4) + ", target=" + target4 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums4, target4)); // Expected: 3

        // Edge case: Target is the first element
        int[] nums5 = {5, 1, 3};
        int target5 = 5;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums5) + ", target=" + target5 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums5, target5)); // Expected: 0

        // Edge case: Single element array, target found
        int[] nums6 = {1};
        int target6 = 1;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums6) + ", target=" + target6 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums6, target6)); // Expected: 0

        // Edge case: Single element array, target not found
        int[] nums7 = {1};
        int target7 = 0;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums7) + ", target=" + target7 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums7, target7)); // Expected: -1

        // Edge case: Empty array
        int[] nums8 = {};
        int target8 = 5;
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums8) + ", target=" + target8 + ", Output: " + solution.solutionWithLogNTimeComplexity(nums8, target8)); // Expected: -1
    }
}
