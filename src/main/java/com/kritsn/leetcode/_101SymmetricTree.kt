package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////

/*
Leetcode Problem: Symmetric Tree
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
*/

class _101SymmetricTree {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * This function checks whether a binary tree is symmetric around its center.
     * - We define a helper function `isMirror` to compare the left and right subtrees recursively.
     * - Base cases handle when both nodes are null (true) or when one is null (false).
     * - If both nodes exist, we check their values and recursively check their children in mirrored order.
     *
     * Time Complexity: O(n) - we visit each node once
     * Space Complexity: O(h) - recursion stack height, worst O(n) if unbalanced
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        return isMirror(root?.left, root?.right)
    }

    private fun isMirror(t1: TreeNode?, t2: TreeNode?): Boolean {
        // Both nodes are null, symmetric
        if (t1 == null && t2 == null) return true
        // One is null, the other is not, not symmetric
        if (t1 == null || t2 == null) return false
        // Values must match and the subtrees must mirror each other
        return (t1.`val` == t2.`val`)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tree = _101SymmetricTree()

            // 🧪 Test Case 1: Symmetric
            val root1 = TreeNode(1,
                left = TreeNode(2, TreeNode(3), TreeNode(4)),
                right = TreeNode(2, TreeNode(4), TreeNode(3))
            )
            println("Test Case 1: ${tree.isSymmetric(root1)}") // Expected: true

            // 🧪 Test Case 2: Asymmetric
            val root2 = TreeNode(1,
                left = TreeNode(2, null, TreeNode(3)),
                right = TreeNode(2, null, TreeNode(3))
            )
            println("Test Case 2: ${tree.isSymmetric(root2)}") // Expected: false

            // 🧪 Test Case 3: Single Node
            val root3 = TreeNode(1)
            println("Test Case 3: ${tree.isSymmetric(root3)}") // Expected: true
        }
    }
}