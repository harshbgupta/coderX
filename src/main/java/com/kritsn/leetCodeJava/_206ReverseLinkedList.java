package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []

Constraints:
- The number of nodes in the list is in the range [0, 5000].
- -5000 <= Node.val <= 5000
*/
public class _206ReverseLinkedList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 🧠 Iterative Approach
    // Time Complexity: O(n), Space Complexity: O(1)
    ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next; // Save next node
            curr.next = prev;              // Reverse current node pointer
            prev = curr;                   // Move prev to current
            curr = nextTemp;               // Move to next node
        }

        return prev; // New head of reversed list
    }

    // 🧠 Recursive Approach
    // Time Complexity: O(n), Space Complexity: O(n) for recursion stack
    ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or only one node
        if (head == null || head.next == null) return head;

        // Recursively reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);

        // Make head.next point back to head
        head.next.next = head;
        head.next = null; // Cut the old link

        return newHead; // Return new head of reversed list
    }

    private static ListNode createList(List<Integer> values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) {
            current.next = new ListNode(v);
            current = current.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode node = head;
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        _206ReverseLinkedList solution = new _206ReverseLinkedList();

        ListNode input1 = createList(List.of(1, 2, 3, 4, 5));
        ListNode result1 = solution.reverseListIterative(input1);
        System.out.print("Iterative Reverse [1,2,3,4,5]: ");
        printList(result1); // Expected: [5,4,3,2,1]

        ListNode input2 = createList(List.of(1, 2));
        ListNode result2 = solution.reverseListRecursive(input2);
        System.out.print("Recursive Reverse [1,2]: ");
        printList(result2); // Expected: [2,1]

        ListNode input3 = createList(List.of());
        ListNode result3 = solution.reverseListIterative(input3);
        System.out.print("Iterative Reverse []: ");
        printList(result3); // Expected: []
    }
}
