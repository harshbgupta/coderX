package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

///////////////////////////////////////////////////////////////////////////
// Solution
///////////////////////////////////////////////////////////////////////////
public class _002AddTwoNumbers {

    /**
     * Definition for singly-linked list node.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Adds two numbers represented by linked lists in reverse order.
     *
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the new linked list representing the sum.
     * <p>
     * Time Complexity: O(max(m, n)), where m and n are the lengths of the two lists.
     * Space Complexity: O(max(m, n)), for the output list.
     */
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // Dummy node to simplify result list construction
        ListNode current = dummyHead;
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;

        // Traverse both lists until both are null and no carry remains
        while (p != null || q != null || carry != 0) {
            int x = p != null ? p.val : 0; // Value from l1, or 0 if l1 is exhausted
            int y = q != null ? q.val : 0; // Value from l2, or 0 if l2 is exhausted
            int sum = x + y + carry;
            carry = sum / 10; // Update carry for next iteration

            // Create a new node for the current digit
            current.next = new ListNode(sum % 10);
            current = current.next;

            // Move to next nodes if available
            p = p != null ? p.next : null;
            q = q != null ? q.next : null;
        }

        // Return the next node after dummy, which is the actual result head
        return dummyHead.next;
    }

    // Helper method to create a linked list from a list of integers for easy testing
    private static ListNode createList(List<Integer> numbers) {
        if (numbers.isEmpty()) return null;
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int number : numbers) {
            current.next = new ListNode(number);
            current = current.next;
        }
        return dummyHead.next;
    }

    // Helper method to print a linked list in a readable format
    private static void printList(ListNode node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }
        List<Integer> result = new ArrayList<>();
        ListNode current = node;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(result.get(i));
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        _002AddTwoNumbers solver = new _002AddTwoNumbers();

        System.out.println("--- Test Case 1: Standard Addition ---");
        // l1 = 342 (represented as 2 -> 4 -> 3)
        // l2 = 465 (represented as 5 -> 6 -> 4)
        // sum = 807 (represented as 7 -> 0 -> 8)
        ListNode l1 = createList(List.of(2, 4, 3));
        ListNode l2 = createList(List.of(5, 6, 4));

        System.out.print("List 1: ");
        printList(l1);
        System.out.print("List 2: ");
        printList(l2);

        ListNode sum1 = solver.addTwoNumbers(l1, l2);
        System.out.print("Sum:    ");
        printList(sum1); // Expected: 7 -> 0 -> 8
        System.out.println();

        System.out.println("--- Test Case 2: Different Lengths & Multiple Carries ---");
        // l1 = 9,999,999
        // l2 = 9,999
        // sum = 10,009,998
        ListNode l3 = createList(List.of(9, 9, 9, 9, 9, 9, 9));
        ListNode l4 = createList(List.of(9, 9, 9, 9));

        System.out.print("List 1: ");
        printList(l3);
        System.out.print("List 2: ");
        printList(l4);

        ListNode sum2 = solver.addTwoNumbers(l3, l4);
        System.out.print("Sum:    ");
        printList(sum2); // Expected: 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
        System.out.println();

        System.out.println("--- Test Case 3: Sum results in a new carry digit ---");
        // l1 = 5
        // l2 = 5
        // sum = 10
        ListNode l5 = createList(List.of(5));
        ListNode l6 = createList(List.of(5));

        System.out.print("List 1: ");
        printList(l5);
        System.out.print("List 2: ");
        printList(l6);

        ListNode sum3 = solver.addTwoNumbers(l5, l6);
        System.out.print("Sum:    ");
        printList(sum3); // Expected: 0 -> 1
        System.out.println();
    }
}
