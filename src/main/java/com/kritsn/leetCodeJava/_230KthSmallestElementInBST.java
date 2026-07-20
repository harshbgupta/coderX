package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode Problem: 230. Kth Smallest Element in a BST

Given the root of a binary search tree and an integer k,
return the kth smallest value (1-indexed) among all node values.
*/
public class _230KthSmallestElementInBST {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int count = 0;   // Counter to track the number of visited nodes
    private int result = -1; // Holds the final kth smallest value

    ///////////////////////////////////////////////////////////////////////////
    // 🧠 Algorithm & Approach:
    // - Use in-order traversal (left -> root -> right) to process nodes in sorted order.
    // - Increment counter each time a node is visited.
    // - When counter equals k, store the current node value as result.
    //
    // ⏱ Time Complexity: O(H + k) in the average case, O(n) worst case.
    // ⏱ Space Complexity: O(h) due to recursion stack (h = tree height)
    ///////////////////////////////////////////////////////////////////////////
    int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inOrder(root, k);
        return result;
    }

    // 🔁 In-order traversal helper function
    private void inOrder(TreeNode node, int k) {
        if (node == null) return;

        inOrder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inOrder(node.right, k);
    }

    public static void main(String[] args) {
        _230KthSmallestElementInBST solution = new _230KthSmallestElementInBST();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);
        root1.right = new TreeNode(4);
        System.out.println("Test Case 1 Output: " + solution.kthSmallest(root1, 1)); // Expected: 1

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(4);
        root2.right = new TreeNode(6);
        System.out.println("Test Case 2 Output: " + solution.kthSmallest(root2, 3)); // Expected: 3

        // 🧪 Test Case 3
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3 Output: " + solution.kthSmallest(root3, 1)); // Expected: 1
    }
}
