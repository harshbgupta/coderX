package com.kritsn.leetCodeJava;

import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 92. Reverse Linked List II
 * You are given the head of a singly linked list and two integers left and right where left &lt;= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */
public class _092ReverseLinkedListII {

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
     * 1. Use a dummy node to handle edge cases.
     * 2. Traverse to node just before position `left`.
     * 3. Reverse nodes between `left` and `right` using head-insertion technique.
     * 4. Reconnect reversed part to the rest of the list.
     * <p>
     * ⏱ Time Complexity: O(n), where n is the length of the list.
     * 🪫 Space Complexity: O(1), since we're reversing in-place.
     */
    ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        // Step 1: Create dummy node to simplify edge handling
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 2: Move `prev` to the node just before `left`
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Step 3: Reverse the sublist from `left` to `right`
        ListNode start = prev.next; // Start of sublist to be reversed
        ListNode then = start.next; // Node that will be moved to the front iteratively

        // Reverse the sublist using head-insertion
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;   // Detach `then`
            then.next = prev.next;    // Insert `then` at the beginning
            prev.next = then;         // Reconnect previous to `then`
            then = start.next;        // Move `then` one step forward
        }

        return dummy.next; // Return the new head
    }

    // 🔹 Helper function to build a list from an array
    private static ListNode buildList(List<Integer> arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 🔹 Helper function to convert list to string for printing
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(" -> ");
            curr = curr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        _092ReverseLinkedListII obj = new _092ReverseLinkedListII();

        // 🧪 Test Case 1:
        ListNode head1 = buildList(List.of(1, 2, 3, 4, 5));
        int left1 = 2;
        int right1 = 4;
        ListNode result1 = obj.reverseBetween(head1, left1, right1);
        System.out.println("Test Case 1: Input = [1,2,3,4,5], left = 2, right = 4");
        System.out.println("Output: " + listToString(result1)); // Expected: 1 -> 4 -> 3 -> 2 -> 5

        // 🧪 Test Case 2:
        ListNode head2 = buildList(List.of(5));
        int left2 = 1;
        int right2 = 1;
        ListNode result2 = obj.reverseBetween(head2, left2, right2);
        System.out.println("\nTest Case 2: Input = [5], left = 1, right = 1");
        System.out.println("Output: " + listToString(result2)); // Expected: 5

        // 🧪 Test Case 3: full reversal
        ListNode head3 = buildList(List.of(1, 2, 3));
        ListNode result3 = obj.reverseBetween(head3, 1, 3);
        System.out.println("\nTest Case 3: Input = [1,2,3], left = 1, right = 3");
        System.out.println("Output: " + listToString(result3)); // Expected: 3 -> 2 -> 1
    }
}
