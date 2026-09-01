package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 23, 2025
///////////////////////////////////////////////////////////////////////////

/*
Leetcode 106: Construct Binary Tree from Inorder and Postorder Traversal
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
*/

class _106ConstructBinaryTreeFromInorderAndPostorder {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    // https://youtu.be/LgLRTaEMRVc?feature=shared
    // 🧠 Algorithm & Approach:
    // - The last element in postorder is the root.
    // - Use HashMap for O(1) lookup of root index in inorder array.
    // - Recurse by processing postorder from end to start.
    // - Build right subtree first, then left.
    // - Maintain postorderIndex pointer globally as we move backward.

    /**
     * Main method to build the binary tree from inorder and postorder traversal arrays.
     * It maps each value in the inorder array to its index for O(1) root index lookup.
     */
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        // Base case check
        if (inorder.isEmpty() || postorder.isEmpty() || inorder.size != postorder.size) return null

        // Map to store value -> index for quick lookup in inorder
        val inorderIndexMap = HashMap<Int, Int>()
        for (i in inorder.indices) {
            inorderIndexMap[inorder[i]] = i
        }

        // Recursive call with boundaries
        return buildSubTree(
            inorder, 0, inorder.lastIndex,
            postorder, 0, postorder.lastIndex,
            inorderIndexMap
        )
    }

    /**
     * Recursively constructs the binary tree from given bounds.
     */
    private fun buildSubTree(
        inorder: IntArray, inStart: Int, inEnd: Int,
        postorder: IntArray, postStart: Int, postEnd: Int,
        inorderIndexMap: Map<Int, Int>
    ): TreeNode? {
        // Base case: no elements to process
        if (postStart > postEnd || inStart > inEnd) return null

        // The last element in postorder is the root of the current subtree
        val rootValue = postorder[postEnd]
        val root = TreeNode(rootValue)

        // Get the index of the root in inorder array to divide left and right subtree
        val rootIndexInInorder = inorderIndexMap[rootValue]!!

        // Count of elements in the left subtree
        val leftSubtreeSize = rootIndexInInorder - inStart

        // Build left and right subtrees recursively
        root.left = buildSubTree(
            inorder, inStart, rootIndexInInorder - 1,
            postorder, postStart, postStart + leftSubtreeSize - 1,
            inorderIndexMap
        )

        root.right = buildSubTree(
            inorder, rootIndexInInorder + 1, inEnd,
            postorder, postStart + leftSubtreeSize, postEnd - 1,
            inorderIndexMap
        )

        return root
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val builder = _106ConstructBinaryTreeFromInorderAndPostorder()

            // 🧪 Test Case 1
            val inorder1 = intArrayOf(9, 3, 15, 20, 7)
            val postorder1 = intArrayOf(9, 15, 7, 20, 3)
            val root1 = builder.buildTree(inorder1, postorder1)
            println("Test Case 1: Root = ${root1?.`val`} (Expected: 3)")

            // 🧪 Test Case 2
            val inorder2 = intArrayOf(2, 1)
            val postorder2 = intArrayOf(2, 1)
            val root2 = builder.buildTree(inorder2, postorder2)
            println("Test Case 2: Root = ${root2?.`val`} (Expected: 1), Left = ${root2?.left?.`val`} (Expected: 2)")

            // 🧪 Test Case 3
            val inorder3 = intArrayOf(1)
            val postorder3 = intArrayOf(1)
            val root3 = builder.buildTree(inorder3, postorder3)
            println("Test Case 3: Root = ${root3?.`val`} (Expected: 1)")
        }
    }
}