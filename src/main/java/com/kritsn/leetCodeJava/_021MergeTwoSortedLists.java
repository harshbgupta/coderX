package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 21. Merge Two Sorted Lists
 * <p>
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge them into a new sorted linked list and return its head.
 */
public class _021MergeTwoSortedLists {

    /**
     * ListNode definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 🧠 Algorithm & Approach:
     * - Use a dummy node to ease list building
     * - Traverse both lists, comparing values
     * - Always append the smaller node to the new list
     * - If one list ends, attach the rest of the other
     * <p>
     * Time Complexity: O(n + m) where n and m are lengths of list1 and list2
     * Space Complexity: O(1) — we reuse existing nodes, no new memory allocation
     */
    ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // Dummy start node
        ListNode tail = dummy;            // Tail for building the new list

        ListNode l1 = list1;
        ListNode l2 = list2;

        // Traverse both lists and merge
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // Attach remaining list
        tail.next = l1 != null ? l1 : l2;

        return dummy.next;
    }

    // Helper to convert array to linked list
    private static ListNode arrayToList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper to convert linked list to string
    private static String listToString(ListNode node) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = node;
        while (curr != null) {
            sb.append(curr.val).append(" -> ");
            curr = curr.next;
        }
        return sb.append("null").toString();
    }

    public static void main(String[] args) {
        _021MergeTwoSortedLists solution = new _021MergeTwoSortedLists();

        ListNode test1L1 = arrayToList(new int[]{1, 2, 4});
        ListNode test1L2 = arrayToList(new int[]{1, 3, 4});
        ListNode merged1 = solution.mergeTwoLists(test1L1, test1L2);
        System.out.println("Test 1: Merged List = " + listToString(merged1));

        ListNode test2L1 = arrayToList(new int[]{});
        ListNode test2L2 = arrayToList(new int[]{});
        ListNode merged2 = solution.mergeTwoLists(test2L1, test2L2);
        System.out.println("Test 2: Merged List = " + listToString(merged2));

        ListNode test3L1 = arrayToList(new int[]{});
        ListNode test3L2 = arrayToList(new int[]{0});
        ListNode merged3 = solution.mergeTwoLists(test3L1, test3L2);
        System.out.println("Test 3: Merged List = " + listToString(merged3));
    }
}
