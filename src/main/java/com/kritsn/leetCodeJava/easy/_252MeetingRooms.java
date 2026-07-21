package com.kritsn.leetCodeJava.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode 252: Meeting Rooms
 * <p>
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 */
public class _252MeetingRooms {

    /**
     * 🧠 Algorithm & Approach:
     * - Sort intervals by start time.
     * - If any interval starts before the previous one ends → overlap → return false.
     * - Else, return true (all meetings can be attended).
     * <p>
     * Time Complexity: O(n log n) for sorting
     * Space Complexity: O(1) extra space
     */
    boolean canAttendMeetings(int[][] intervals) {
        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Step 2: Check for overlaps
        for (int i = 1; i < intervals.length; i++) {
            int prevEnd = intervals[i - 1][1];
            int currStart = intervals[i][0];

            // If the current meeting starts before the previous one ends → conflict
            if (currStart < prevEnd) return false;
        }

        return true; // No overlaps found
    }

    public static void main(String[] args) {
        _252MeetingRooms solution = new _252MeetingRooms();

        // 🧪 Test Case 1: No overlap
        int[][] intervals1 = {{0, 30}, {35, 40}};
        System.out.println("Test Case 1: " + solution.canAttendMeetings(intervals1) + " (Expected: true)");

        // 🧪 Test Case 2: Overlapping meetings
        int[][] intervals2 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Test Case 2: " + solution.canAttendMeetings(intervals2) + " (Expected: false)");

        // 🧪 Test Case 3: Only one meeting
        int[][] intervals3 = {{5, 10}};
        System.out.println("Test Case 3: " + solution.canAttendMeetings(intervals3) + " (Expected: true)");
    }
}
