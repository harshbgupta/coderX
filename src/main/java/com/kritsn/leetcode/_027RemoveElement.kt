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
    var k = 0 // Counter for elements not equal to val
    for (i in nums.indices) {
        // If the current element is not val, keep it in the array
        if (nums[i] != `val`) {
            nums[k] = nums[i] // Place the element at the next position
            k++ // Increment count of valid elements
        }
        // If nums[i] == val, do nothing (it will be overwritten or ignored)
    }
    return k // Return the count of elements not equal to val
}