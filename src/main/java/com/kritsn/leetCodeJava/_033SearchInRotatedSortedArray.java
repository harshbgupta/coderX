package com.kritsn.leetCodeJava;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 06, 2025
 */

public class _033SearchInRotatedSortedArray {

    private int search(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;

            if(nums[left] <= nums[mid]){
                //left half is sorted
                if(target>= nums[left] && target<nums[mid]){
                    //target lies in left half sorted
                    right = mid - 1;
                } else {
                    //target lies in right half
                    left = mid + 1;
                }
            } else {
                // right half sorted
                if (target>nums[mid] && target<=nums[right]){
                    //target lies in right half sorted
                    left = mid + 1;
                } else {
                    //target lies in left half
                    right = mid - 1;
                }
            }
        }
        //target not found
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        _033SearchInRotatedSortedArray solution = new _033SearchInRotatedSortedArray();
        int result = solution.search(nums, target);
        System.out.println("Index of target: " + result);
    }
}
