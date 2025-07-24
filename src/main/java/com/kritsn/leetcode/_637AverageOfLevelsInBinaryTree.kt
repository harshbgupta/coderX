package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode 637: Average of Levels in Binary Tree

Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
Answers within 10^-5 of the actual answer will be accepted.
*/

class _637AverageOfLevelsInBinaryTree {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Perform a level order traversal using a queue.
     * - At each level, sum the values of all nodes.
     * - Compute average by dividing sum by the number of nodes at that level.
     * - Append average to result list.
     *
     * Time Complexity: O(n) — visiting every node once.
     * Space Complexity: O(n) — for queue and result storage.
     */
    fun averageOfLevels(root: TreeNode?): List<Double> {
        val result = mutableListOf<Double>()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            var levelSum = 0.0

            repeat(levelSize) {
                val node = queue.removeFirst()
                levelSum += node.`val`

                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            result.add(levelSum / levelSize)
        }

        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val sol = _637AverageOfLevelsInBinaryTree()

            // 🧪 Test Case 1
            val root1 = TreeNode(3).apply {
                left = TreeNode(9)
                right = TreeNode(20).apply {
                    left = TreeNode(15)
                    right = TreeNode(7)
                }
            }
            println("Test Case 1: ${sol.averageOfLevels(root1)}") // Expected: [3.0, 14.5, 11.0]

            // 🧪 Test Case 2
            val root2 = TreeNode(10)
            println("Test Case 2: ${sol.averageOfLevels(root2)}") // Expected: [10.0]

            // 🧪 Test Case 3
            println("Test Case 3: ${sol.averageOfLevels(null)}") // Expected: []
        }
    }
}