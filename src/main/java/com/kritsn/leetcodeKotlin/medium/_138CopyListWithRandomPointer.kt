package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 19, 2025
///////////////////////////////////////////////////////////////////////////

/*
138. Copy List with Random Pointer

You are given a linked list of length n such that each node contains an additional random pointer,
which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list
such that the pointers in the original list and copied list represent the same list state.

Return the head of the copied linked list.
*/

class _138CopyListWithRandomPointer {

    data class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    /*
    https://youtu.be/8ze7Zopdsaw?feature=shared
    🧠 Algorithm & Approach:
    Step 1: Clone nodes in-between original list nodes.
    Step 2: Set the random pointer of copied nodes.
    Step 3: Separate the interleaved list into original and copied lists.

    Time Complexity: O(n)
    Space Complexity: O(1) (no hashmap used, purely in-place)
    */
    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null

        var curr = node

        // Step 1: Clone each node and insert the copy right after the original node
        while (curr != null) {
            val copy = Node(curr.`val`)
            copy.next = curr.next
            curr.next = copy
            curr = copy.next
        }

        // Step 2: Assign random pointers to the copied nodes
        curr = node
        while (curr != null) {
            curr.next?.random = curr.random?.next
            curr = curr.next?.next
        }

        // Step 3: Separate original and copied nodes
        curr = node
        val pseudoHead = Node(0)
        var copyCurr = pseudoHead

        while (curr != null) {
            val copy = curr.next
            val nextOrig = copy?.next

            copyCurr.next = copy
            copyCurr = copy!!

            curr.next = nextOrig // restore original list
            curr = nextOrig
        }

        return pseudoHead.next
    }

    /*
    https://youtu.be/8ze7Zopdsaw?feature=shared
    🧠 Algorithm & Approach:
    Step 1: Clone nodes in-between original list nodes.
    Step 2: save in map for future use
    Step 3: now again transeverse to old node, take the new value from map

    Time Complexity: O(n)
    Space Complexity: O(n) (used hashmap used, no purely in-place)
    */
    fun copyRandomListNoOptimised(node: Node?): Node? {
        if (node == null) {
            return null
        }

        val originalToCopy = mutableMapOf<Node, Node>()

        var currentNode = node
        var prevNode: Node? = null
        while (currentNode != null) {
            val newNode = Node(currentNode.`val`)
            if (currentNode.random == null) {
                newNode.random = null
            }
            prevNode?.next = newNode
            originalToCopy[currentNode] = newNode

            prevNode = newNode
            currentNode = currentNode.next
        }

        currentNode = node

        while (currentNode != null) {
            if (currentNode.random != null) {
                val newNode = originalToCopy[currentNode]!!
                val randomNode = originalToCopy[currentNode.random]!!
                newNode.random = randomNode
            }
            currentNode = currentNode.next
        }

        return originalToCopy[node]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Create sample linked list with random pointers
            val node1 = Node(7)
            val node2 = Node(13)
            val node3 = Node(11)
            val node4 = Node(10)
            val node5 = Node(1)

            node1.next = node2
            node2.next = node3
            node3.next = node4
            node4.next = node5

            node2.random = node1
            node3.random = node5
            node4.random = node3
            node5.random = node1

            val solution = _138CopyListWithRandomPointer()
            val copiedHead = solution.copyRandomList(node1)

            // Print copied list to verify
            var curr = copiedHead
            println("Copied list: [val, random_val]")
            while (curr != null) {
                val randomVal = curr.random?.`val` ?: "null"
                println("[${curr.`val`}, $randomVal]")
                curr = curr.next
            }
        }
    }
}

