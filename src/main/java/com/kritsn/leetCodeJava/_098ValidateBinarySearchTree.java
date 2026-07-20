package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode Problem: 98. Validate Binary Search Tree

Given the root of a binary tree, determine if it is a valid binary search tree (BST).
*/
public class _098ValidateBinarySearchTree {

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
     * https://youtu.be/dSBcCynP1nA?feature=shared
     * 🧠 Algorithm & Approach:
     * - Use recursion to validate the BST property.
     * - Each node must lie in the valid range (min, max).  ---> Imp Note
     * - Recursively validate left and right subtrees by updating the range. ---> Imp Note
     * <p>
     * Time Complexity: O(n) — visit each node once
     * Space Complexity: O(h) — recursion stack (h = tree height)
     */
    boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Helper function to validate BST within min..max range
    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        // Current node must be strictly between min and max
        if (node.val <= min || node.val >= max) return false;

        // Left subtree must be in (min, node.val)
        // Right subtree must be in (node.val, max)
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        _098ValidateBinarySearchTree validator = new _098ValidateBinarySearchTree();
        // 🧪 IMP: Test Case IMP: Invalid BST (3 in right of main node 5, and as per BST all smaller value should be left of the node not just adjusent top it applies for ancestors)
        /* Visual Representation of the Binary Tree:
            5
          /   \
         1     6
              / \
             3   7
        */
        TreeNode rootImp = new TreeNode(5);
        rootImp.left = new TreeNode(1);
        rootImp.right = new TreeNode(6);
        rootImp.right.left = new TreeNode(3);
        rootImp.right.right = new TreeNode(7);
        System.out.println("Test Case <<Imp>>: " + validator.isValidBST(rootImp) + " (Expected: false)");

        // 🧪 Test Case 1: Valid BST
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("Test Case 1: " + validator.isValidBST(root1) + " (Expected: true)");

        // 🧪 Test Case 2: Invalid BST (3 in left subtree of 5)
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("Test Case 2: " + validator.isValidBST(root2) + " (Expected: false)");

        // 🧪 Test Case 3: Single Node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + validator.isValidBST(root3) + " (Expected: true)");
    }
}
