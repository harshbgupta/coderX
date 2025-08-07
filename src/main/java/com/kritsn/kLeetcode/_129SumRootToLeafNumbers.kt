package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 23, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode 129: Sum Root to Leaf Numbers

You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
Return the total sum of all the numbers formed by root-to-leaf paths.
A leaf is a node with no children.
*/

class _129SumRootToLeafNumbers {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Traverse tree using DFS.
     * - Maintain currentSum by appending digits → currentSum = currentSum * 10 + node.val.
     * - When a leaf is reached, return the formed number.
     * - Accumulate left and right subtree values and return total.
     *
     * ⏱ Time Complexity: O(n) — each node visited once.
     * ⏱ Space Complexity: O(h) — due to recursion stack (h = tree height).
     */
    fun sumNumbers(root: TreeNode?): Int {
        fun dfs(node: TreeNode?, currentSum: Int): Int {
            if (node == null) return 0

            // Update the path number by appending the current digit
            val newSum = currentSum * 10 + node.`val`

            // If it's a leaf node, return the number formed so far
            if (node.left == null && node.right == null) {
                return newSum
            }

            // Recurse on left and right subtrees and return the total
            val leftSum = dfs(node.left, newSum)
            val rightSum = dfs(node.right, newSum)

            return leftSum + rightSum
        }

        return dfs(root, 0)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _129SumRootToLeafNumbers()

            // 🧪 Test Case 1: Tree [1,2,3] → Numbers: 12, 13 → Sum: 25
            val root1 = TreeNode(1, TreeNode(2), TreeNode(3))
            println("Test 1: Sum = ${solver.sumNumbers(root1)}") // Expected: 25

            // 🧪 Test Case 2: Tree [4,9,0,5,1] → Numbers: 495, 491, 40 → Sum: 1026
            val root2 = TreeNode(4,
                left = TreeNode(9, TreeNode(5), TreeNode(1)),
                right = TreeNode(0)
            )
            println("Test 2: Sum = ${solver.sumNumbers(root2)}") // Expected: 1026

            // 🧪 Test Case 3: Tree [0] → Number: 0
            val root3 = TreeNode(0)
            println("Test 3: Sum = ${solver.sumNumbers(root3)}") // Expected: 0

            // 🧪 Test Case 4: Empty Tree
            println("Test 4: Sum = ${solver.sumNumbers(null)}") // Expected: 0
        }
    }
}