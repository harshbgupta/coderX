package com.kritsn.leetCodeJava.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode - Rotate List
 * <p>
 * Given the head of a linked list, rotate the list to the right by k places.
 */
public class _061RotateList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * https://youtu.be/uT7YI7XbTY8?feature=shared
     * 🧠 Algorithm & Approach:
     * 1. Count the length of the list.
     * 2. Make the list circular by connecting tail to head.
     * 3. Find the new tail node after (length - k % length) steps.
     * 4. Break the circular link and return the new head.
     * <p>
     * Time Complexity: O(n) — Traverse list to count and break at correct spot.
     * Space Complexity: O(1) — In-place rotation.
     */
    ListNode rotateRight(ListNode head, int k) {
        // Edge case: if list is empty or has one node or no rotation needed
        if (head == null || head.next == null || k == 0) return head;

        int length = 1;
        ListNode tail = head;

        // Count total number of nodes and get the tail
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Form a circular list by connecting tail to head
        tail.next = head;

        // Find the number of steps to new tail
        int stepsToNewTail = length - (k % length);

        // Traverse to the new tail node
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // New head is next of new tail
        ListNode newHead = newTail.next;

        // Break the circular link
        newTail.next = null;

        return newHead;
    }

    // Helper function to create linked list from list
    private static ListNode createLinkedList(List<Integer> values) {
        if (values.isEmpty()) return null;
        ListNode head = new ListNode(values.get(0));
        ListNode current = head;
        for (int value : values.subList(1, values.size())) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return head;
    }

    // Helper function to print linked list
    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        List<Integer> result = new ArrayList<>();
        while (curr != null) {
            result.add(curr.val);
            curr = curr.next;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        _061RotateList solution = new _061RotateList();

        // Test Case 1
        ListNode input1 = createLinkedList(List.of(1, 2, 3, 4, 5));
        ListNode result1 = solution.rotateRight(input1, 2);
        System.out.println("🔁 Rotated by 2: ");
        printLinkedList(result1); // Output: [4, 5, 1, 2, 3]

        // Test Case 2
        ListNode input2 = createLinkedList(List.of(0, 1, 2));
        ListNode result2 = solution.rotateRight(input2, 4);
        System.out.println("🔁 Rotated by 4: ");
        printLinkedList(result2); // Output: [2, 0, 1]

        // Test Case 3
        ListNode input3 = createLinkedList(List.of(1));
        ListNode result3 = solution.rotateRight(input3, 10);
        System.out.println("🔁 Rotated by 10 (single node): ");
        printLinkedList(result3); // Output: [1]
    }
}
