package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _080RemoveDuplicateFromSortedArrayII {

    /**
     * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
     * <p>
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
     * <p>
     * Return k after placing the final result in the first k slots of nums.
     * <p>
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
     */
    int removeDuplicates(int[] nums) {
        // Base case: If array has 2 or fewer elements, return its length
        if (nums.length <= 2) return nums.length;

        // This pointer keeps track of the position where the next valid element should go
        int insertPos = 2;

        // Start from index 2, since the first two elements are always allowed
        for (int i = 2; i < nums.length; i++) {
            // Only insert if the current number is not equal to the element two places behind insertPos
            if (nums[i] != nums[insertPos - 2]) {
                nums[insertPos] = nums[i]; // Copy the valid number to the insert position
                insertPos++;               // Move the insert position forward
            }
            // Else: skip the number as it would cause more than two duplicates
        }

        // insertPos now represents the length of the updated array
        return insertPos;
    }

    public static void main(String[] args) {
        _080RemoveDuplicateFromSortedArrayII solver = new _080RemoveDuplicateFromSortedArrayII();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = solver.removeDuplicates(nums);

        System.out.println("Output length (k): " + k);
        System.out.println("Modified array (first k elements): " + Arrays.toString(Arrays.copyOfRange(nums, 0, k)));
    }
}
