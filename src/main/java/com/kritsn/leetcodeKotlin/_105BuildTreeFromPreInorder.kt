package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * Leetcode 105: Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */
class _105BuildTreeFromPreInorder {

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
        // Step 1: Build a map of inorder values and their indices for fast lookup
        val inMap = mutableMapOf<Int, Int>()
        for (i in inorder.indices) {
            inMap[inorder[i]] = i
        }

        // Step 2: Recursive helper to build the tree
        return buildTreeRecursive(
            preorder,
            0, preorder.size - 1,
            inorder,
            0, inorder.size - 1,
            inMap
        )
    }

    // 🧩 Recursive function to construct the tree using preorder and inorder slices
    private fun buildTreeRecursive(
        preorder: IntArray, preStart: Int, preEnd: Int,
        inorder: IntArray, inStart: Int, inEnd: Int,
        inMap: Map<Int, Int>
    ): TreeNode? {

        // Base condition: If range is invalid, return null
        if (preStart > preEnd || inStart > inEnd) return null

        // First element in preorder is the root node
        val rootVal = preorder[preStart]
        val root = TreeNode(rootVal)

        // Index of root in inorder traversal
        val inRoot = inMap[rootVal] ?: return null

        // Number of nodes in left subtree
        val numsLeft = inRoot - inStart

        // Construct left and right subtrees recursively
        root.left = buildTreeRecursive(
            preorder,
            preStart + 1, preStart + numsLeft,
            inorder,
            inStart, inRoot - 1,
            inMap
        )

        root.right = buildTreeRecursive(
            preorder,
            preStart + numsLeft + 1, preEnd,
            inorder,
            inRoot + 1, inEnd,
            inMap
        )

        return root
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val builder = _105BuildTreeFromPreInorder()

            // 🧪 Test Case 1
            /*
                   3
                  / \
                 9  20
                    / \
                   15  7
             */
            val preorder1 = intArrayOf(3, 9, 20, 15, 7)
            val inorder1 = intArrayOf(9, 3, 15, 20, 7)
            val root1 = builder.buildTree(preorder1, inorder1)
            println("Test Case 1: Tree Root = ${root1?.`val`} (Expected: 3)")

            // 🧪 Test Case 2
            /*
                 1
                /
               2
             */
            val preorder2 = intArrayOf(1, 2)
            val inorder2 = intArrayOf(2, 1)
            val root2 = builder.buildTree(preorder2, inorder2)
            println("Test Case 2: Tree Root = ${root2?.`val`} (Expected: 1), Left Child = ${root2?.left?.`val`} (Expected: 2)")

            // 🧪 Test Case 3
            /*
                 1
             */
            val preorder3 = intArrayOf(1)
            val inorder3 = intArrayOf(1)
            val root3 = builder.buildTree(preorder3, inorder3)
            println("Test Case 3: Tree Root = ${root3?.`val`} (Expected: 1)")

        }
    }

    /**
     * 🌳 Basic TreeNode definition.
     */
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}