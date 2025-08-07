package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode 102: Binary Tree Level Order Traversal

Given the root of a binary tree, return the level order traversal of its nodes' values.
(i.e., from left to right, level by level).
*/

class _102BinaryTreeLevelOrderTraversal {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * https://youtu.be/6ZnyEApgFYg?feature=shared
     * 🧠 Algorithm & Approach:
     * - Use BFS traversal with a queue to process the tree level by level.
     * - At each level, collect node values into a list.
     * - Add children of current level nodes to the queue.
     *
     * Time Complexity: O(n) — visiting each node once.
     * Space Complexity: O(n) — queue + result list.
     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val currentLevel = mutableListOf<Int>()

            repeat(levelSize) {
                val node = queue.removeFirst()
                currentLevel.add(node.`val`)

                // Enqueue left and right children
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            result.add(currentLevel)
        }

        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val sol = _102BinaryTreeLevelOrderTraversal()

            // 🧪 Test Case 1
            val root1 = TreeNode(3).apply {
                left = TreeNode(9)
                right = TreeNode(20).apply {
                    left = TreeNode(15)
                    right = TreeNode(7)
                }
            }
            println("Test Case 1: ${sol.levelOrder(root1)}") // Expected: [[3], [9, 20], [15, 7]]

            // 🧪 Test Case 2
            val root2 = TreeNode(1)
            println("Test Case 2: ${sol.levelOrder(root2)}") // Expected: [[1]]

            // 🧪 Test Case 3
            println("Test Case 3: ${sol.levelOrder(null)}") // Expected: []
        }
    }
}