package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 21, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Leetcode: Partition List
 *
 * Given the head of a linked list and a value x, partition it such that all nodes less than x
 * come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */

class _086PartitionList {

    data class ListNode(var `val`: Int, var next: ListNode? = null)

    // 🧠 Algorithm & Approach:
    //
    // 1. Use two dummy nodes: one for nodes < x, another for nodes >= x.
    // 2. Traverse the list and append nodes to the appropriate dummy list.
    // 3. After traversal, join the two lists.
    // 4. Return the head of the new list starting from less-than partition.
    //
    // Time Complexity: O(n) - Traverse each node once.
    // Space Complexity: O(1) - No extra space except pointers.

    fun partition(head: ListNode?, x: Int): ListNode? {
        // Dummy nodes to form two separate lists
        val lessHead = ListNode(0)
        val greaterHead = ListNode(0)

        // Pointers to build the two lists
        var less = lessHead
        var greater = greaterHead

        var current = head

        // Traverse the original list
        while (current != null) {
            if (current.`val` < x) {
                // Append to less-than list
                less.next = current
                less = less.next!!
            } else {
                // Append to greater-or-equal list
                greater.next = current
                greater = greater.next!!
            }
            current = current.next
        }

        // Important: End the greater list
        greater.next = null

        // Connect the two partitions
        less.next = greaterHead.next

        // Return head of the rearranged list
        return lessHead.next
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            fun createList(values: List<Int>): ListNode? {
                val dummy = ListNode(0)
                var current = dummy
                for (value in values) {
                    current.next = ListNode(value)
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

            val solution = _086PartitionList()

            // 🧪 Test Case 1
            val head1 = createList(listOf(1, 4, 3, 2, 5, 2))
            val x1 = 3
            println("Test Case 1: Input = [1,4,3,2,5,2], x = 3")
            printList(solution.partition(head1, x1)) // Output: [1,2,2,4,3,5]

            // 🧪 Test Case 2
            val head2 = createList(listOf(2, 1))
            val x2 = 2
            println("Test Case 2: Input = [2,1], x = 2")
            printList(solution.partition(head2, x2)) // Output: [1,2]

            // 🧪 Edge Case: Empty list
            val head3 = createList(listOf())
            val x3 = 0
            println("Test Case 3: Input = [], x = 0")
            printList(solution.partition(head3, x3)) // Output: []
        }
    }
}
