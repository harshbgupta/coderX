package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 124: Binary Tree Maximum Path Sum

A path is any sequence of nodes connected via parent-child links (not necessarily through root).
Each node can be included at most once.
The path sum is the sum of node values in the path.

Return the maximum path sum of any non-empty path.
*/
public class _124BinaryTreeMaximumPathSum {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left) {
            this.val = val;
            this.left = left;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int maxSumInternal; // 🟢 tracks best sum across the recursive helper

    /**
     * https://www.youtube.com/watch?v=WszrfSwMz58
     * 🧠 Algorithm & Approach:
     * - Use DFS to compute maximum gain from each subtree.
     * - Ignore negative gains (don't include them in path).
     * - Track the highest path sum seen so far (may include both children).
     * <p>
     * Time Complexity: O(n), we visit each node once.
     * Space Complexity: O(h), recursion stack (h = tree height).
     */
    int maxPathSum(TreeNode root) {
        return helper1(root);
    }

    private int helper1(TreeNode root) {
        maxSumInternal = Integer.MIN_VALUE;
        dfs(root);
        return maxSumInternal;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // 🔁 Recurse into left and right, ignore negative contributions
        int leftGain = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));

        // 🔄 Current path sum using both children + current node
        int currentPathSum = node.val + leftGain + rightGain;

        // 🔼 Update global max path sum if current is better
        maxSumInternal = Math.max(maxSumInternal, currentPathSum);

        // ⬅️ Return max gain for parent to use (only one child allowed)
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        _124BinaryTreeMaximumPathSum solver = new _124BinaryTreeMaximumPathSum();

        // 🧪 Test Case 1: Tree = [1,2,3]
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 1 Output: " + solver.maxPathSum(root1)); // Expected: 6

        // 🧪 Test Case 2: Tree = [-10,9,20,null,null,15,7]
        TreeNode root2 = new TreeNode(
                -10,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Test 2 Output: " + solver.maxPathSum(root2)); // Expected: 42

        // 🧪 Test Case 3: Tree = [2,-1]
        TreeNode root3 = new TreeNode(2, new TreeNode(-1));
        System.out.println("Test 3 Output: " + solver.maxPathSum(root3)); // Expected: 2

        // 🧪 Test Case 4: Tree = [-3]
        TreeNode root4 = new TreeNode(-3);
        System.out.println("Test 4 Output: " + solver.maxPathSum(root4)); // Expected: -3
    }
}
