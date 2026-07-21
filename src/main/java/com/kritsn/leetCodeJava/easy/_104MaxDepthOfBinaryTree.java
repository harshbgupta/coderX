package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode: 104. Maximum Depth of Binary Tree
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 */
public class _104MaxDepthOfBinaryTree {

    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 🧠 Algorithm & Approach:
     * - Base case: If the current node is null, return 0.
     * - Recursively compute max depth of left and right subtrees.
     * - Return 1 + maximum of left and right subtree depths.
     * <p>
     * 📦 Time Complexity: O(n) where n = number of nodes
     * 💾 Space Complexity: O(h) where h = height of the tree (recursive call stack)
     */
    int maxDepth(TreeNode root) {
        // If the current node is null, return 0 (base case)
        if (root == null) return 0;

        // Recursively calculate depth of left subtree
        int leftDepth = maxDepth(root.left);

        // Recursively calculate depth of right subtree
        int rightDepth = maxDepth(root.right);

        // Return 1 (for current node) + max of left and right subtree depths
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        _104MaxDepthOfBinaryTree solution = new _104MaxDepthOfBinaryTree();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1: Input = [3,9,20,null,null,15,7]");
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output: " + solution.maxDepth(root1));
        System.out.println("------");

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        System.out.println("Test Case 2: Input = [1,null,2]");
        System.out.println("Expected Output: 2");
        System.out.println("Actual Output: " + solution.maxDepth(root2));
        System.out.println("------");

        // 🧪 Test Case 3 (Edge case: empty tree)
        TreeNode root3 = null;
        System.out.println("Test Case 3: Input = []");
        System.out.println("Expected Output: 0");
        System.out.println("Actual Output: " + solution.maxDepth(root3));
        System.out.println("------");
    }
}
