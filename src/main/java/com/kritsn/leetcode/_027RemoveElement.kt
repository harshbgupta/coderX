package com.kritsn.leetcode

/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 *
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 * Custom Judge:
 *
 * The judge will test your solution with the following code:
 *
 * int[] nums = [...]; // Input array
 * int val = ...; // Value to remove
 * int[] expectedNums = [...]; // The expected answer with correct length.
 *                             // It is sorted with no values equaling val.
 *
 * int k = removeElement(nums, val); // Calls your implementation
 *
 * assert k == expectedNums.length;
 * sort(nums, 0, k); // Sort the first k elements of nums
 * for (int i = 0; i < actualLength; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 *
 */
// Example usage
fun main() {
    val nums = intArrayOf(3, 2, 2, 3)
    val v = 3
    val k = removeElement(nums, v)
    println("k = $k") // Output the number of valid elements
    println("Modified nums: ${nums.slice(0 until k)}") // Output the modified array (first k elements)
}

/**
 * Removes all occurrences of val in nums in-place and returns the number of elements not equal to val.
 * The first k elements of nums will contain the elements not equal to val. The order of elements can be changed.
 *
 * @param nums The input integer array (modified in-place).
 * @param `val` The value to remove from the array.
 * @return The number of elements in nums which are not equal to val.
 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    // `k` acts as the "write index" or "slow pointer". It marks the position
    // where the next element that is NOT equal to `val` should be placed.
    var k = 0

    // Iterate through the entire array using `i` as the "read index" or "fast pointer".
    for (i in nums.indices) {
        // Check if the element at the current read position should be kept.
        if (nums[i] != `val`) {
            // If the element is not the value to be removed, copy it to the
            // current "write" position, which is indicated by `k`.
            nums[k] = nums[i]

            // Increment the write index `k` to prepare for the next valid element.
            k++
        }
        // If nums[i] IS equal to `val`, we do nothing. The read pointer `i`
        // will advance, but the write pointer `k` stays put. This effectively
        // "skips" the element to be removed, as it will be overwritten by a
        // subsequent element that is kept.
    }

    // After the loop, `k` represents the total count of elements that were kept.
    // These elements now occupy the first `k` positions of the `nums` array.
    return k
}