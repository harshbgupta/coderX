package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Leetcode: 104. Maximum Depth of Binary Tree
 *
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 */

class _104MaxDepthOfBinaryTree {

    // TreeNode definition
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Base case: If the current node is null, return 0.
     * - Recursively compute max depth of left and right subtrees.
     * - Return 1 + maximum of left and right subtree depths.
     *
     * 📦 Time Complexity: O(n) where n = number of nodes
     * 💾 Space Complexity: O(h) where h = height of the tree (recursive call stack)
     */
    fun maxDepth(root: TreeNode?): Int {
        // If the current node is null, return 0 (base case)
        if (root == null) return 0

        // Recursively calculate depth of left subtree
        val leftDepth = maxDepth(root.left)

        // Recursively calculate depth of right subtree
        val rightDepth = maxDepth(root.right)

        // Return 1 (for current node) + max of left and right subtree depths
        return 1 + maxOf(leftDepth, rightDepth)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _104MaxDepthOfBinaryTree()

            // 🧪 Test Case 1
            val root1 = TreeNode(3).apply {
                left = TreeNode(9)
                right = TreeNode(20).apply {
                    left = TreeNode(15)
                    right = TreeNode(7)
                }
            }
            println("Test Case 1: Input = [3,9,20,null,null,15,7]")
            println("Expected Output: 3")
            println("Actual Output: ${solution.maxDepth(root1)}")
            println("------")

            // 🧪 Test Case 2
            val root2 = TreeNode(1).apply {
                right = TreeNode(2)
            }
            println("Test Case 2: Input = [1,null,2]")
            println("Expected Output: 2")
            println("Actual Output: ${solution.maxDepth(root2)}")
            println("------")

            // 🧪 Test Case 3 (Edge case: empty tree)
            val root3: TreeNode? = null
            println("Test Case 3: Input = []")
            println("Expected Output: 0")
            println("Actual Output: ${solution.maxDepth(root3)}")
            println("------")
        }
    }
}
