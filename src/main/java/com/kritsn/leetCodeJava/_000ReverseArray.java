package com.kritsn.leetCodeJava;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an array of integers, reverse the array in-place.
 *
 * That means, the first element becomes the last, the second becomes the second last, and so on.
 *
 * You must perform the operation in-place without using extra memory.
 */
public class _000ReverseArray {

    ///////////////////////////////////////////////////////////////////////////
    // Two Pointer Swap Approach:
    //
    // We use two pointers: one starting from the beginning, and one from the end.
    // By swapping elements at these pointers and moving them toward each other,
    // we reverse the array in-place.
    //
    // 🪜 Steps:
    // 1. Initialize two pointers: `left = 0`, `right = arr.length - 1`
    // 2. While left < right:
    //    a. Swap arr[left] and arr[right]
    //    b. Move left++, right--
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — where n is the number of elements in the array.
    // Space Complexity: O(1) — in-place reversal with no extra space.
    ///////////////////////////////////////////////////////////////////////////
    void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Swap arr[left] and arr[right]
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // Move pointers towards the center
            left++;
            right--;
        }
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _000ReverseArray solver = new _000ReverseArray();

        int[] case1 = {1, 2, 3, 4, 5};
        solver.reverseArray(case1);
        System.out.println("Test Case 1: Reversed Array = " + Arrays.toString(case1)); // Expected: [5, 4, 3, 2, 1]

        int[] case2 = {10, 20};
        solver.reverseArray(case2);
        System.out.println("Test Case 2: Reversed Array = " + Arrays.toString(case2)); // Expected: [20, 10]

        int[] case3 = {100};
        solver.reverseArray(case3);
        System.out.println("Test Case 3: Reversed Array = " + Arrays.toString(case3)); // Expected: [100]

        int[] case4 = {};
        solver.reverseArray(case4);
        System.out.println("Test Case 4: Reversed Array = " + Arrays.toString(case4)); // Expected: []

        int[] case5 = {-1, 0, 1};
        solver.reverseArray(case5);
        System.out.println("Test Case 5: Reversed Array = " + Arrays.toString(case5)); // Expected: [1, 0, -1]
    }
}
