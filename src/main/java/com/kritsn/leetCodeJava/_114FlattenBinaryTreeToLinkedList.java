package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode 114 - Flatten Binary Tree to Linked List
 * Given the root of a binary tree, flatten it to a linked list in-place (in pre-order traversal order).
 */
public class _114FlattenBinaryTreeToLinkedList {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode prev;

    /**
     * 🧠 Algorithm & Approach:
     * - Use reverse pre-order traversal (right → left → root)
     * - Maintain a 'prev' pointer which always points to the previously processed node
     * - As we visit nodes, we:
     * - Set current.right = prev
     * - Set current.left = null
     * - Move prev = current
     * <p>
     * This way, we "flatten" the tree in reverse order, eventually forming a single rightward chain.
     * <p>
     * ⏱ Time Complexity: O(n)
     * ⏱ Space Complexity: O(h) (recursive stack height, h = height of tree)
     */
    void flatten(TreeNode root) {
        prev = null;
        reversePreorder(root);
    }

    private void reversePreorder(TreeNode node) {
        if (node == null) return;

        // Traverse right first, then left (reverse pre-order)
        reversePreorder(node.right);
        reversePreorder(node.left);

        // Flatten: link current node to previously visited node
        node.right = prev;
        node.left = null;
        prev = node;
    }

    public static void main(String[] args) {
        _114FlattenBinaryTreeToLinkedList solver = new _114FlattenBinaryTreeToLinkedList();

        // Test tree:
        //        1
        //       / \
        //      2   5
        //     / \   \
        //    3   4   6
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6))
        );

        solver.flatten(root);

        // Print flattened tree (should be: 1 → 2 → 3 → 4 → 5 → 6)
        TreeNode curr = root;
        System.out.print("Flattened Tree (Pre-order as linked list): ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            if (curr.left != null) {
                System.out.println("❌ Left child found (Invalid)");
                break;
            }
            curr = curr.right;
        }
    }
}
