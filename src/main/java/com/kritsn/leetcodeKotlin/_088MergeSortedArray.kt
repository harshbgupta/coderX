package com.kritsn.leetcodeKotlin

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be
 * stored inside the array nums1. To accommodate this, nums1 has a length of m + n,
 * where the first m elements denote the elements that should be merged, and the last n
 * elements are set to 0 and should be ignored. nums2 has a length of n.
 */

class _088MergeSortedArray {

    /**
     * Merges two sorted arrays using a brute-force approach.
     *
     * This method works by creating a new temporary array of size `m + n`. It then
     * iterates through both input arrays, comparing elements and placing them in the
     * correct order into the temporary array. Finally, it copies the contents of the
     * temporary array back into `nums1`.
     *
     * While this approach is functionally correct, it is not optimal as it violates
     * the common constraint of solving this problem in-place (with O(1) space).
     *
     * Time Complexity: O(m + n)
     * - The algorithm iterates through all elements of both arrays to fill the new
     *   array, and then iterates through them again to copy the result back to `nums1`.
     *
     * Space Complexity: O(m + n)
     * - A new array of size `m + n` is allocated to store the merged result, which
     *   is not memory-efficient compared to an in-place solution.
     *
     * @param nums1 The first array, which will be modified to store the final result.
     * @param m The number of initialized elements in nums1.
     * @param nums2 The second array.
     * @param n The number of elements in nums2.
     * @return The modified `nums1` array containing the merged and sorted elements.
     */
    fun mergeSortedArrayBruteForce(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
// 1.    Allocate a new temporary array to hold the final merged result.
//    This uses O(m + n) extra space.
        val sol = IntArray(m + n)

// 2. Initialize pointers.
        var index = 0 // Pointer for the current position in the 'sol' array.
        var i = 0     // Pointer for the current position in 'nums1'.
        var j = 0     // Pointer for the current position in 'nums2'.

// 3. Main loop to fill the 'sol' array.
//    The condition `i + j < m + n` ensures we process exactly m + n elements in total.
        while (i + j < m + n) {
            // This block checks if we have already used all elements from one of the arrays.
            if (i >= m || j >= n) {
                // If all elements from 'nums1' are used, copy the rest of 'nums2'.
                while (j < n) {
                    sol[index] = nums2[j]
                    index++ // Move to the next spot in 'sol'.
                    j++     // Move to the next element in 'nums2'.
                }

                // If all elements from 'nums2' are used, copy the rest of 'nums1'.
                while (i < m) {
                    sol[index] = nums1[i]
                    index++ // Move to the next spot in 'sol'.
                    i++     // Move to the next element in 'nums1'.
                }
            } else {
                // This block executes when both arrays still have elements to compare.
                val element1 = nums1[i]
                val element2 = nums2[j]

                // Compare elements and place the smaller one into 'sol'.
                when {
                    element1 < element2 -> {
                        sol[index] = element1
                        index++ // Move to the next spot in 'sol'.
                        i++     // Move to the next element in 'nums1'.
                    }

                    element1 > element2 -> {
                        sol[index] = element2
                        index++ // Move to the next spot in 'sol'.
                        j++     // Move to the next element in 'nums2'.
                    }

                    else -> { // This handles the case where element1 == element2.
                        // Add both elements to the solution array to maintain order.
                        sol[index] = element1
                        index++ // Move to the next spot in 'sol'.
                        i++     // Move to the next element in 'nums1'.

                        sol[index] = element2
                        index++ // Move to the next spot in 'sol'.
                        j++     // Move to the next element in 'nums2'.
                    }
                }
            }
        }

// 4. Final Step: Copy the sorted result from 'sol' back into 'nums1'.
//    This is required to meet the problem's in-place modification constraint.
        for ((idx, ele) in sol.withIndex()) {
            nums1[idx] = ele
        }
        return nums1
    }

    /**
     * Merges two sorted integer arrays, nums1 and nums2, into nums1 in-place.
     *
     * The algorithm uses a "two-pointer" approach, starting from the end of both arrays.
     * By filling the `nums1` array from its last index backwards, we avoid overwriting
     * elements in `nums1` that have not yet been compared.
     *
     * Time Complexity: O(m + n) - We iterate through each element of both arrays once.
     * Space Complexity: O(1) - The merge is done in-place, using no extra space.
     *
     * @param nums1 The first array, which has a size of m + n and contains buffer space.
     * @param m The number of initialized elements in nums1.
     * @param nums2 The second array.
     * @param n The number of elements in nums2.
     */
    fun mergeSortedArrayOptimised(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
        // Pointer for the last element of the initialized part of nums1.
        var p1 = m - 1
        // Pointer for the last element of nums2.
        var p2 = n - 1
        // Pointer for the last available spot in nums1 (the actual end of the array).
        var i = m + n - 1

        // Iterate backwards from the end of both arrays.
        while (p1 >= 0 && p2 >= 0) {
            // Compare the elements at the pointers and place the larger one
            // at the end of nums1.
            if (nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1]
                p1--
            } else {
                nums1[i] = nums2[p2]
                p2--
            }
            i--
        }

        // If there are any remaining elements in nums2, they must be smaller
        // than all the elements already placed. Copy them to the front of nums1.
        // We don't need to handle remaining elements in nums1 because they are
        // already in their correct sorted position.
        while (p2 >= 0) {
            nums1[i] = nums2[p2]
            p2--
            i--
        }
        return nums1
    }

    companion object {

        @JvmStatic
        fun main(array: Array<String>) {
            val solver = _088MergeSortedArray()

            val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
            val m = 3
            val nums2 = intArrayOf(2, 5, 6)
            val n = 3

            println("Array before merge: ${nums1.contentToString()}")

            // Call the function to modify nums1 in-place.
            val resultArray = solver.mergeSortedArrayOptimised(nums1, m, nums2, n)

            println("Array after merge:  ${resultArray.contentToString()}") // Expected: [1, 2, 2, 3, 5, 6]
        }
    }
}