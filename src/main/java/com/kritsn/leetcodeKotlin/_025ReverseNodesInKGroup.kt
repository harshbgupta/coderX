package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 20, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * 📄 Problem Statement:
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 * - The number of nodes in the list is n
 * - 1 <= k <= n <= 5000
 * - 0 <= Node.val <= 1000
 * - You may not alter the values in the list's nodes, only nodes themselves may be changed.
 */

class _025ReverseNodesInKGroup {

    // Definition for singly-linked list.
    data class ListNode(var `val`: Int, var next: ListNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * basically you have to reach (n-k)th node to and connect it's next to (n-k)th next next node as shown beloe
     * ((n-k)th Node).next = ((n-k)th Node).next.next
     *
     * now use fast pointer and slow pointer approach, run a saparate loop to 1 to K and make the fast pointer go
     * afer that move slow and fast pointer simutenously, till fast pointer.next  == null. this means your slow pointer
     * will be at reaches to (n-k)th this is waht we wanted, now do the ((n-k)th Node).next = ((n-k)th Node).next.next
     *
     * - Traverse the list and count the total number of nodes.
     * - For every complete group of size k:
     *    - Reverse that segment.
     *    - Connect the reversed segment to the previously reversed group.
     * - Use a helper function to reverse a segment between two nodes.
     *
     * Time Complexity: O(n), where n is the number of nodes in the list.
     * Space Complexity: O(1), since we're reversing in-place.
     */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null || k == 1) return head

        // Dummy node is used to simplify handling of head reversals
        val dummy = ListNode(0)
        dummy.next = head

        // These two pointers will help to keep track of group start and end
        var prevGroupEnd: ListNode = dummy
        var groupStart: ListNode? = head

        while (true) {
            // Set the kth node from the group start
            var kth = getKthNode(groupStart, k)
            if (kth == null) break // Less than k nodes left — no need to reverse

            val currentGroupEnd = kth
            val nextGroupStart = kth.next

            // Break the chain to reverse the group cleanly
            currentGroupEnd.next = null

            // Reverse current group
            val reversedHead = reverseList(groupStart)

            // Connect previous group to the newly reversed head
            prevGroupEnd.next = reversedHead

            // Connect end of the reversed group to the start of the next group
            groupStart?.next = nextGroupStart

            // Move pointers forward for the next group
            prevGroupEnd = groupStart!!
            groupStart = nextGroupStart
        }

        return dummy.next
    }

    /**
     * 🧠 Utility Method: Get the k-th node from the current node
     *
     * @param curr the current node to start counting from
     * @param k number of steps to take
     * @return the k-th node or null if not enough nodes
     */
    private fun getKthNode(curr: ListNode?, k: Int): ListNode? {
        var node = curr
        var count = 1
        while (node != null && count < k) {
            node = node.next
            count++
        }
        return node
    }

    /**
     * 🧠 Utility Method: Reverse a linked list and return the new head
     *
     * Standard iterative approach using three pointers
     */
    private fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr = head
        while (curr != null) {
            val next = curr.next      // store next node
            curr.next = prev          // reverse current pointer
            prev = curr               // move prev forward
            curr = next               // move current forward
        }
        return prev // new head of reversed list
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Helper to build linked list from list
            fun buildList(values: List<Int>): ListNode? {
                val dummy = ListNode(0)
                var current = dummy
                for (v in values) {
                    current.next = ListNode(v)
                    current = current.next!!
                }
                return dummy.next
            }

            // Helper to convert linked list to list
            fun toList(head: ListNode?): List<Int> {
                val result = mutableListOf<Int>()
                var curr = head
                while (curr != null) {
                    result.add(curr.`val`)
                    curr = curr.next
                }
                return result
            }

            // Test case 1
            val list1 = buildList(listOf(1, 2, 3, 4, 5))
            val k1 = 2
            val reversed1 = _025ReverseNodesInKGroup().reverseKGroup(list1, k1)
            println("Test 1 Output: ${toList(reversed1)}") // [2, 1, 4, 3, 5]

            // Test case 2
            val list2 = buildList(listOf(1, 2, 3, 4, 5))
            val k2 = 3
            val reversed2 = _025ReverseNodesInKGroup().reverseKGroup(list2, k2)
            println("Test 2 Output: ${toList(reversed2)}") // [3, 2, 1, 4, 5]

            // Test case 3: Less than k nodes
            val list3 = buildList(listOf(1, 2))
            val k3 = 3
            val reversed3 = _025ReverseNodesInKGroup().reverseKGroup(list3, k3)
            println("Test 3 Output: ${toList(reversed3)}") // [1, 2]
        }
    }
}
