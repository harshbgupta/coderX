package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 23, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Leetcode 114 - Flatten Binary Tree to Linked List
 * Given the root of a binary tree, flatten it to a linked list in-place (in pre-order traversal order).
 */
class _114FlattenBinaryTreeToLinkedList {

    // ✅ Definition for a binary tree node
    data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * 🧠 Algorithm & Approach:
     * - Use reverse pre-order traversal (right → left → root)
     * - Maintain a 'prev' pointer which always points to the previously processed node
     * - As we visit nodes, we:
     *   - Set current.right = prev
     *   - Set current.left = null
     *   - Move prev = current
     *
     * This way, we "flatten" the tree in reverse order, eventually forming a single rightward chain.
     *
     * ⏱ Time Complexity: O(n)
     * ⏱ Space Complexity: O(h) (recursive stack height, h = height of tree)
     */
    fun flatten(root: TreeNode?) {
        var prev: TreeNode? = null

        fun reversePreorder(node: TreeNode?) {
            if (node == null) return

            // Traverse right first, then left (reverse pre-order)
            reversePreorder(node.right)
            reversePreorder(node.left)

            // Flatten: link current node to previously visited node
            node.right = prev
            node.left = null
            prev = node
        }

        reversePreorder(root)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _114FlattenBinaryTreeToLinkedList()

            // Test tree:
            //        1
            //       / \
            //      2   5
            //     / \   \
            //    3   4   6
            val root = TreeNode(1,
                left = TreeNode(2,
                    left = TreeNode(3),
                    right = TreeNode(4)
                ),
                right = TreeNode(5,
                    right = TreeNode(6)
                )
            )

            solver.flatten(root)

            // Print flattened tree (should be: 1 → 2 → 3 → 4 → 5 → 6)
            var curr: TreeNode? = root
            print("Flattened Tree (Pre-order as linked list): ")
            while (curr != null) {
                print("${curr.`val`} ")
                if (curr.left != null) {
                    println("❌ Left child found (Invalid)")
                    break
                }
                curr = curr.right
            }
        }
    }
}