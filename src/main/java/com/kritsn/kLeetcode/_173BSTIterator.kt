package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 24, 2025
///////////////////////////////////////////////////////////////////////////
/*
Leetcode 173: Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST).
It should return the nodes in ascending (in-order) order one at a time.
*/

class _173BSTIterator(root: TreeNode?) {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    // Stack to simulate in-order traversal
    private val stack = ArrayDeque<TreeNode>()

    init {
        // Initially push all the way left from the root
        pushLeftBranch(root)
    }

    /**
     * Push all left children from the current node to the stack
     * This simulates going to the next smallest element in BST
     */
    private fun pushLeftBranch(node: TreeNode?) {
        var current = node
        while (current != null) {
            stack.addLast(current)
            current = current.left
        }
    }

    /** @return the next smallest number */
    fun next(): Int {
        // Pop the next node in in-order sequence
        val node = stack.removeLast()

        // If the node has a right child, push its left subtree
        if (node.right != null) {
            pushLeftBranch(node.right)
        }

        return node.`val`
    }

    /** @return whether we have a next smallest number */
    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Create BST: [7, 3, 15, null, null, 9, 20]
            val root = TreeNode(7).apply {
                left = TreeNode(3)
                right = TreeNode(15).apply {
                    left = TreeNode(9)
                    right = TreeNode(20)
                }
            }

            val iterator = _173BSTIterator(root)

            // 🧪 Expected Output: 3, 7, 9, 15, 20
            val results = mutableListOf<Int>()
            while (iterator.hasNext()) {
                results.add(iterator.next())
            }

            println("In-order Traversal: $results")
        }
    }
}