package com.kritsn.leetcodeKotlin

import kotlin.math.abs

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode 530: Minimum Absolute Difference in BST

Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
*/

class _530MinAbsDiffInBST {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    private var prevValue: Int? = null           // Keeps track of previous node during in-order traversal
    private var minDiff = Int.MAX_VALUE          // Stores the minimum absolute difference

    ///////////////////////////////////////////////////////////////////////////
    // 🧠 Algorithm & Approach:
    // - Use in-order traversal to visit BST in ascending order.
    // - At each node, calculate difference from the previous node.
    // - Update minDiff with the smallest difference found.
    //
    // ⏱ Time Complexity: O(n)
    // ⏱ Space Complexity: O(h) — recursion stack (h = tree height)
    ///////////////////////////////////////////////////////////////////////////
    fun getMinimumDifference(root: TreeNode?): Int {
        inOrder(root)
        return minDiff
    }

    // 🔁 In-order traversal: left -> root -> right
    private fun inOrder(node: TreeNode?) {
        if (node == null) return

        inOrder(node.left)

        // If previous node exists, compute the difference
        prevValue?.let {
            val diff = abs(node.`val` - it)
            minDiff = minOf(minDiff, diff)
        }
        prevValue = node.`val` // Update previous node value

        inOrder(node.right)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _530MinAbsDiffInBST()

            // 🧪 Test Case 1
            val root1 = TreeNode(4).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                }
                right = TreeNode(6)
            }
            println("Test Case 1 Output: ${solution.getMinimumDifference(root1)}") // Expected: 1

            // 🧪 Test Case 2
            val root2 = TreeNode(1).apply {
                right = TreeNode(3)
            }
            println("Test Case 2 Output: ${solution.getMinimumDifference(root2)}") // Expected: 2

            // 🧪 Test Case 3
            val root3 = TreeNode(543).apply {
                left = TreeNode(384).apply {
                    right = TreeNode(445)
                }
                right = TreeNode(652).apply {
                    right = TreeNode(699)
                }
            }
            println("Test Case 3 Output: ${solution.getMinimumDifference(root3)}") // Expected: 47
        }
    }
}