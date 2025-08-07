package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * Leetcode 199: Binary Tree Right Side View
 * Given the root of a binary tree, return the values of the nodes visible from the right side.
 */

class _199BinaryTreeRightSideView {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    ///////////////////////////////////////////////////////////////////////////
    // 🧠 Algorithm & Approach:
    //
    // We perform a level-order traversal (BFS), and for each level,
    // we add the value of the last node (i.e., rightmost node at that level)
    // to the result list.
    //
    // ⏱ Time Complexity: O(n) — visit each node once
    // ⏳ Space Complexity: O(n) — for queue and output list
    ///////////////////////////////////////////////////////////////////////////
    fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result

        val queue: ArrayDeque<TreeNode> = ArrayDeque()
        queue.add(root)

        // Start level-order traversal
        while (queue.isNotEmpty()) {
            val size = queue.size

            for (i in 0 until size) {
                val node = queue.removeFirst()

                // If it's the last node in the level, it's visible from the right
                if (i == size - 1) {
                    result.add(node.`val`)
                }

                // Add child nodes for the next level
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }

        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _199BinaryTreeRightSideView()

            // 🧪 Test Case 1
            val root1 = TreeNode(1).apply {
                left = TreeNode(2).apply {
                    right = TreeNode(5)
                }
                right = TreeNode(3).apply {
                    right = TreeNode(4)
                }
            }
            println("Test Case 1: Output = ${solution.rightSideView(root1)}") // Expected: [1, 3, 4]

            // 🧪 Test Case 2
            val root2 = TreeNode(1)
            println("Test Case 2: Output = ${solution.rightSideView(root2)}") // Expected: [1]

            // 🧪 Test Case 3
            println("Test Case 3: Output = ${solution.rightSideView(null)}") // Expected: []
        }
    }
}