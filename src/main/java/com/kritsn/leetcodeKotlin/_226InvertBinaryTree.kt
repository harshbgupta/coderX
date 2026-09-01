package com.kritsn.leetcodeKotlin

import java.util.LinkedList

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 📄 Problem: Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 */

class _226InvertBinaryTree {

    // 👇 Definition for a binary tree node placed inside the main class for future reference.
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Use recursive DFS to invert the binary tree.
     * - For each node, swap the left and right subtrees.
     * - Recursively apply this to left and right children.
     *
     * ⏱ Time Complexity: O(N), where N is the number of nodes (each node visited once)
     * 🗂 Space Complexity: O(H), where H is the height of the tree (recursion stack)
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        // Base case: If node is null, just return null.
        if (root == null) return null

        // Recursively invert left and right subtrees.
        val leftInverted = invertTree(root.left)
        val rightInverted = invertTree(root.right)

        // Swap the inverted children.
        root.left = rightInverted
        root.right = leftInverted

        // Return the current root after inverting its subtrees.
        return root
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _226InvertBinaryTree()

            // 🧪 Test Case 1:
            val root1 = TreeNode(4,
                left = TreeNode(2, TreeNode(1), TreeNode(3)),
                right = TreeNode(7, TreeNode(6), TreeNode(9))
            )
            val inverted1 = solution.invertTree(root1)
            println("Test Case 1 Output: ${printTree(inverted1)}")
            // Expected Output: [4, 7, 2, 9, 6, 3, 1]

            // 🧪 Test Case 2:
            val root2 = TreeNode(2,
                left = TreeNode(1),
                right = TreeNode(3)
            )
            val inverted2 = solution.invertTree(root2)
            println("Test Case 2 Output: ${printTree(inverted2)}")
            // Expected Output: [2, 3, 1]

            // 🧪 Test Case 3: Null input
            val inverted3 = solution.invertTree(null)
            println("Test Case 3 Output: ${printTree(inverted3)}")
            // Expected Output: []
        }

        // Helper function to print tree in level-order
        fun printTree(root: TreeNode?): List<Int?> {
            val result = mutableListOf<Int?>()
            val queue = LinkedList<TreeNode?>()
            queue.add(root)
            while (queue.isNotEmpty()) {
                val node = queue.poll()
                if (node == null) {
                    result.add(null)
                } else {
                    result.add(node.`val`)
                    queue.add(node.left)
                    queue.add(node.right)
                }
            }
            // Trim trailing nulls for cleaner output
            while (result.isNotEmpty() && result.last() == null) result.removeAt(result.size - 1)
            return result
        }
    }
}