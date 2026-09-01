package com.kritsn.leetCodeJava;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
 * Leetcode 103: Binary Tree Zigzag Level Order Traversal
 * Return the zigzag level order traversal of its nodes' values.
 */
public class _103ZigzagLevelOrderTraversal {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // https://youtu.be/igbboQbiwqw?feature=shared
    // 🧠 Algorithm & Approach:
    //
    // - Use BFS (level order traversal) with a queue
    // - For each level, collect node values in a temporary list
    // - If current level is to be traversed right to left, reverse the list
    // - Flip the direction for the next level
    //
    // ⏱ Time Complexity: O(n)
    // ⏳ Space Complexity: O(n)
    ///////////////////////////////////////////////////////////////////////////
    List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();

                // Add node value in order depending on direction
                if (leftToRight) {
                    currentLevel.addLast(node.val);
                } else {
                    currentLevel.addFirst(node.val); // Insert at beginning for right-to-left
                }

                // Add child nodes to queue for next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            // Append current level to result and flip direction
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }

        return result;
    }

    public static void main(String[] args) {
        _103ZigzagLevelOrderTraversal sol = new _103ZigzagLevelOrderTraversal();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1: " + sol.zigzagLevelOrder(root1)); // [[3], [20, 9], [15, 7]]

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test Case 2: " + sol.zigzagLevelOrder(root2)); // [[1]]

        // 🧪 Test Case 3
        System.out.println("Test Case 3: " + sol.zigzagLevelOrder(null)); // []
    }
}
