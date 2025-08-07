package com.kritsn.leetCodeJava;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

public class _019RemoveNthNodeFromOfList {

    private class ListNode {
        ListNode next = null;
        int val = -1;

        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        for (int i =0; i<=n; i++){
            fast = fast.next;
        }

        while (fast!= null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }


    public static void main(String[] args) {
        _019RemoveNthNodeFromOfList solver = new _019RemoveNthNodeFromOfList();

        // Test Case 1: Normal case
        ListNode list1 = solver.createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Input: [1,2,3,4,5], n=2 → Output: " + solver.printList(solver.removeNthFromEnd(list1, 2))); // Expected: [1,2,3,5]

        // Test Case 2: Single node
        ListNode list2 = solver.createList(new int[]{1});
        System.out.println("Input: [1], n=1 → Output: " + solver.printList(solver.removeNthFromEnd(list2, 1))); // Expected: []

        // Test Case 3: Two nodes
        ListNode list3 = solver.createList(new int[]{1, 2});
        System.out.println("Input: [1,2], n=1 → Output: " + solver.printList(solver.removeNthFromEnd(list3, 1))); // Expected: [1]
    }

    // Utility to create linked list from array of integers
    public ListNode createList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Utility to print linked list as string
    public String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(",");
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
