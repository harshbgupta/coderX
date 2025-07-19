package com.kritsn.leetcode



///////////////////////////////////////////////////////////////////////////
// Solution
///////////////////////////////////////////////////////////////////////////
class _002AddTwoNumbers {
    /**
     * Definition for singly-linked list node.
     */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * Adds two numbers represented by linked lists in reverse order.
     *
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the new linked list representing the sum.
     *
     * Time Complexity: O(max(m, n)), where m and n are the lengths of the two lists.
     * Space Complexity: O(max(m, n)), for the output list.
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummyHead = ListNode(0) // Dummy node to simplify result list construction
        var current = dummyHead
        var p = l1
        var q = l2
        var carry = 0

        // Traverse both lists until both are null and no carry remains
        while (p != null || q != null || carry != 0) {
            val x = p?.`val` ?: 0 // Value from l1, or 0 if l1 is exhausted
            val y = q?.`val` ?: 0 // Value from l2, or 0 if l2 is exhausted
            val sum = x + y + carry
            carry = sum / 10 // Update carry for next iteration

            // Create a new node for the current digit
            current.next = ListNode(sum % 10)
            current = current.next!!

            // Move to next nodes if available
            p = p?.next
            q = q?.next
        }

        // Return the next node after dummy, which is the actual result head
        return dummyHead.next
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _002AddTwoNumbers()
            // Helper function to create a linked list from a list of integers for easy testing
            fun createList(numbers: List<Int>): ListNode? {
                if (numbers.isEmpty()) return null
                val dummyHead = ListNode(0)
                var current = dummyHead
                for (number in numbers) {
                    current.next = ListNode(number)
                    current = current.next!!
                }
                return dummyHead.next
            }

            // Helper function to print a linked list in a readable format
            fun printList(node: ListNode?) {
                if (node == null) {
                    println("[]")
                    return
                }
                var current: ListNode? = node
                val result = mutableListOf<Int>()
                while (current != null) {
                    result.add((current as ListNode).`val`)
                    current = (current as ListNode).next
                }
                println(result.joinToString(" -> "))
            }

            println("--- Test Case 1: Standard Addition ---")
            // l1 = 342 (represented as 2 -> 4 -> 3)
            // l2 = 465 (represented as 5 -> 6 -> 4)
            // sum = 807 (represented as 7 -> 0 -> 8)
            val l1 = createList(listOf(2, 4, 3))
            val l2 = createList(listOf(5, 6, 4))

            print("List 1: ")
            printList(l1)
            print("List 2: ")
            printList(l2)

            val sum1 = solver.addTwoNumbers(l1, l2)
            print("Sum:    ")
            printList(sum1) // Expected: 7 -> 0 -> 8
            println()


            println("--- Test Case 2: Different Lengths & Multiple Carries ---")
            // l1 = 9,999,999
            // l2 = 9,999
            // sum = 10,009,998
            val l3 = createList(listOf(9, 9, 9, 9, 9, 9, 9))
            val l4 = createList(listOf(9, 9, 9, 9))

            print("List 1: ")
            printList(l3)
            print("List 2: ")
            printList(l4)

            val sum2 = solver.addTwoNumbers(l3, l4)
            print("Sum:    ")
            printList(sum2) // Expected: 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
            println()


            println("--- Test Case 3: Sum results in a new carry digit ---")
            // l1 = 5
            // l2 = 5
            // sum = 10
            val l5 = createList(listOf(5))
            val l6 = createList(listOf(5))

            print("List 1: ")
            printList(l5)
            print("List 2: ")
            printList(l6)

            val sum3 = solver.addTwoNumbers(l5, l6)
            print("Sum:    ")
            printList(sum3) // Expected: 0 -> 1
            println()
        }

    }
}