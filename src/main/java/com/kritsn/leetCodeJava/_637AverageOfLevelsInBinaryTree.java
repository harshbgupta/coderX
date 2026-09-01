package com.kritsn.leetCodeJava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 637: Average of Levels in Binary Tree

Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
Answers within 10^-5 of the actual answer will be accepted.
*/
public class _637AverageOfLevelsInBinaryTree {

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
     * 🧠 Algorithm & Approach:
     * - Perform a level order traversal using a queue.
     * - At each level, sum the values of all nodes.
     * - Compute average by dividing sum by the number of nodes at that level.
     * - Append average to result list.
     * <p>
     * Time Complexity: O(n) — visiting every node once.
     * Space Complexity: O(n) — for queue and result storage.
     */
    List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0.0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.removeFirst();
                levelSum += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(levelSum / levelSize);
        }

        return result;
    }

    public static void main(String[] args) {
        _637AverageOfLevelsInBinaryTree sol = new _637AverageOfLevelsInBinaryTree();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1: " + sol.averageOfLevels(root1)); // Expected: [3.0, 14.5, 11.0]

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(10);
        System.out.println("Test Case 2: " + sol.averageOfLevels(root2)); // Expected: [10.0]

        // 🧪 Test Case 3
        System.out.println("Test Case 3: " + sol.averageOfLevels(null)); // Expected: []
    }
}
