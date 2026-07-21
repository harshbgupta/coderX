package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 20, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 📄 Problem Statement:
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * Follow-up: Could you do this in one pass?
 */

class _019RemoveNthNodeFromEnd {

    // Definition for singly-linked list.
    data class ListNode(var `val`: Int, var next: ListNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Use two-pointer technique (fast and slow pointers)
     * - Fast pointer is moved n+1 steps ahead of slow
     * - When fast reaches end, slow will be just before the node to remove
     * - Modify slow.next to skip the nth node from end
     *
     * Time Complexity: O(L), where L is the length of the linked list
     * Space Complexity: O(1)
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // Create a dummy node to handle edge cases (like removing the head)
        val dummy = ListNode(0)
        dummy.next = head

        // Initialize two pointers both starting at dummy node
        var fast: ListNode? = dummy
        var slow: ListNode? = dummy

        // Move fast pointer n+1 steps ahead to maintain gap of n between fast and slow
        for (i in 0..n) {
            fast = fast?.next
        }

        // Move both fast and slow one step at a time until fast reaches the end
        while (fast != null) {
            fast = fast.next
            slow = slow?.next
        }

        // Now slow is just before the node to be deleted
        // Skip the node by changing next pointer
        slow?.next = slow?.next?.next

        // Return the new head of the list (might be different if head was removed)
        return dummy.next
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _019RemoveNthNodeFromEnd()

            // Test Case 1: Normal case
            val list1 = createList(listOf(1, 2, 3, 4, 5))
            println("Input: [1,2,3,4,5], n=2 → Output: " + printList(solver.removeNthFromEnd(list1, 2))) // Expected: [1,2,3,5]

            // Test Case 2: Single node
            val list2 = createList(listOf(1))
            println("Input: [1], n=1 → Output: " + printList(solver.removeNthFromEnd(list2, 1))) // Expected: []

            // Test Case 3: Two nodes
            val list3 = createList(listOf(1, 2))
            println("Input: [1,2], n=1 → Output: " + printList(solver.removeNthFromEnd(list3, 1))) // Expected: [1]
        }

        // Utility to create linked list from list of integers
        fun createList(values: List<Int>): ListNode? {
            val dummy = ListNode(0)
            var current = dummy
            for (v in values) {
                current.next = ListNode(v)
                current = current.next!!
            }
            return dummy.next
        }

        // Utility to print linked list as string
        fun printList(head: ListNode?): String {
            val result = mutableListOf<Int>()
            var current = head
            while (current != null) {
                result.add(current.`val`)
                current = current.next
            }
            return result.toString()
        }
    }
}
