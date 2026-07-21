package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode 112. Path Sum
 * Given a binary tree and a target sum, determine if the tree has a root-to-leaf path such that the sum equals the target.
 */
public class _112PathSum {

    // ✅ Definition for a binary tree node
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
     * Solution 1
     * 🧠 Algorithm & Approach:
     * - Use DFS traversal (recursion).
     * - At each node, subtract its value from the current targetSum.
     * - If it's a leaf and targetSum == node value, return true.
     * - Recurse down left and right subtrees.
     * <p>
     * ⏱ Time Complexity: O(n) - where n is the number of nodes
     * ⏱ Space Complexity: O(h) - recursion stack, h = height of the tree
     */
    boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false; // Empty tree has no path

        // If we reached a leaf, check if path sum equals targetSum
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Subtract current node's value and recur on children
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Solution 2
    // https://www.youtube.com/watch?v=ANR85j2_ir0
    ///////////////////////////////////////////////////////////////////////////
    private int target;

    boolean hasPathSum2(TreeNode root, int targetSum) {
        target = targetSum;
        return helper(root, 0);
    }

    private boolean helper(TreeNode root, int currentSum) {
        if (root == null) {
            return false;
        }
        int sumTemp = currentSum;
        sumTemp += root.val;

        // leaf node check
        if (root.left == null && root.right == null) {
            return sumTemp == target;
        }
        boolean leftAns = helper(root.left, sumTemp);
        boolean rightAns = helper(root.right, sumTemp);
        return leftAns || rightAns;
    }

    public static void main(String[] args) {
        _112PathSum solver = new _112PathSum();

        // 🧪 Test Case 1: Valid path exists (5→4→11→2 = 22)
        TreeNode root1 = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4, null, new TreeNode(1)))
        );
        System.out.println("Test 1: Has path sum = 22? → " + solver.hasPathSum(root1, 22)); // ✅ true

        // 🧪 Test Case 2: No valid path
        TreeNode root2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 2: Has path sum = 5? → " + solver.hasPathSum(root2, 5)); // ❌ false

        // 🧪 Test Case 3: Empty tree
        System.out.println("Test 3: Has path sum in empty tree? → " + solver.hasPathSum(null, 0)); // ❌ false

        // 🧪 Test Case 4: Single node tree
        TreeNode root4 = new TreeNode(1);
        System.out.println("Test 4: Has path sum = 1? → " + solver.hasPathSum(root4, 1)); // ✅ true
    }
}
