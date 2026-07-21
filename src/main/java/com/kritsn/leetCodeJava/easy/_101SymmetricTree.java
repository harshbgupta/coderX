package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode Problem: Symmetric Tree
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
*/
public class _101SymmetricTree {

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

    /**
     * 🧠 Algorithm & Approach:
     * This function checks whether a binary tree is symmetric around its center.
     * - We define a helper function `isMirror` to compare the left and right subtrees recursively.
     * - Base cases handle when both nodes are null (true) or when one is null (false).
     * - If both nodes exist, we check their values and recursively check their children in mirrored order.
     * <p>
     * Time Complexity: O(n) - we visit each node once
     * Space Complexity: O(h) - recursion stack height, worst O(n) if unbalanced
     */
    boolean isSymmetric(TreeNode root) {
        return isMirror(root == null ? null : root.left, root == null ? null : root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // Both nodes are null, symmetric
        if (t1 == null && t2 == null) return true;
        // One is null, the other is not, not symmetric
        if (t1 == null || t2 == null) return false;
        // Values must match and the subtrees must mirror each other
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        _101SymmetricTree tree = new _101SymmetricTree();

        // 🧪 Test Case 1: Symmetric
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );
        System.out.println("Test Case 1: " + tree.isSymmetric(root1)); // Expected: true

        // 🧪 Test Case 2: Asymmetric
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3))
        );
        System.out.println("Test Case 2: " + tree.isSymmetric(root2)); // Expected: false

        // 🧪 Test Case 3: Single Node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + tree.isSymmetric(root3)); // Expected: true
    }
}
