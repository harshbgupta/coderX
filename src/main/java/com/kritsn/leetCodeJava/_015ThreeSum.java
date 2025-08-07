package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

public class _015ThreeSum {

    private List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;

        nums  = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int iValue  = nums[i];
                int leftValue  = nums[left];
                int rightValue  = nums[right];
                int sum = iValue + leftValue + rightValue;
                if (sum == target) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    while (left<right && nums[left] == nums[left+1]) left++;
                    while (left<right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int target = 0;
        List<List<Integer>> result = new _015ThreeSum().threeSum(nums, target);
        System.out.println(result);
    }
}
