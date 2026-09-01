package com.kritsn.leetcodeKotlin
import kotlin.properties.Delegates

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 23, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * Leetcode 112. Path Sum
 * Given a binary tree and a target sum, determine if the tree has a root-to-leaf path such that the sum equals the target.
 */
class _112PathSum {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * Solution 1
     * 🧠 Algorithm & Approach:
     * - Use DFS traversal (recursion).
     * - At each node, subtract its value from the current targetSum.
     * - If it's a leaf and targetSum == node value, return true.
     * - Recurse down left and right subtrees.
     *
     * ⏱ Time Complexity: O(n) - where n is the number of nodes
     * ⏱ Space Complexity: O(h) - recursion stack, h = height of the tree
     */
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false  // Empty tree has no path

        // If we reached a leaf, check if path sum equals targetSum
        if (root.left == null && root.right == null) {
            return targetSum == root.`val`
        }

        // Subtract current node's value and recur on children
        val remainingSum = targetSum - root.`val`
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum)
    }


    ///////////////////////////////////////////////////////////////////////////
    // Solution 2
    // https://www.youtube.com/watch?v=ANR85j2_ir0
    ///////////////////////////////////////////////////////////////////////////
    var target by Delegates.notNull<Int>()
    fun hasPathSum2(root: TreeNode?, targetSum: Int): Boolean {
        target = targetSum
        return helper(root, targetSum)
    }

    private fun helper(root: TreeNode?, currentSum: Int): Boolean{
        if (root==null){
            return false
        }
        var sumTemp  = currentSum
        sumTemp +=root.`val`

        //leaf node check
        if(root.left ==null && root.right==null){
            return sumTemp == target
        }
        val leftAns = helper(root.left, sumTemp)
        val rightAns = helper(root.right, sumTemp)
        return leftAns || rightAns
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _112PathSum()

            // 🧪 Test Case 1: Valid path exists (5→4→11→2 = 22)
            val root1 = TreeNode(5,
                left = TreeNode(4,
                    left = TreeNode(11,
                        left = TreeNode(7),
                        right = TreeNode(2)
                    )
                ),
                right = TreeNode(8,
                    left = TreeNode(13),
                    right = TreeNode(4,
                        right = TreeNode(1)
                    )
                )
            )
            println("Test 1: Has path sum = 22? → ${solver.hasPathSum(root1, 22)}") // ✅ true

            // 🧪 Test Case 2: No valid path
            val root2 = TreeNode(1,
                left = TreeNode(2),
                right = TreeNode(3)
            )
            println("Test 2: Has path sum = 5? → ${solver.hasPathSum(root2, 5)}") // ❌ false

            // 🧪 Test Case 3: Empty tree
            println("Test 3: Has path sum in empty tree? → ${solver.hasPathSum(null, 0)}") // ❌ false

            // 🧪 Test Case 4: Single node tree
            val root4 = TreeNode(1)
            println("Test 4: Has path sum = 1? → ${solver.hasPathSum(root4, 1)}") // ✅ true
        }
    }
}
    