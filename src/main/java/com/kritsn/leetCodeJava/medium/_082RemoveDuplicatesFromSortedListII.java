package com.kritsn.leetCodeJava.medium;

import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode 82. Remove Duplicates from Sorted List II
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 */
public class _082RemoveDuplicatesFromSortedListII {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 🧠 Algorithm & Approach:
     * <p>
     * - Use a dummy node before the head to simplify removal at the beginning of the list.
     * - Use a pointer `temp` to point to the last confirmed unique node.
     * - Use a pointer `current` to iterate and detect duplicate values.
     * - Skip over nodes that have duplicate values.
     * - Carefully manage links so only unique nodes remain in the list.
     * <p>
     * Time Complexity: O(n), where n is the number of nodes in the list.
     * Space Complexity: O(1), as we do it in-place without extra space.
     */
    ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node that points to the head — helps handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // temp points to the last node before the current sequence
        ListNode temp = dummy;
        ListNode current = head;

        while (current != null) {
            // Check if current node is the start of a duplicate sequence
            if (current.next != null && current.val == current.next.val) {
                int duplicateVal = current.val;
                // Skip all nodes with the same value
                while (current != null && current.val == duplicateVal) {
                    current = current.next;
                }
                // Link prev to the node after duplicates
                temp.next = current;
            } else {
                // If current is unique, move prev forward
                temp = current;
                current = current.next;
            }
        }

        // Return the next of dummy, which is the new head
        return dummy.next;
    }

    // Helper to create list from array
    private static ListNode createList(List<Integer> arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper to print list
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        _082RemoveDuplicatesFromSortedListII solution = new _082RemoveDuplicatesFromSortedListII();

        // 🔹 Test Case 1
        ListNode head1 = createList(List.of(1, 2, 3, 3, 4, 4, 5));
        System.out.println("Input: [1,2,3,3,4,4,5]");
        ListNode result1 = solution.deleteDuplicates(head1);
        System.out.print("Output: ");
        printList(result1);

        // 🔹 Test Case 2
        ListNode head2 = createList(List.of(1, 1, 1, 2, 3));
        System.out.println("\nInput: [1,1,1,2,3]");
        ListNode result2 = solution.deleteDuplicates(head2);
        System.out.print("Output: ");
        printList(result2);

        // 🔹 Test Case 3 (no duplicates)
        ListNode head3 = createList(List.of(1, 2, 3));
        System.out.println("\nInput: [1,2,3]");
        ListNode result3 = solution.deleteDuplicates(head3);
        System.out.print("Output: ");
        printList(result3);

        // 🔹 Test Case 4 (all duplicates)
        ListNode head4 = createList(List.of(1, 1, 1));
        System.out.println("\nInput: [1,1,1]");
        ListNode result4 = solution.deleteDuplicates(head4);
        System.out.print("Output: ");
        printList(result4);
    }
}
