package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
class _222CountCompleteTreeNodes() {
    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * https://youtu.be/d4zLyf32e3I?feature=shared
     *
     * 🧠 Optimized Recursive Approach (O(log^2 N)):
     * - For each subtree, compare the depth of left-most and right-most paths.
     * - If equal → it's a perfect binary subtree ⇒ return 2^depth - 1 nodes.
     * - If not equal → recursively count left and right subtree sizes.
     *
     * Time: O(log^2 N), Space: O(log N) for recursion
     */
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        // Get left-most height
        fun leftHeight(node: TreeNode?): Int {
            var h = 0
            var curr = node
            while (curr != null) {
                h++
                curr = curr.left
            }
            return h
        }

        // Get right-most height
        fun rightHeight(node: TreeNode?): Int {
            var h = 0
            var curr = node
            while (curr != null) {
                h++
                curr = curr!!.right
            }
            return h
        }

        val lh = leftHeight(root)
        val rh = rightHeight(root)

        return if (lh == rh) {
            // Tree is perfect ⇒ total nodes = 2^height - 1
            (1 shl lh) - 1
        } else {
            // Otherwise, recursively count left and right subtrees
            1 + countNodes(root.left) + countNodes(root.right)
        }
    }


    /**
     * Simply count the nodes
    */
    fun countNodesOther(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val leftCount = countNodes(root.left)
        val rightCount = countNodes(root.right)
        return 1 + leftCount + rightCount
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tree = _222CountCompleteTreeNodes()

            // 🧪 Test Case 1: Complete tree [1,2,3,4,5,6]
            val root1 = TreeNode(1).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(4)
                    right = TreeNode(5)
                }
                right = TreeNode(3).apply {
                    left = TreeNode(6)
                }
            }
            println("Test Case 1: Expected = 6, Got = ${tree.countNodes(root1)}")

            // 🧪 Test Case 2: Single node
            val root2 = TreeNode(1)
            println("Test Case 2: Expected = 1, Got = ${tree.countNodes(root2)}")

            // 🧪 Test Case 3: Null tree
            println("Test Case 3: Expected = 0, Got = ${tree.countNodes(null)}")

            // 🧪 Test Case 4: Full tree of height 3
            val root4 = TreeNode(1).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(4)
                    right = TreeNode(5)
                }
                right = TreeNode(3).apply {
                    left = TreeNode(6)
                    right = TreeNode(7)
                }
            }
            println("Test Case 4: Expected = 7, Got = ${tree.countNodes(root4)}")
        }
    }
}