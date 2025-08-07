package com.kritsn.jLeetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
Leetcode 253: Meeting Rooms II

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
*/
public class _253MeetingRoomsII {

    /*
     * 🧠 Algorithm & Approach:
     * - Sort meetings by start time.
     * - Use a Min-Heap (PriorityQueue) to track earliest end time of current meetings.
     * - If new meeting starts after the earliest ending one → reuse room (pop heap).
     * - Else → need new room (push without removing).
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) for heap
     */
    private int getMeeting(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Sort meetings by start time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int[] meeting : intervals) {
            int startTime = meeting[0];
            int endTime = meeting[1];

            // If new meeting starts after the earliest ending one → reuse room (pop heap)
            if (!priorityQueue.isEmpty() && startTime >= priorityQueue.peek()) {
                priorityQueue.poll();
            }
            priorityQueue.add(endTime);
        }

        return priorityQueue.size();
    }

    private List<List<Integer>> sortMeetingBasedOnStartingTime(int[][] intervals) {
        //converting int[][] -> List<List<Integer>>
        List<List<Integer>> listConverted = Arrays.stream(intervals) // Creates a Stream<int[]>
                .map(innerArray -> Arrays.stream(innerArray)   // For each int[], create an IntStream
                        .boxed()              // Convert int to Integer (Stream<Integer>)
                        .collect(Collectors.toList())) // Collect into a List<Integer>
                .toList();

        //sorting Meeting Based On Starting Time
        List<List<Integer>> lists = listConverted.stream().sorted((Comparator.comparingInt(o -> o.get(0)))).toList();
        return lists;
    }

    public static void main(String[] args) {
        _253MeetingRoomsII solution = new _253MeetingRoomsII();

        // 🧪 Test Case 1: Overlapping meetings
        int[][] intervals1 = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Test Case 1: " + solution.getMeeting(intervals1) + " (Expected: 2)");

        // 🧪 Test Case 2: No overlapping meetings
        int[][] intervals2 = new int[][]{{7, 10}, {2, 4}};
        System.out.println("Test Case 2: " + solution.getMeeting(intervals2) + " (Expected: 1)");

        // 🧪 Test Case 3: Fully overlapping
        int[][] intervals3 = new int[][]{{1, 10}, {2, 7}, {3, 19}, {8, 12}};
        System.out.println("Test Case 3: " + solution.getMeeting(intervals3) + " (Expected: 3)");

        // 🧪 Test Case 4: Single meeting
        int[][] intervals4 = new int[][]{{1, 5}};
        System.out.println("Test Case 4: " + solution.getMeeting(intervals4) + " (Expected: 1)");
    }
}

