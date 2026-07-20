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

/**
 * Leetcode 199: Binary Tree Right Side View
 * Given the root of a binary tree, return the values of the nodes visible from the right side.
 */
public class _199BinaryTreeRightSideView {

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
    // 🧠 Algorithm & Approach:
    //
    // We perform a level-order traversal (BFS), and for each level,
    // we add the value of the last node (i.e., rightmost node at that level)
    // to the result list.
    //
    // ⏱ Time Complexity: O(n) — visit each node once
    // ⏳ Space Complexity: O(n) — for queue and output list
    ///////////////////////////////////////////////////////////////////////////
    List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        // Start level-order traversal
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();

                // If it's the last node in the level, it's visible from the right
                if (i == size - 1) {
                    result.add(node.val);
                }

                // Add child nodes for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        _199BinaryTreeRightSideView solution = new _199BinaryTreeRightSideView();

        // 🧪 Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.right = new TreeNode(5);
        root1.right = new TreeNode(3);
        root1.right.right = new TreeNode(4);
        System.out.println("Test Case 1: Output = " + solution.rightSideView(root1)); // Expected: [1, 3, 4]

        // 🧪 Test Case 2
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test Case 2: Output = " + solution.rightSideView(root2)); // Expected: [1]

        // 🧪 Test Case 3
        System.out.println("Test Case 3: Output = " + solution.rightSideView(null)); // Expected: []
    }
}
