package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * Leetcode 103: Binary Tree Zigzag Level Order Traversal
 * Return the zigzag level order traversal of its nodes' values.
 */

class _103ZigzagLevelOrderTraversal {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    ///////////////////////////////////////////////////////////////////////////
    // https://youtu.be/igbboQbiwqw?feature=shared
    // 🧠 Algorithm & Approach:
    //
    // - Use BFS (level order traversal) with a queue
    // - For each level, collect node values in a temporary list
    // - If current level is to be traversed right to left, reverse the list
    // - Flip the direction for the next level
    //
    // ⏱ Time Complexity: O(n)
    // ⏳ Space Complexity: O(n)
    ///////////////////////////////////////////////////////////////////////////
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        var leftToRight = true

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val currentLevel = mutableListOf<Int>()

            repeat(levelSize) {
                val node = queue.removeFirst()

                // Add node value in order depending on direction
                if (leftToRight) {
                    currentLevel.add(node.`val`)
                } else {
                    currentLevel.add(0, node.`val`)  // Insert at beginning for right-to-left
                }

                // Add child nodes to queue for next level
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            // Append current level to result and flip direction
            result.add(currentLevel)
            leftToRight = !leftToRight
        }

        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val sol = _103ZigzagLevelOrderTraversal()

            // 🧪 Test Case 1
            val root1 = TreeNode(3).apply {
                left = TreeNode(9)
                right = TreeNode(20).apply {
                    left = TreeNode(15)
                    right = TreeNode(7)
                }
            }
            println("Test Case 1: ${sol.zigzagLevelOrder(root1)}") // [[3], [20, 9], [15, 7]]

            // 🧪 Test Case 2
            val root2 = TreeNode(1)
            println("Test Case 2: ${sol.zigzagLevelOrder(root2)}") // [[1]]

            // 🧪 Test Case 3
            println("Test Case 3: ${sol.zigzagLevelOrder(null)}") // []
        }
    }
}