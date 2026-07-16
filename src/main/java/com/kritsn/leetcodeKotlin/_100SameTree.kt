package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////

/*
Leetcode Problem: Same Tree

Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
*/
class _100SameTree {

    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * Recursive approach to check if two trees are identical.
     *
     * Time Complexity: O(n), where n is the number of nodes (in worst case we compare all nodes).
     * Space Complexity: O(h), where h is the height of the tree (due to recursion stack).
     */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        // If both nodes are null, they are structurally the same
        if (p == null && q == null) return true

        // If one is null and the other is not, they are not the same
        if (p == null || q == null) return false

        // If values differ, trees are not the same
        if (p.`val` != q.`val`) return false

        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _100SameTree()

            // Example 1: p = [1,2,3], q = [1,2,3] → true
            val p1 = TreeNode(1).apply {
                left = TreeNode(2)
                right = TreeNode(3)
            }
            val q1 = TreeNode(1).apply {
                left = TreeNode(2)
                right = TreeNode(3)
            }
            println("Test Case 1: ${solution.isSameTree(p1, q1)}") // Expected: true

            // Example 2: p = [1,2], q = [1,null,2] → false
            val p2 = TreeNode(1).apply {
                left = TreeNode(2)
            }
            val q2 = TreeNode(1).apply {
                right = TreeNode(2)
            }
            println("Test Case 2: ${solution.isSameTree(p2, q2)}") // Expected: false

            // Example 3: p = [1,2,1], q = [1,1,2] → false
            val p3 = TreeNode(1).apply {
                left = TreeNode(2)
                right = TreeNode(1)
            }
            val q3 = TreeNode(1).apply {
                left = TreeNode(1)
                right = TreeNode(2)
            }
            println("Test Case 3: ${solution.isSameTree(p3, q3)}") // Expected: false

            // Edge Case 4: Both trees are null
            val p4: TreeNode? = null
            val q4: TreeNode? = null
            println("Test Case 4: ${solution.isSameTree(p4, q4)}") // Expected: true

            // Edge Case 5: One tree is null
            val p5 = TreeNode(10)
            val q5: TreeNode? = null
            println("Test Case 5: ${solution.isSameTree(p5, q5)}") // Expected: false
        }
    }
}