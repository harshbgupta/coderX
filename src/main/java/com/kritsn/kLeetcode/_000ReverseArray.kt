package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an array of integers, reverse the array in-place.
 *
 * That means, the first element becomes the last, the second becomes the second last, and so on.
 *
 * You must perform the operation in-place without using extra memory.
 */
class _000ReverseArray {

    ///////////////////////////////////////////////////////////////////////////
    // Two Pointer Swap Approach:
    //
    // We use two pointers: one starting from the beginning, and one from the end.
    // By swapping elements at these pointers and moving them toward each other,
    // we reverse the array in-place.
    //
    // 🪜 Steps:
    // 1. Initialize two pointers: `left = 0`, `right = arr.size - 1`
    // 2. While left < right:
    //    a. Swap arr[left] and arr[right]
    //    b. Move left++, right--
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — where n is the number of elements in the array.
    // Space Complexity: O(1) — in-place reversal with no extra space.
    ///////////////////////////////////////////////////////////////////////////
    fun reverseArray(arr: IntArray) {
        var left = 0
        var right = arr.size - 1

        while (left < right) {
            // Swap arr[left] and arr[right]
            val temp = arr[left]
            arr[left] = arr[right]
            arr[right] = temp

            // Move pointers towards the center
            left++
            right--
        }
    }

    companion object {

        // 🔍 Main method with clearly labeled test cases
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _000ReverseArray()

            val case1 = intArrayOf(1, 2, 3, 4, 5)
            solver.reverseArray(case1)
            println("Test Case 1: Reversed Array = ${case1.contentToString()}") // Expected: [5, 4, 3, 2, 1]

            val case2 = intArrayOf(10, 20)
            solver.reverseArray(case2)
            println("Test Case 2: Reversed Array = ${case2.contentToString()}") // Expected: [20, 10]

            val case3 = intArrayOf(100)
            solver.reverseArray(case3)
            println("Test Case 3: Reversed Array = ${case3.contentToString()}") // Expected: [100]

            val case4 = intArrayOf()
            solver.reverseArray(case4)
            println("Test Case 4: Reversed Array = ${case4.contentToString()}") // Expected: []

            val case5 = intArrayOf(-1, 0, 1)
            solver.reverseArray(case5)
            println("Test Case 5: Reversed Array = ${case5.contentToString()}") // Expected: [1, 0, -1]
        }

    }
}
