package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 21, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Leetcode - Rotate List
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 */

class _061RotateList {

    data class ListNode(var `val`: Int, var next: ListNode? = null)

    /**
     * https://youtu.be/uT7YI7XbTY8?feature=shared
     * 🧠 Algorithm & Approach:
     * 1. Count the length of the list.
     * 2. Make the list circular by connecting tail to head.
     * 3. Find the new tail node after (length - k % length) steps.
     * 4. Break the circular link and return the new head.
     *
     * Time Complexity: O(n) — Traverse list to count and break at correct spot.
     * Space Complexity: O(1) — In-place rotation.
     */
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        // Edge case: if list is empty or has one node or no rotation needed
        if (head == null || head.next == null || k == 0) return head

        var length = 1
        var tail = head

        // Count total number of nodes and get the tail
        while (tail?.next != null) {
            tail = tail.next
            length++
        }

        // Form a circular list by connecting tail to head
        tail?.next = head

        // Find the number of steps to new tail
        val stepsToNewTail = length - (k % length)

        // Traverse to the new tail node
        var newTail = head
        for (i in 1 until stepsToNewTail) {
            newTail = newTail?.next
        }

        // New head is next of new tail
        val newHead = newTail?.next

        // Break the circular link
        newTail?.next = null

        return newHead
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _061RotateList()

            // Helper function to create linked list from list
            fun createLinkedList(values: List<Int>): ListNode? {
                if (values.isEmpty()) return null
                val head = ListNode(values[0])
                var current = head
                for (value in values.drop(1)) {
                    current.next = ListNode(value)
                    current = current.next!!
                }
                return head
            }

            // Helper function to print linked list
            fun printLinkedList(head: ListNode?) {
                var curr = head
                val result = mutableListOf<Int>()
                while (curr != null) {
                    result.add(curr.`val`)
                    curr = curr.next
                }
                println(result)
            }

            // Test Case 1
            val input1 = createLinkedList(listOf(1, 2, 3, 4, 5))
            val result1 = solution.rotateRight(input1, 2)
            println("🔁 Rotated by 2: ")
            printLinkedList(result1) // Output: [4, 5, 1, 2, 3]

            // Test Case 2
            val input2 = createLinkedList(listOf(0, 1, 2))
            val result2 = solution.rotateRight(input2, 4)
            println("🔁 Rotated by 4: ")
            printLinkedList(result2) // Output: [2, 0, 1]

            // Test Case 3
            val input3 = createLinkedList(listOf(1))
            val result3 = solution.rotateRight(input3, 10)
            println("🔁 Rotated by 10 (single node): ")
            printLinkedList(result3) // Output: [1]
        }
    }
}
