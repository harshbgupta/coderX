package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 21, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * Leetcode 82. Remove Duplicates from Sorted List II
 *
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 */
class _082RemoveDuplicatesFromSortedListII {

    data class ListNode(var `val`: Int, var next: ListNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     *
     * - Use a dummy node before the head to simplify removal at the beginning of the list.
     * - Use a pointer `temp` to point to the last confirmed unique node.
     * - Use a pointer `current` to iterate and detect duplicate values.
     * - Skip over nodes that have duplicate values.
     * - Carefully manage links so only unique nodes remain in the list.
     *
     * Time Complexity: O(n), where n is the number of nodes in the list.
     * Space Complexity: O(1), as we do it in-place without extra space.
     */
    fun deleteDuplicates(head: ListNode?): ListNode? {
        // Create a dummy node that points to the head — helps handle edge cases
        val dummy = ListNode(0)
        dummy.next = head

        // temp points to the last node before the current sequence
        var temp = dummy
        var current = head

        while (current != null) {
            // Check if current node is the start of a duplicate sequence
            if (current.next != null && current.`val` == current.next!!.`val`) {
                val duplicateVal = current.`val`
                // Skip all nodes with the same value
                while (current != null && current.`val` == duplicateVal) {
                    current = current.next
                }
                // Link prev to the node after duplicates
                temp.next = current
            } else {
                // If current is unique, move prev forward
                temp = current
                current = current.next
            }
        }

        // Return the next of dummy, which is the new head
        return dummy.next
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _082RemoveDuplicatesFromSortedListII()

            // Helper to create list from array
            fun createList(arr: List<Int>): ListNode? {
                val dummy = ListNode(0)
                var current = dummy
                for (num in arr) {
                    current.next = ListNode(num)
                    current = current.next!!
                }
                return dummy.next
            }

            // Helper to print list
            fun printList(head: ListNode?) {
                var curr = head
                while (curr != null) {
                    print("${curr.`val`} -> ")
                    curr = curr.next
                }
                println("null")
            }

            // 🔹 Test Case 1
            val head1 = createList(listOf(1, 2, 3, 3, 4, 4, 5))
            println("Input: [1,2,3,3,4,4,5]")
            val result1 = solution.deleteDuplicates(head1)
            print("Output: ")
            printList(result1)

            // 🔹 Test Case 2
            val head2 = createList(listOf(1, 1, 1, 2, 3))
            println("\nInput: [1,1,1,2,3]")
            val result2 = solution.deleteDuplicates(head2)
            print("Output: ")
            printList(result2)

            // 🔹 Test Case 3 (no duplicates)
            val head3 = createList(listOf(1, 2, 3))
            println("\nInput: [1,2,3]")
            val result3 = solution.deleteDuplicates(head3)
            print("Output: ")
            printList(result3)

            // 🔹 Test Case 4 (all duplicates)
            val head4 = createList(listOf(1, 1, 1))
            println("\nInput: [1,1,1]")
            val result4 = solution.deleteDuplicates(head4)
            print("Output: ")
            printList(result4)
        }
    }
}
