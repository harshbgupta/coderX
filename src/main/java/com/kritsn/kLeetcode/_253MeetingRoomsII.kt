package com.kritsn.kLeetcode
/*
Leetcode 253: Meeting Rooms II

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
*/

import java.util.PriorityQueue

class _253MeetingRoomsII {

    /**
     * 🧠 Algorithm & Approach:
     * - Sort meetings by start time.
     * - Use a Min-Heap (PriorityQueue) to track earliest end time of current meetings.
     * - If new meeting starts after the earliest ending one → reuse room (pop heap).
     * - Else → need new room (push without removing).
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) for heap
     */
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        if (intervals.isEmpty()) return 0

        // Step 1: Sort meetings by start time
        intervals.sortBy { it[0] }

        // Step 2: Min Heap to track meeting end times
        val minHeap = PriorityQueue<Int>()

        // Step 3: Process each meeting
        for ((start, end) in intervals) {
            // Free up room if previous meeting ended before current one starts
            if (minHeap.isNotEmpty() && start >= minHeap.peek()) {
                minHeap.poll()
            }
            // Allocate room (whether reused or new)
            minHeap.offer(end)
        }

        // Number of active rooms is the heap size
        return minHeap.size
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _253MeetingRoomsII()

            // 🧪 Test Case 1: Overlapping meetings
            val intervals1 = arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20))
            println("Test Case 1: ${solution.minMeetingRooms(intervals1)} (Expected: 2)")

            // 🧪 Test Case 2: No overlapping meetings
            val intervals2 = arrayOf(intArrayOf(7, 10), intArrayOf(2, 4))
            println("Test Case 2: ${solution.minMeetingRooms(intervals2)} (Expected: 1)")

            // 🧪 Test Case 3: Fully overlapping
            val intervals3 = arrayOf(intArrayOf(1, 10), intArrayOf(2, 7), intArrayOf(3, 19), intArrayOf(8, 12))
            println("Test Case 3: ${solution.minMeetingRooms(intervals3)} (Expected: 3)")

            // 🧪 Test Case 4: Single meeting
            val intervals4 = arrayOf(intArrayOf(1, 5))
            println("Test Case 4: ${solution.minMeetingRooms(intervals4)} (Expected: 1)")
        }
    }
}