package com.kritsn.leetCodeJava.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _001TwoSum {
    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     */
    private int[] twoSum(int[] nums, int target) {
        // for (int i = 0; i < nums.length - 1; i++) {
        // this will not work assume the edge case [3,3],target is 6
        // because for i = 0 if we check diff index it will return 0 and if put the condition index != i, so in this case,
        // code will fail, So we will run the loop from end that will solve the issue first iteration on i = 1 the diff
        // index will 0 which will work and resolve the case

        for (int i = nums.length - 1; i >= 0; i--) { // this will work assume the edge case [3,3],target is 6
            int diff = target - nums[i];
            int index = IntStream.range(0, nums.length)
                    .filter(j -> nums[j] == diff)
                    .findFirst()
                    .orElse(-1);
            System.out.println("i and index " + i + " , " + index);
            if (index != -1 && index != i) {
                System.out.println("i and index after " + i + " , " + index);
                return new int[]{i, index};
            }
        }
        return null;
    }

    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     * <p>
     * This is an optimized one-pass hash map solution.
     * <p>
     * Time Complexity: O(n) - We iterate through the array only once. Each map lookup is O(1) on average.
     * Space Complexity: O(n) - In the worst case, we store all n elements in the hash map.
     */
    private int[] twoSumOptimized(int[] nums, int target) {
        // A map to store the number and its index: {number -> index}
        Map<Integer, Integer> numMap = new HashMap<>();

        // Iterate through the array with both the value and its index
        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];
            int complement = target - num;
            // Check if the complement needed to reach the target exists in our map
            if (numMap.containsKey(complement)) {
                // If it exists, we found our solution.
                return new int[]{numMap.get(complement), index};
            }
            // If the complement is not found, add the current number and its index to the map
            // for subsequent iterations to check against.
            numMap.put(num, index);
        }

        // Per the problem statement, a solution always exists.
        // Throwing an exception is a robust way to handle cases where no solution is found,
        // which would violate the problem's contract.
        throw new IllegalArgumentException("No two sum solution found");
    }

    public static void main(String[] args) {
        _001TwoSum solver = new _001TwoSum();
        System.out.println("Result: " + Arrays.toString(solver.twoSum(new int[]{3, 3}, 6)));

        // Example 1
        System.out.println("Result for [2, 7, 11, 15], target 9: " +
                Arrays.toString(solver.twoSumOptimized(new int[]{2, 7, 11, 15}, 9)));

        // Example 2 (the edge case from your original code)
        System.out.println("Result for [3, 3], target 6: " +
                Arrays.toString(solver.twoSumOptimized(new int[]{3, 3}, 6)));

        // Example 3
        System.out.println("Result for [3, 2, 4], target 6: " +
                Arrays.toString(solver.twoSumOptimized(new int[]{3, 2, 4}, 6)));
    }
}
