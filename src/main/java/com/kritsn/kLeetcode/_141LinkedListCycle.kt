package com.kritsn.kLeetcode


/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since July 09, 2025
 */
/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
class _141LinkedListCycle {

    /**
     * Definition for singly-linked list node.
     */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * It's a fact if a linked list has cycle at some point then fast pointer and slow pointer will be same
     *
     * This method implements Floyd's Cycle-Finding Algorithm, also known as the "Tortoise and the Hare" algorithm.
     *
     * https://medium.com/@arifimran5/fast-and-slow-pointer-pattern-in-linked-list-43647869ac99
     * @param head The starting node of the linked list.
     * @return `true` if the linked list has a cycle, otherwise `false`.
     */
    fun hasCycle(head: ListNode?): Boolean {
        // Initialize two pointers, both starting at the head of the list.
        var slow = head
        var fast = head

        // if first node is iteslf is null, it's there is no linked List, so no cycle there
        if (fast==null) return  false

        // Traverse the list. The loop continues as long as the fast pointer
        // and the node ahead of it are not null. This prevents a NullPointerException
        // when accessing `fast.next.next`.
        while (fast?.next != null) {
            // Move the slow pointer one step at a time.
            slow = slow!!.next
            // Move the fast pointer two steps at a time.
            fast = fast.next!!.next

            // If the slow and fast pointers ever meet, it means there is a cycle in the list.
            if (slow == fast) {
                return true
            }
        }

        // If the loop completes, it means the fast pointer reached the end of the list (null).
        // Therefore, no cycle was found.
        return false
    }

    /**
     * Another way to do this using hash map
     */
    fun hasCycleHasMap(head: ListNode?): Boolean {
        val aMap = mutableMapOf<ListNode, Boolean>()

        // loop through the list
        var curNode = head
        while (curNode != null) {
            if (!aMap.contains(curNode)) {
                // never visited before
                aMap[curNode] = true
            } else {
                return true
            }
            curNode = curNode.next
        }
        return false

    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _141LinkedListCycle()

            // Test case 1: No cycle
            val node1 = ListNode(1)
            val node2 = ListNode(1)
            val node3 = ListNode(1)
            node1.next = node2
            node2.next = node3
            println("Test 1 (No cycle): " + solution.hasCycle(node1)) // Expected: false

            // Test case 2: Cycle exists (last node points to node2)
            val node4 = ListNode(1)
            val node5 = ListNode(1)
            val node6 = ListNode(1)
            node4.next = node5
            node5.next = node6
            node6.next = node5 // cycle here
            println("Test 2 (Cycle exists): " + solution.hasCycle(node4)) // Expected: true

            // Test case 3: Single node, no cycle
            val node7 = ListNode(1)
            println("Test 3 (Single node, no cycle): " + solution.hasCycle(node7)) // Expected: false

            // Test case 4: Single node, cycle to itself
            val node8 = ListNode(1)
            node8.next = node8
            println("Test 4 (Single node, cycle to itself): " + solution.hasCycle(node8)) // Expected: true

            // Test case 5: Empty list
            println("Test 5 (Empty list): " + solution.hasCycle(null)) // Expected: false
        }

    }
}
