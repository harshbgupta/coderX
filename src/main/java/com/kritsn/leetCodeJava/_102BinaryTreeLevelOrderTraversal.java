package com.kritsn.leetCodeJava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 102: Binary Tree Level Order Traversal

Given the root of a binary tree, return the level order traversal of its nodes' values.
(i.e., from left to right, level by level).
*/
public class _102BinaryTreeLevelOrderTraversal {

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
     * https://youtu.be/6ZnyEApgFYg?feature=shared
     * 🧠 Algorithm & Approach:
     * - Use BFS traversal with a queue to process the tree level by level.
     * - At each level, collect node values into a list.
     * - Add children of current level nodes to the queue.
     * <p>
     * Time Complexity: O(n) — visiting each node once.
     * Space Complexity: O(n) — queue + result list.
     */
    List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();
                currentLevel.add(node.val);

                // Enqueue left and right children
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        _102BinaryTreeLevelOrderTraversal sol = new _102BinaryTreeLevelOrderTraversal();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1: " + sol.levelOrder(root1)); // Expected: [[3], [9, 20], [15, 7]]

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test Case 2: " + sol.levelOrder(root2)); // Expected: [[1]]

        // 🧪 Test Case 3
        System.out.println("Test Case 3: " + sol.levelOrder(null)); // Expected: []
    }
}
