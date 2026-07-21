package com.kritsn.leetcodeKotlin.easy
/**

Leetcode 252: Meeting Rooms

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

*/
class _252MeetingRooms {

    /**
     * 🧠 Algorithm & Approach:
     * - Sort intervals by start time.
     * - If any interval starts before the previous one ends → overlap → return false.
     * - Else, return true (all meetings can be attended).
     *
     * Time Complexity: O(n log n) for sorting
     * Space Complexity: O(1) extra space
     */
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        // Step 1: Sort intervals by start time
        intervals.sortBy { it[0] }

        // Step 2: Check for overlaps
        for (i in 1 until intervals.size) {
            val prevEnd = intervals[i - 1][1]
            val currStart = intervals[i][0]

            // If the current meeting starts before the previous one ends → conflict
            if (currStart < prevEnd) return false
        }

        return true // No overlaps found
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _252MeetingRooms()

            // 🧪 Test Case 1: No overlap
            val intervals1 = arrayOf(intArrayOf(0, 30), intArrayOf(35, 40))
            println("Test Case 1: ${solution.canAttendMeetings(intervals1)} (Expected: true)")

            // 🧪 Test Case 2: Overlapping meetings
            val intervals2 = arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20))
            println("Test Case 2: ${solution.canAttendMeetings(intervals2)} (Expected: false)")

            // 🧪 Test Case 3: Only one meeting
            val intervals3 = arrayOf(intArrayOf(5, 10))
            println("Test Case 3: ${solution.canAttendMeetings(intervals3)} (Expected: true)")
        }
    }
}