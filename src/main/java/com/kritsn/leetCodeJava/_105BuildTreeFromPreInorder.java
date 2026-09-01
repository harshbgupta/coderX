package com.kritsn.leetCodeJava;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode 105: Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */
public class _105BuildTreeFromPreInorder {

    /**
     * 🌳 Basic TreeNode definition.
     */
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
     * - The first element in preorder is always the root.
     * - Find that root in inorder to split into left and right subtree.
     * - Recurse on left and right portions of preorder and inorder accordingly.
     * - Use a HashMap to store value -> index of inorder for quick lookup.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) for HashMap and recursion stack
     */
    TreeNode buildTree(int[] preorder, int[] inorder) {
        // Step 1: Build a map of inorder values and their indices for fast lookup
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        // Step 2: Recursive helper to build the tree
        return buildTreeRecursive(
                preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                inMap
        );
    }

    // 🧩 Recursive function to construct the tree using preorder and inorder slices
    private TreeNode buildTreeRecursive(
            int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inMap
    ) {
        // Base condition: If range is invalid, return null
        if (preStart > preEnd || inStart > inEnd) return null;

        // First element in preorder is the root node
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Index of root in inorder traversal
        Integer inRoot = inMap.get(rootVal);
        if (inRoot == null) return null;

        // Number of nodes in left subtree
        int numsLeft = inRoot - inStart;

        // Construct left and right subtrees recursively
        root.left = buildTreeRecursive(
                preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1,
                inMap
        );

        root.right = buildTreeRecursive(
                preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd,
                inMap
        );

        return root;
    }

    public static void main(String[] args) {
        _105BuildTreeFromPreInorder builder = new _105BuildTreeFromPreInorder();

        // 🧪 Test Case 1
        /*
               3
              / \
             9  20
                / \
               15  7
         */
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode root1 = builder.buildTree(preorder1, inorder1);
        System.out.println("Test Case 1: Tree Root = " + root1.val + " (Expected: 3)");

        // 🧪 Test Case 2
        /*
             1
            /
           2
         */
        int[] preorder2 = {1, 2};
        int[] inorder2 = {2, 1};
        TreeNode root2 = builder.buildTree(preorder2, inorder2);
        System.out.println("Test Case 2: Tree Root = " + root2.val + " (Expected: 1), Left Child = " + root2.left.val + " (Expected: 2)");

        // 🧪 Test Case 3
        /*
             1
         */
        int[] preorder3 = {1};
        int[] inorder3 = {1};
        TreeNode root3 = builder.buildTree(preorder3, inorder3);
        System.out.println("Test Case 3: Tree Root = " + root3.val + " (Expected: 1)");
    }
}
