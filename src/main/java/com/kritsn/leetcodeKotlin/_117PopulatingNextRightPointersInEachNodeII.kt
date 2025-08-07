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
class _117PopulatingNextRightPointersInEachNodeII {

    // ✅ Definition for a binary tree node with 'next' pointer.
    data class Node(
        var `val`: Int,
        var left: Node? = null,
        var right: Node? = null,
        var next: Node? = null
    )

    /**
     * 🧠 Algorithm & Approach:
     * - Use level-by-level traversal.
     * - Use a dummy node to build 'next' links for each level.
     * - Traverse current level using existing .next pointers.
     * - Constant space: no extra queue or list used.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) (excluding output tree)
     */
    fun connect(root: Node?): Node? {
        var curr = root  // Start from root node

        while (curr != null) {
            val dummy = Node(0) // Dummy node to build next level
            var tail = dummy     // Tail pointer to connect children

            while (curr != null) {
                // If left child exists, attach it to the tail
                if (curr.left != null) {
                    tail.next = curr.left
                    tail = tail.next!!
                }

                // If right child exists, attach it as next
                if (curr.right != null) {
                    tail.next = curr.right
                    tail = tail.next!!
                }

                // Move to next node in the current level
                curr = curr.next
            }

            // Move to the first node of the next level
            curr = dummy.next
        }

        return root
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _117PopulatingNextRightPointersInEachNodeII()

            // Build tree:
            //        1
            //       / \
            //      2   3
            //     / \   \
            //    4   5   7
            val root = Node(1,
                left = Node(2,
                    left = Node(4),
                    right = Node(5)
                ),
                right = Node(3,
                    right = Node(7)
                )
            )

            val result = solver.connect(root)
            print("${root.`val`}, #, ")
            println("Next pointer of root.left (2): ${result?.left?.next?.`val`} (Expected: 3)")
            println("Next pointer of root.left.left (4): ${result?.left?.left?.next?.`val`} (Expected: 5)")
            println("Next pointer of root.left.right (5): ${result?.left?.right?.next?.`val`} (Expected: 7)")
        }
    }
}