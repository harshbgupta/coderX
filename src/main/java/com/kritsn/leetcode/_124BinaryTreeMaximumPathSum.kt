package com.kritsn.leetcode

import kotlin.math.max

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 23, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode 124: Binary Tree Maximum Path Sum

A path is any sequence of nodes connected via parent-child links (not necessarily through root).
Each node can be included at most once.
The path sum is the sum of node values in the path.

Return the maximum path sum of any non-empty path.
*/

class _124BinaryTreeMaximumPathSum {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * https://www.youtube.com/watch?v=WszrfSwMz58
     * 🧠 Algorithm & Approach:
     * - Use DFS to compute maximum gain from each subtree.
     * - Ignore negative gains (don't include them in path).
     * - Track the highest path sum seen so far (may include both children).
     *
     * Time Complexity: O(n), we visit each node once.
     * Space Complexity: O(h), recursion stack (h = tree height).
     */
    fun maxPathSum(root: TreeNode?): Int {
        return helper1(root)
//        return helper2(root)
    }

    private fun helper1(root: TreeNode?): Int {
        var maxSumInternal = Int.MIN_VALUE  // 🟢 Global variable to track best sum

        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0

            // 🔁 Recurse into left and right, ignore negative contributions
            val leftGain = maxOf(0, dfs(node.left))
            val rightGain = maxOf(0, dfs(node.right))

            // 🔄 Current path sum using both children + current node
            val currentPathSum = node.`val` + leftGain + rightGain

            // 🔼 Update global max path sum if current is better
            maxSumInternal = maxOf(maxSumInternal, currentPathSum)

            // ⬅️ Return max gain for parent to use (only one child allowed)
            return node.`val` + maxOf(leftGain, rightGain)
        }

        dfs(root)
        return maxSumInternal
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _124BinaryTreeMaximumPathSum()

            // 🧪 Test Case 1: Tree = [1,2,3]
            val root1 = TreeNode(1, TreeNode(2), TreeNode(3))
            println("Test 1 Output: ${solver.maxPathSum(root1)}") // Expected: 6

            // 🧪 Test Case 2: Tree = [-10,9,20,null,null,15,7]
            val root2 = TreeNode(
                -10,
                TreeNode(9),
                TreeNode(20, TreeNode(15), TreeNode(7))
            )
            println("Test 2 Output: ${solver.maxPathSum(root2)}") // Expected: 42

            // 🧪 Test Case 3: Tree = [2,-1]
            val root3 = TreeNode(2, TreeNode(-1))
            println("Test 3 Output: ${solver.maxPathSum(root3)}") // Expected: 2

            // 🧪 Test Case 4: Tree = [-3]
            val root4 = TreeNode(-3)
            println("Test 4 Output: ${solver.maxPathSum(root4)}") // Expected: -3
        }
    }
}