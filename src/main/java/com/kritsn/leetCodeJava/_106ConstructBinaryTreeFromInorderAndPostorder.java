package com.kritsn.leetCodeJava;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 106: Construct Binary Tree from Inorder and Postorder Traversal
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
*/
public class _106ConstructBinaryTreeFromInorderAndPostorder {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // https://youtu.be/LgLRTaEMRVc?feature=shared
    // 🧠 Algorithm & Approach:
    // - The last element in postorder is the root.
    // - Use HashMap for O(1) lookup of root index in inorder array.
    // - Recurse by processing postorder from end to start.
    // - Build right subtree first, then left.
    // - Maintain postorderIndex pointer globally as we move backward.

    /**
     * Main method to build the binary tree from inorder and postorder traversal arrays.
     * It maps each value in the inorder array to its index for O(1) root index lookup.
     */
    TreeNode buildTree(int[] inorder, int[] postorder) {
        // Base case check
        if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;

        // Map to store value -> index for quick lookup in inorder
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Recursive call with boundaries
        return buildSubTree(
                inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1,
                inorderIndexMap
        );
    }

    /**
     * Recursively constructs the binary tree from given bounds.
     */
    private TreeNode buildSubTree(
            int[] inorder, int inStart, int inEnd,
            int[] postorder, int postStart, int postEnd,
            Map<Integer, Integer> inorderIndexMap
    ) {
        // Base case: no elements to process
        if (postStart > postEnd || inStart > inEnd) return null;

        // The last element in postorder is the root of the current subtree
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        // Get the index of the root in inorder array to divide left and right subtree
        int rootIndexInInorder = inorderIndexMap.get(rootValue);

        // Count of elements in the left subtree
        int leftSubtreeSize = rootIndexInInorder - inStart;

        // Build left and right subtrees recursively
        root.left = buildSubTree(
                inorder, inStart, rootIndexInInorder - 1,
                postorder, postStart, postStart + leftSubtreeSize - 1,
                inorderIndexMap
        );

        root.right = buildSubTree(
                inorder, rootIndexInInorder + 1, inEnd,
                postorder, postStart + leftSubtreeSize, postEnd - 1,
                inorderIndexMap
        );

        return root;
    }

    public static void main(String[] args) {
        _106ConstructBinaryTreeFromInorderAndPostorder builder = new _106ConstructBinaryTreeFromInorderAndPostorder();

        // 🧪 Test Case 1
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode root1 = builder.buildTree(inorder1, postorder1);
        System.out.println("Test Case 1: Root = " + root1.val + " (Expected: 3)");

        // 🧪 Test Case 2
        int[] inorder2 = {2, 1};
        int[] postorder2 = {2, 1};
        TreeNode root2 = builder.buildTree(inorder2, postorder2);
        System.out.println("Test Case 2: Root = " + root2.val + " (Expected: 1), Left = " + root2.left.val + " (Expected: 2)");

        // 🧪 Test Case 3
        int[] inorder3 = {1};
        int[] postorder3 = {1};
        TreeNode root3 = builder.buildTree(inorder3, postorder3);
        System.out.println("Test Case 3: Root = " + root3.val + " (Expected: 1)");
    }
}
