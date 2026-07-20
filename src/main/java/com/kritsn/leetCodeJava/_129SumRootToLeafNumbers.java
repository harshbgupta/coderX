package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode 129: Sum Root to Leaf Numbers

You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
Return the total sum of all the numbers formed by root-to-leaf paths.
A leaf is a node with no children.
*/
public class _129SumRootToLeafNumbers {

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
     * 🧠 Algorithm & Approach:
     * - Traverse tree using DFS.
     * - Maintain currentSum by appending digits → currentSum = currentSum * 10 + node.val.
     * - When a leaf is reached, return the formed number.
     * - Accumulate left and right subtree values and return total.
     * <p>
     * ⏱ Time Complexity: O(n) — each node visited once.
     * ⏱ Space Complexity: O(h) — due to recursion stack (h = tree height).
     */
    int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;

        // Update the path number by appending the current digit
        int newSum = currentSum * 10 + node.val;

        // If it's a leaf node, return the number formed so far
        if (node.left == null && node.right == null) {
            return newSum;
        }

        // Recurse on left and right subtrees and return the total
        int leftSum = dfs(node.left, newSum);
        int rightSum = dfs(node.right, newSum);

        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        _129SumRootToLeafNumbers solver = new _129SumRootToLeafNumbers();

        // 🧪 Test Case 1: Tree [1,2,3] → Numbers: 12, 13 → Sum: 25
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 1: Sum = " + solver.sumNumbers(root1)); // Expected: 25

        // 🧪 Test Case 2: Tree [4,9,0,5,1] → Numbers: 495, 491, 40 → Sum: 1026
        TreeNode root2 = new TreeNode(4,
                new TreeNode(9, new TreeNode(5), new TreeNode(1)),
                new TreeNode(0)
        );
        System.out.println("Test 2: Sum = " + solver.sumNumbers(root2)); // Expected: 1026

        // 🧪 Test Case 3: Tree [0] → Number: 0
        TreeNode root3 = new TreeNode(0);
        System.out.println("Test 3: Sum = " + solver.sumNumbers(root3)); // Expected: 0

        // 🧪 Test Case 4: Empty Tree
        System.out.println("Test 4: Sum = " + solver.sumNumbers(null)); // Expected: 0
    }
}
