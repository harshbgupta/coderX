package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 17, 2025
///////////////////////////////////////////////////////////////////////////

/*
Problem:
You are given a sorted unique integer array nums.
Return the smallest list of ranges covering all numbers in the array exactly.
Each range [a, b] should be formatted as:
- "a->b" if a != b
- "a" if a == b
*/

class _228SummaryRanges {

    /**
     * 🧠 Algorithm & Approach:
     * --------------------------------------------
     * 1. Traverse through the array and find segments where consecutive numbers exist.
     * 2. Maintain a start pointer for the beginning of a new range.
     * 3. Whenever the current number is NOT consecutive, close the previous range.
     * 4. Add the formatted range string to the result list.
     * 5. After loop ends, make sure to add the last pending range.
     *
     * ✅ Time Complexity: O(n) — Single pass through the array.
     * ✅ Space Complexity: O(1) extra (excluding result list).
     */
    fun summaryRanges(nums: IntArray): List<String> {
        val result = mutableListOf<String>()

        // Edge case: empty input
        if (nums.isEmpty()) return result

        // Start of the current range
        var start = nums[0]

        // Iterate through 1..n to compare with previous element
        for (i in 1 until nums.size) {
            // If current is not consecutive to previous
            if (nums[i] != nums[i - 1] + 1) {
                // Add the completed range to result
                if (start == nums[i - 1]) {
                    result.add("$start")         // Single element range
                } else {
                    result.add("$start->${nums[i - 1]}") // Multiple elements
                }
                // Start a new range
                start = nums[i]
            }
        }

        // Add the last range (not handled in loop)
        if (start == nums.last()) {
            result.add("$start")
        } else {
            result.add("$start->${nums.last()}")
        }

        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = _228SummaryRanges()

            val nums1 = intArrayOf(0, 1, 2, 4, 5, 7)
            println("Test 1: Input = ${nums1.joinToString()}")
            println("Output = ${obj.summaryRanges(nums1)}")
            println("Expected = [\"0->2\", \"4->5\", \"7\"]\n")

            val nums2 = intArrayOf(0, 2, 3, 4, 6, 8, 9)
            println("Test 2: Input = ${nums2.joinToString()}")
            println("Output = ${obj.summaryRanges(nums2)}")
            println("Expected = [\"0\", \"2->4\", \"6\", \"8->9\"]\n")

            val nums3 = intArrayOf(1)
            println("Test 3: Input = ${nums3.joinToString()}")
            println("Output = ${obj.summaryRanges(nums3)}")
            println("Expected = [\"1\"]\n")

            val nums4 = intArrayOf()
            println("Test 4: Input = ${nums4.joinToString()}")
            println("Output = ${obj.summaryRanges(nums4)}")
            println("Expected = []\n")

            val nums5 = intArrayOf(1, 3, 5, 7)
            println("Test 5: Input = ${nums5.joinToString()}")
            println("Output = ${obj.summaryRanges(nums5)}")
            println("Expected = [\"1\", \"3\", \"5\", \"7\"]")
        }
    }
}
