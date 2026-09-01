package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 23, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * Leetcode 117 - Populating Next Right Pointers in Each Node II
 * Given a binary tree, connect each node with its next right node.
 * This version works for trees that are not perfect.
 */
class _116PopulatingNextRightPointersInEachNode {

    // Node definition
    data class Node(
        var `val`: Int,
        var left: Node? = null,
        var right: Node? = null,
        var next: Node? = null
    )

    /**
     * 🧠 Algorithm & Approach:
     * - We use level-order traversal (BFS) using a queue.
     * - For each level, we link all nodes using the `next` pointer.
     * - Last node in the level should point to `null`.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N) for queue
     */
    fun connect(root: Node?): Node? {
        if (root == null) return null

        val queue: ArrayDeque<Node> = ArrayDeque()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val size = queue.size

            var prev: Node? = null

            for (i in 0 until size) {
                val curr = queue.removeFirst()

                if (prev != null) {
                    prev.next = curr
                }
                prev = curr

                if (curr.left != null) queue.add(curr.left!!)
                if (curr.right != null) queue.add(curr.right!!)
            }

            // Last node in the level points to null
            prev?.next = null
        }

        return root
    }

    /**
     * Helper function to print the tree level by level including `next` pointers.
     * Prints output in the same format as Leetcode: [[1],[2,3],[4,5,6,7]]
     */
    fun printTreeWithNext(root: Node?) {
        var levelStart = root

        val result = mutableListOf<MutableList<Int>>()

        while (levelStart != null) {
            var current = levelStart
            val level = mutableListOf<Int>()

            while (current != null) {
                level.add(current.`val`)
                current = current.next
            }

            result.add(level)

            levelStart = levelStart.left
        }

        println("Output with next pointers: $result")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _116PopulatingNextRightPointersInEachNode()

            // Sample Tree:
            //        1
            //      /   \
            //     2     3
            //    / \   / \
            //   4   5 6   7

            val root = Node(1)
            root.left = Node(2)
            root.right = Node(3)
            root.left!!.left = Node(4)
            root.left!!.right = Node(5)
            root.right!!.left = Node(6)
            root.right!!.right = Node(7)

            val connectedRoot = solution.connect(root)

            // Expected Leetcode format output: [[1],[2,3],[4,5,6,7]]
            solution.printTreeWithNext(connectedRoot)
        }
    }
}