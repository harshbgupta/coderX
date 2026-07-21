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
 * 📄 Problem Statement:
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * Follow-up: Could you do this in one pass?
 */
public class _019RemoveNthNodeFromEnd {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 🧠 Algorithm & Approach:
     * - Use two-pointer technique (fast and slow pointers)
     * - Fast pointer is moved n+1 steps ahead of slow
     * - When fast reaches end, slow will be just before the node to remove
     * - Modify slow.next to skip the nth node from end
     * <p>
     * Time Complexity: O(L), where L is the length of the linked list
     * Space Complexity: O(1)
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases (like removing the head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize two pointers both starting at dummy node
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast pointer n+1 steps ahead to maintain gap of n between fast and slow
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both fast and slow one step at a time until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Now slow is just before the node to be deleted
        // Skip the node by changing next pointer
        slow.next = slow.next.next;

        // Return the new head of the list (might be different if head was removed)
        return dummy.next;
    }

    // Utility to create linked list from list of integers
    private static ListNode createList(List<Integer> values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) {
            current.next = new ListNode(v);
            current = current.next;
        }
        return dummy.next;
    }

    // Utility to print linked list as string
    private static String printList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        _019RemoveNthNodeFromEnd solver = new _019RemoveNthNodeFromEnd();

        // Test Case 1: Normal case
        ListNode list1 = createList(List.of(1, 2, 3, 4, 5));
        System.out.println("Input: [1,2,3,4,5], n=2 → Output: " + printList(solver.removeNthFromEnd(list1, 2))); // Expected: [1,2,3,5]

        // Test Case 2: Single node
        ListNode list2 = createList(List.of(1));
        System.out.println("Input: [1], n=1 → Output: " + printList(solver.removeNthFromEnd(list2, 1))); // Expected: []

        // Test Case 3: Two nodes
        ListNode list3 = createList(List.of(1, 2));
        System.out.println("Input: [1,2], n=1 → Output: " + printList(solver.removeNthFromEnd(list3, 1))); // Expected: [1]
    }
}
