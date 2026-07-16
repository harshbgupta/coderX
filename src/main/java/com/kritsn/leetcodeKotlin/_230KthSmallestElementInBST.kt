package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode Problem: 230. Kth Smallest Element in a BST

Given the root of a binary search tree and an integer k,
return the kth smallest value (1-indexed) among all node values.
*/

class _230KthSmallestElementInBST {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    private var count = 0       // Counter to track the number of visited nodes
    private var result = -1     // Holds the final kth smallest value

    ///////////////////////////////////////////////////////////////////////////
    // 🧠 Algorithm & Approach:
    // - Use in-order traversal (left -> root -> right) to process nodes in sorted order.
    // - Increment counter each time a node is visited.
    // - When counter equals k, store the current node value as result.
    //
    // ⏱ Time Complexity: O(H + k) in the average case, O(n) worst case.
    // ⏱ Space Complexity: O(h) due to recursion stack (h = tree height)
    ///////////////////////////////////////////////////////////////////////////
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        count = 0
        result = -1
        inOrder(root, k)
        return result
    }

    // 🔁 In-order traversal helper function
    private fun inOrder(node: TreeNode?, k: Int) {
        if (node == null) return

        inOrder(node.left, k)

        count++
        if (count == k) {
            result = node.`val`
            return
        }

        inOrder(node.right, k)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _230KthSmallestElementInBST()

            // 🧪 Test Case 1
            val root1 = TreeNode(3).apply {
                left = TreeNode(1).apply { right = TreeNode(2) }
                right = TreeNode(4)
            }
            println("Test Case 1 Output: ${solution.kthSmallest(root1, 1)}") // Expected: 1

            // 🧪 Test Case 2
            val root2 = TreeNode(5).apply {
                left = TreeNode(3).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1)
                    }
                    right = TreeNode(4)
                }
                right = TreeNode(6)
            }
            println("Test Case 2 Output: ${solution.kthSmallest(root2, 3)}") // Expected: 3

            // 🧪 Test Case 3
            val root3 = TreeNode(1)
            println("Test Case 3 Output: ${solution.kthSmallest(root3, 1)}") // Expected: 1
        }
    }
}