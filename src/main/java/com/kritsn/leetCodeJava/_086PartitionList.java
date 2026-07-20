package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode: Partition List
 * <p>
 * Given the head of a linked list and a value x, partition it such that all nodes less than x
 * come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class _086PartitionList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 🧠 Algorithm & Approach:
    //
    // 1. Use two dummy nodes: one for nodes < x, another for nodes >= x.
    // 2. Traverse the list and append nodes to the appropriate dummy list.
    // 3. After traversal, join the two lists.
    // 4. Return the head of the new list starting from less-than partition.
    //
    // Time Complexity: O(n) - Traverse each node once.
    // Space Complexity: O(1) - No extra space except pointers.

    ListNode partition(ListNode head, int x) {
        // Dummy nodes to form two separate lists
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);

        // Pointers to build the two lists
        ListNode less = lessHead;
        ListNode greater = greaterHead;

        ListNode current = head;

        // Traverse the original list
        while (current != null) {
            if (current.val < x) {
                // Append to less-than list
                less.next = current;
                less = less.next;
            } else {
                // Append to greater-or-equal list
                greater.next = current;
                greater = greater.next;
            }
            current = current.next;
        }

        // Important: End the greater list
        greater.next = null;

        // Connect the two partitions
        less.next = greaterHead.next;

        // Return head of the rearranged list
        return lessHead.next;
    }

    private static ListNode createList(List<Integer> values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
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
        _086PartitionList solution = new _086PartitionList();

        // 🧪 Test Case 1
        ListNode head1 = createList(List.of(1, 4, 3, 2, 5, 2));
        int x1 = 3;
        System.out.println("Test Case 1: Input = [1,4,3,2,5,2], x = 3");
        printList(solution.partition(head1, x1)); // Output: [1,2,2,4,3,5]

        // 🧪 Test Case 2
        ListNode head2 = createList(List.of(2, 1));
        int x2 = 2;
        System.out.println("Test Case 2: Input = [2,1], x = 2");
        printList(solution.partition(head2, x2)); // Output: [1,2]

        // 🧪 Edge Case: Empty list
        ListNode head3 = createList(List.of());
        int x3 = 0;
        System.out.println("Test Case 3: Input = [], x = 0");
        printList(solution.partition(head3, x3)); // Output: []
    }
}
