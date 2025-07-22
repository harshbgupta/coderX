package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode Problem: Construct Binary Tree from Preorder and Inorder Traversal
Given preorder and inorder traversal of a binary tree, construct the tree.
*/

class _105BuildTreeFromPreInorder {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - The first element in preorder is always the root.
     * - Find that root in inorder to split into left and right subtree.
     * - Recurse on left and right portions of preorder and inorder accordingly.
     * - Use a HashMap to store value -> index of inorder for quick lookup.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) for HashMap and recursion stack
     */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty() || inorder.isEmpty()) return null

        // Map from node value to its index in inorder for O(1) lookup
        val inorderIndexMap = mutableMapOf<Int, Int>()
        inorder.forEachIndexed { index, value ->
            inorderIndexMap[value] = index
        }

        // Mutable index pointer for preorder array (acts like a global pointer)
        var preorderIndex = 0

        // Recursive function to build tree
        fun build(left: Int, right: Int): TreeNode? {
            // Base case: no elements to construct subtree
            if (left > right) return null

            // Get root value and advance preorder pointer
            val rootVal = preorder[preorderIndex++]
            val root = TreeNode(rootVal)

            // Split inorder into left and right subtrees
            val inorderIndex = inorderIndexMap[rootVal]!!

            // Recursively build left and right subtrees
            root.left = build(left, inorderIndex - 1)
            root.right = build(inorderIndex + 1, right)

            return root
        }

        // Construct the full tree
        return build(0, inorder.size - 1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val builder = _105BuildTreeFromPreInorder()

            // 🧪 Test Case 1
            val preorder1 = intArrayOf(3, 9, 20, 15, 7)
            val inorder1 = intArrayOf(9, 3, 15, 20, 7)
            val root1 = builder.buildTree(preorder1, inorder1)
            println("Test Case 1: Tree Root = ${root1?.`val`} (Expected: 3)")

            // 🧪 Test Case 2
            val preorder2 = intArrayOf(1, 2)
            val inorder2 = intArrayOf(2, 1)
            val root2 = builder.buildTree(preorder2, inorder2)
            println("Test Case 2: Tree Root = ${root2?.`val`} (Expected: 1), Left Child = ${root2?.left?.`val`} (Expected: 2)")

            // 🧪 Test Case 3
            val preorder3 = intArrayOf(1)
            val inorder3 = intArrayOf(1)
            val root3 = builder.buildTree(preorder3, inorder3)
            println("Test Case 3: Tree Root = ${root3?.`val`} (Expected: 1)")
        }
    }
}