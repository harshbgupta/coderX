package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 530: Minimum Absolute Difference in BST

Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
*/
public class _530MinAbsDiffInBST {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private Integer prevValue = null;        // Keeps track of previous node during in-order traversal
    private int minDiff = Integer.MAX_VALUE; // Stores the minimum absolute difference

    ///////////////////////////////////////////////////////////////////////////
    // 🧠 Algorithm & Approach:
    // - Use in-order traversal to visit BST in ascending order.
    // - At each node, calculate difference from the previous node.
    // - Update minDiff with the smallest difference found.
    //
    // ⏱ Time Complexity: O(n)
    // ⏱ Space Complexity: O(h) — recursion stack (h = tree height)
    ///////////////////////////////////////////////////////////////////////////
    int getMinimumDifference(TreeNode root) {
        prevValue = null;
        minDiff = Integer.MAX_VALUE;
        inOrder(root);
        return minDiff;
    }

    // 🔁 In-order traversal: left -> root -> right
    private void inOrder(TreeNode node) {
        if (node == null) return;

        inOrder(node.left);

        // If previous node exists, compute the difference
        if (prevValue != null) {
            int diff = Math.abs(node.val - prevValue);
            minDiff = Math.min(minDiff, diff);
        }
        prevValue = node.val; // Update previous node value

        inOrder(node.right);
    }

    public static void main(String[] args) {
        _530MinAbsDiffInBST solution = new _530MinAbsDiffInBST();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right = new TreeNode(6);
        System.out.println("Test Case 1 Output: " + solution.getMinimumDifference(root1)); // Expected: 1

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);
        System.out.println("Test Case 2 Output: " + solution.getMinimumDifference(root2)); // Expected: 2

        // 🧪 Test Case 3
        TreeNode root3 = new TreeNode(543);
        root3.left = new TreeNode(384);
        root3.left.right = new TreeNode(445);
        root3.right = new TreeNode(652);
        root3.right.right = new TreeNode(699);
        System.out.println("Test Case 3 Output: " + solution.getMinimumDifference(root3)); // Expected: 47
    }
}
