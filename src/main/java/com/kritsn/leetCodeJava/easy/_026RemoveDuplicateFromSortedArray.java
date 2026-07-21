package com.kritsn.leetCodeJava.easy;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same. Then return the number of
 * unique elements in nums.
 * <p>
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 * <p>
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were
 * present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 */
public class _026RemoveDuplicateFromSortedArray {

    /**
     * Removes duplicates from a sorted array in-place.
     *
     * @param nums The input array, sorted in non-decreasing order.
     * @return The number of unique elements (k). The first k elements of nums will be the unique elements.
     */
    int removeDuplicates(int[] nums) {
        // If the array is empty, there are no unique elements.
        if (nums.length == 0) {
            return 0;
        }

        // 'insertIndexSlowPointer' is the slow-runner pointer. It marks the position
        // for the next unique element. It starts at 1 because the first
        // element is always unique and in its correct place.
        int insertIndexSlowPointer = 1;

        // We iterate through the array with a fast-runner 'i' starting from the second element.
        for (int iOrFastPointer = 1; iOrFastPointer < nums.length; iOrFastPointer++) {
            // Compare the current element with the last unique element found.
            // The last unique element is at nums[insertIndexSlowPointer - 1].
            if (nums[iOrFastPointer] != nums[insertIndexSlowPointer - 1]) {
                // If they are different, we have found a new unique element.
                // Place it at the 'insertIndexSlowPointer' position.
                nums[insertIndexSlowPointer] = nums[iOrFastPointer];
                // Move the insertIndexSlowPointer forward to mark the new boundary of the unique subarray.
                insertIndexSlowPointer++;
            }
            // If nums[iOrFastPointer] is a duplicate, we do nothing and just let 'iOrFastPointer' increment,
            // effectively skipping the duplicate.
        }

        // 'insertIndexSlowPointer' now represents the total number of unique elements.
        return insertIndexSlowPointer;
    }

    public static void main(String[] args) {
        _026RemoveDuplicateFromSortedArray solution = new _026RemoveDuplicateFromSortedArray();
        int[][] testCases = {
                {1, 1, 2},
                {0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                {},
                {1, 2, 3, 4}
        };

        for (int[] nums : testCases) {
            String originalArray = Arrays.toString(nums);
            int k = solution.removeDuplicates(nums);
            String modifiedArray = IntStream.range(0, k)
                    .mapToObj(i -> String.valueOf(nums[i]))
                    .collect(Collectors.joining(", "));

            System.out.println("Original: " + originalArray + " Result: k = " + k + ", Modified Nums: [" + modifiedArray + "]");
            System.out.println("-------------------");
        }
    }
}
