package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode Problem: Lowest Common Ancestor of a Binary Tree
Given a binary tree, find the lowest common ancestor of two given nodes in the tree.

Definition:
The LCA is the lowest node in the tree that has both nodes p and q as descendants.
*/
public class _236LowestCommonAncestorBinaryTree {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * https://youtu.be/K_oNnoUeDJE?feature=shared
     * 🧠 Recursive DFS Approach:
     * - Traverse the tree from the root.
     * - If current node is null, return null.
     * - If current node is either p or q, return current node.
     * - Recurse left and right.
     * - If both left and right return non-null, current node is LCA.
     * - Else return the non-null result.
     * <p>
     * ⏱ Time Complexity: O(n), where n = number of nodes in the tree.
     * 🛠 Space Complexity: O(h), where h = height of tree (due to recursion stack).
     */
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: null node
        if (root == null) return null;

        // If current node matches either p or q, return it
        if (root == p || root == q) return root;

        // Recurse left and right
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        // If both sides return a node, current node is the LCA
        if (leftLCA != null && rightLCA != null) return root;

        // Otherwise return non-null child (or null if both are null)
        return leftLCA != null ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        _236LowestCommonAncestorBinaryTree solution = new _236LowestCommonAncestorBinaryTree();

        // Constructing the binary tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p = root.left;               // Node with value 5
        TreeNode q = root.left.right.right;   // Node with value 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("Test Case 1: LCA of " + p.val + " and " + q.val + " is " + lca.val + " (Expected: 5)");

        TreeNode p2 = root.left;  // Node 5
        TreeNode q2 = root.right; // Node 1
        TreeNode lca2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("Test Case 2: LCA of " + p2.val + " and " + q2.val + " is " + lca2.val + " (Expected: 3)");
    }
}
