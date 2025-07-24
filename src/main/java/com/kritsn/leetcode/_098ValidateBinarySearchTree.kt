package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode Problem: 98. Validate Binary Search Tree

Given the root of a binary tree, determine if it is a valid binary search tree (BST).
*/

class _098ValidateBinarySearchTree {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * https://youtu.be/dSBcCynP1nA?feature=shared
     * 🧠 Algorithm & Approach:
     * - Use recursion to validate the BST property.
     * - Each node must lie in the valid range (min, max).  ---> Imp Note
     * - Recursively validate left and right subtrees by updating the range. ---> Imp Note
     *
     * Time Complexity: O(n) — visit each node once
     * Space Complexity: O(h) — recursion stack (h = tree height)
     */
    fun isValidBST(root: TreeNode?): Boolean {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    // Helper function to validate BST within min..max range
    private fun validate(node: TreeNode?, min: Long, max: Long): Boolean {
        if (node == null) return true

        // Current node must be strictly between min and max
        if (node.`val`.toLong() <= min || node.`val`.toLong() >= max) return false

        // Left subtree must be in (min, node.val)
        // Right subtree must be in (node.val, max)
        return validate(node.left, min, node.`val`.toLong()) &&
                validate(node.right, node.`val`.toLong(), max)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val validator = _098ValidateBinarySearchTree()
            // 🧪 IMP: Test Case IMP: Invalid BST (3 in right of main node 5, and as per BST all smaller value should be left of the node not just adjusent top it applies for ancestors)
            /* Visual Representation of the Binary Tree:
                5
              /   \
             1     6
                  / \
                 3   7
            */
            val rootImp = TreeNode(5).apply {
                left = TreeNode(1)
                right = TreeNode(6).apply {
                    left = TreeNode(3)
                    right = TreeNode(7)
                }
            }
            println("Test Case <<Imp>>: ${validator.isValidBST(rootImp)} (Expected: false)")

            // 🧪 Test Case 1: Valid BST
            val root1 = TreeNode(2).apply {
                left = TreeNode(1)
                right = TreeNode(3)
            }
            println("Test Case 1: ${validator.isValidBST(root1)} (Expected: true)")

            // 🧪 Test Case 2: Invalid BST (3 in left subtree of 5)
            val root2 = TreeNode(5).apply {
                left = TreeNode(1)
                right = TreeNode(4).apply {
                    left = TreeNode(3)
                    right = TreeNode(6)
                }
            }
            println("Test Case 2: ${validator.isValidBST(root2)} (Expected: false)")

            // 🧪 Test Case 3: Single Node
            val root3 = TreeNode(1)
            println("Test Case 3: ${validator.isValidBST(root3)} (Expected: true)")



        }
    }
}