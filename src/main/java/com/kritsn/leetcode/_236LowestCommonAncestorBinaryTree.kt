package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////

/*
Leetcode Problem: Lowest Common Ancestor of a Binary Tree
Given a binary tree, find the lowest common ancestor of two given nodes in the tree.

Definition:
The LCA is the lowest node in the tree that has both nodes p and q as descendants.
*/
class _236LowestCommonAncestorBinaryTree {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * https://youtu.be/K_oNnoUeDJE?feature=shared
     * 🧠 Recursive DFS Approach:
     * - Traverse the tree from the root.
     * - If current node is null, return null.
     * - If current node is either p or q, return current node.
     * - Recurse left and right.
     * - If both left and right return non-null, current node is LCA.
     * - Else return the non-null result.
     *
     * ⏱ Time Complexity: O(n), where n = number of nodes in the tree.
     * 🛠 Space Complexity: O(h), where h = height of tree (due to recursion stack).
     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // Base case: null node
        if (root == null) return null

        // If current node matches either p or q, return it
        if (root == p || root == q) return root

        // Recurse left and right
        val leftLCA = lowestCommonAncestor(root.left, p, q)
        val rightLCA = lowestCommonAncestor(root.right, p, q)

        // If both sides return a node, current node is the LCA
        if (leftLCA != null && rightLCA != null) return root

        // Otherwise return non-null child (or null if both are null)
        return leftLCA ?: rightLCA
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _236LowestCommonAncestorBinaryTree()

            // Constructing the binary tree
            val root = TreeNode(3)
            root.left = TreeNode(5)
            root.right = TreeNode(1)
            root.left?.left = TreeNode(6)
            root.left?.right = TreeNode(2)
            root.left?.right?.left = TreeNode(7)
            root.left?.right?.right = TreeNode(4)
            root.right?.left = TreeNode(0)
            root.right?.right = TreeNode(8)

            val p = root.left         // Node with value 5
            val q = root.left?.right?.right // Node with value 4

            val lca = solution.lowestCommonAncestor(root, p, q)
            println("Test Case 1: LCA of ${p?.`val`} and ${q?.`val`} is ${lca?.`val`} (Expected: 5)")

            val p2 = root.left        // Node 5
            val q2 = root.right       // Node 1
            val lca2 = solution.lowestCommonAncestor(root, p2, q2)
            println("Test Case 2: LCA of ${p2?.`val`} and ${q2?.`val`} is ${lca2?.`val`} (Expected: 3)")
        }
    }
}