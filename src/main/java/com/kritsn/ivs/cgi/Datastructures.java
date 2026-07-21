package com.kritsn.ivs.cgi;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 21, 2026
 */

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 */
public class Datastructures {

    public static void main(String[] args) {
        int[] input = {5, 7, 7, 8, 8, 8, 10};
        int[] result1 = findOccurrence(input, 8);
        int[] result2 = findOccurrence(input, 6);
        int[] result3 = findOccurrence(input, 5);

        System.out.println("result1." + result1[0] + "," + result1[1]);
        System.out.println("result2." + result2[0] + "," + result2[1]);
        System.out.println("result3." + result3[0] + "," + result3[1]);
    }

    private static int[] findOccurrence(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        int[] result = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result[0] = Math.min(result[0], i);
                result[1] = Math.max(result[1], i);
            }
        }
        if (result[0] == Integer.MAX_VALUE)
            return new int[]{-1, -1};
        return result;
    }

    private static int[] findOccurrence2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                firstIndex = i;
                while (i < nums.length) {
                    i++;
                    if (nums[i] != target) {
                        secondIndex = i - 1;
                        return new int[]{firstIndex, secondIndex};
                    }
                }
            }
        }
        return new int[]{firstIndex, secondIndex};
    }

}
