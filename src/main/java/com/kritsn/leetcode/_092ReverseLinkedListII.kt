package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 20, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 92. Reverse Linked List II
 * You are given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */

class _092ReverseLinkedListII {

    // Definition for singly-linked list.
    data class ListNode(var `val`: Int, var next: ListNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * 1. Use a dummy node to handle edge cases.
     * 2. Traverse to node just before position `left`.
     * 3. Reverse nodes between `left` and `right` using head-insertion technique.
     * 4. Reconnect reversed part to the rest of the list.
     *
     * ⏱ Time Complexity: O(n), where n is the length of the list.
     * 🪫 Space Complexity: O(1), since we're reversing in-place.
     */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null || left == right) return head

        // Step 1: Create dummy node to simplify edge handling
        val dummy = ListNode(0)
        dummy.next = head

        // Step 2: Move `prev` to the node just before `left`
        var prev: ListNode? = dummy
        for (i in 1 until left) {
            prev = prev?.next
        }

        // Step 3: Reverse the sublist from `left` to `right`
        val start = prev?.next                 // Start of sublist to be reversed
        var then = start?.next                 // Node that will be moved to the front iteratively

        // Reverse the sublist using head-insertion
        for (i in 0 until (right - left)) {
            start?.next = then?.next           // Detach `then`
            then?.next = prev?.next            // Insert `then` at the beginning
            prev?.next = then                  // Reconnect previous to `then`
            then = start?.next                 // Move `then` one step forward
        }

        return dummy.next                      // Return the new head
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = _092ReverseLinkedListII()

            // 🔹 Helper function to build a list from an array
            fun buildList(arr: List<Int>): ListNode? {
                val dummy = ListNode(0)
                var curr = dummy
                for (num in arr) {
                    curr.next = ListNode(num)
                    curr = curr.next!!
                }
                return dummy.next
            }

            // 🔹 Helper function to convert list to string for printing
            fun listToString(head: ListNode?): String {
                val sb = StringBuilder()
                var curr = head
                while (curr != null) {
                    sb.append(curr.`val`)
                    if (curr.next != null) sb.append(" -> ")
                    curr = curr.next
                }
                return sb.toString()
            }

            // 🧪 Test Case 1:
            val head1 = buildList(listOf(1, 2, 3, 4, 5))
            val left1 = 2
            val right1 = 4
            val result1 = obj.reverseBetween(head1, left1, right1)
            println("Test Case 1: Input = [1,2,3,4,5], left = 2, right = 4")
            println("Output: ${listToString(result1)}") // Expected: 1 -> 4 -> 3 -> 2 -> 5

            // 🧪 Test Case 2:
            val head2 = buildList(listOf(5))
            val left2 = 1
            val right2 = 1
            val result2 = obj.reverseBetween(head2, left2, right2)
            println("\nTest Case 2: Input = [5], left = 1, right = 1")
            println("Output: ${listToString(result2)}") // Expected: 5

            // 🧪 Test Case 3: full reversal
            val head3 = buildList(listOf(1, 2, 3))
            val result3 = obj.reverseBetween(head3, 1, 3)
            println("\nTest Case 3: Input = [1,2,3], left = 1, right = 3")
            println("Output: ${listToString(result3)}") // Expected: 3 -> 2 -> 1
        }
    }
}
