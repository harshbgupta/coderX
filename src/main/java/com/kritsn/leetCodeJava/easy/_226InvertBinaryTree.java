package com.kritsn.leetCodeJava.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 📄 Problem: Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 */
public class _226InvertBinaryTree {

    // 👇 Definition for a binary tree node placed inside the main class for future reference.
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
     * - Use recursive DFS to invert the binary tree.
     * - For each node, swap the left and right subtrees.
     * - Recursively apply this to left and right children.
     * <p>
     * ⏱ Time Complexity: O(N), where N is the number of nodes (each node visited once)
     * 🗂 Space Complexity: O(H), where H is the height of the tree (recursion stack)
     */
    TreeNode invertTree(TreeNode root) {
        // Base case: If node is null, just return null.
        if (root == null) return null;

        // Recursively invert left and right subtrees.
        TreeNode leftInverted = invertTree(root.left);
        TreeNode rightInverted = invertTree(root.right);

        // Swap the inverted children.
        root.left = rightInverted;
        root.right = leftInverted;

        // Return the current root after inverting its subtrees.
        return root;
    }

    // Helper function to print tree in level-order
    private static List<Integer> printTree(TreeNode root) {
        List<Integer> result = new java.util.ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add(null);
            } else {
                result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        // Trim trailing nulls for cleaner output
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        _226InvertBinaryTree solution = new _226InvertBinaryTree();

        // 🧪 Test Case 1:
        TreeNode root1 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );
        TreeNode inverted1 = solution.invertTree(root1);
        System.out.println("Test Case 1 Output: " + printTree(inverted1));
        // Expected Output: [4, 7, 2, 9, 6, 3, 1]

        // 🧪 Test Case 2:
        TreeNode root2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode inverted2 = solution.invertTree(root2);
        System.out.println("Test Case 2 Output: " + printTree(inverted2));
        // Expected Output: [2, 3, 1]

        // 🧪 Test Case 3: Null input
        TreeNode inverted3 = solution.invertTree(null);
        System.out.println("Test Case 3 Output: " + printTree(inverted3));
        // Expected Output: []
    }
}
