package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 20, 2025
///////////////////////////////////////////////////////////////////////////

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

class _206ReverseLinkedList {

    // Definition for singly-linked list.
    data class ListNode(var `val`: Int, var next: ListNode? = null)
    // 🧠 Iterative Approach
    // Time Complexity: O(n), Space Complexity: O(1)
    fun reverseListIterative(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr = head

        while (curr != null) {
            val nextTemp = curr.next // Save next node
            curr.next = prev         // Reverse current node pointer
            prev = curr              // Move prev to current
            curr = nextTemp          // Move to next node
        }

        return prev // New head of reversed list
    }

    // 🧠 Recursive Approach
    // Time Complexity: O(n), Space Complexity: O(n) for recursion stack
    fun reverseListRecursive(head: ListNode?): ListNode? {
        // Base case: empty list or only one node
        if (head == null || head.next == null) return head

        // Recursively reverse the rest of the list
        val newHead = reverseListRecursive(head.next)

        // Make head.next point back to head
        head.next!!.next = head
        head.next = null // Cut the old link

        return newHead // Return new head of reversed list
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _206ReverseLinkedList()

            fun createList(values: List<Int>): ListNode? {
                val dummy = ListNode(0)
                var current = dummy
                for (v in values) {
                    current.next = ListNode(v)
                    current = current.next!!
                }
                return dummy.next
            }

            fun printList(head: ListNode?) {
                var node = head
                val result = mutableListOf<Int>()
                while (node != null) {
                    result.add(node.`val`)
                    node = node.next
                }
                println(result)
            }

            val input1 = createList(listOf(1, 2, 3, 4, 5))
            val result1 = solution.reverseListIterative(input1)
            print("Iterative Reverse [1,2,3,4,5]: ")
            printList(result1) // Expected: [5,4,3,2,1]

            val input2 = createList(listOf(1, 2))
            val result2 = solution.reverseListRecursive(input2)
            print("Recursive Reverse [1,2]: ")
            printList(result2) // Expected: [2,1]

            val input3 = createList(emptyList())
            val result3 = solution.reverseListIterative(input3)
            print("Iterative Reverse []: ")
            printList(result3) // Expected: []
        }
    }
}

