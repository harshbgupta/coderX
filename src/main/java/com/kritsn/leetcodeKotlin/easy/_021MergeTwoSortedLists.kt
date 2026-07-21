package com.kritsn.leetcodeKotlin.easy
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 19, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 21. Merge Two Sorted Lists
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge them into a new sorted linked list and return its head.
 */

class _021MergeTwoSortedLists {

    /**
     * ListNode definition for singly-linked list.
     */
    data class ListNode(var `val`: Int, var next: ListNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Use a dummy node to ease list building
     * - Traverse both lists, comparing values
     * - Always append the smaller node to the new list
     * - If one list ends, attach the rest of the other
     *
     * Time Complexity: O(n + m) where n and m are lengths of list1 and list2
     * Space Complexity: O(1) — we reuse existing nodes, no new memory allocation
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val dummy = ListNode(0) // Dummy start node
        var tail = dummy        // Tail for building the new list

        var l1 = list1
        var l2 = list2

        // Traverse both lists and merge
        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                tail.next = l1
                l1 = l1.next
            } else {
                tail.next = l2
                l2 = l2.next
            }
            tail = tail.next!!
        }

        // Attach remaining list
        tail.next = l1 ?: l2

        return dummy.next
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _021MergeTwoSortedLists()

            // Helper to convert array to linked list
            fun arrayToList(arr: IntArray): ListNode? {
                val dummy = ListNode(0)
                var current = dummy
                for (num in arr) {
                    current.next = ListNode(num)
                    current = current.next!!
                }
                return dummy.next
            }

            // Helper to convert linked list to string
            fun listToString(node: ListNode?): String {
                val sb = StringBuilder()
                var curr = node
                while (curr != null) {
                    sb.append(curr.`val`).append(" -> ")
                    curr = curr.next
                }
                return sb.append("null").toString()
            }

            val test1L1 = arrayToList(intArrayOf(1, 2, 4))
            val test1L2 = arrayToList(intArrayOf(1, 3, 4))
            val merged1 = solution.mergeTwoLists(test1L1, test1L2)
            println("Test 1: Merged List = ${listToString(merged1)}")

            val test2L1 = arrayToList(intArrayOf())
            val test2L2 = arrayToList(intArrayOf())
            val merged2 = solution.mergeTwoLists(test2L1, test2L2)
            println("Test 2: Merged List = ${listToString(merged2)}")

            val test3L1 = arrayToList(intArrayOf())
            val test3L2 = arrayToList(intArrayOf(0))
            val merged3 = solution.mergeTwoLists(test3L1, test3L2)
            println("Test 3: Merged List = ${listToString(merged3)}")
        }
    }
}
