package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Leetcode Problem: Same Tree

Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
*/
public class _100SameTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Recursive approach to check if two trees are identical.
     * <p>
     * Time Complexity: O(n), where n is the number of nodes (in worst case we compare all nodes).
     * Space Complexity: O(h), where h is the height of the tree (due to recursion stack).
     */
    boolean isSameTree(TreeNode p, TreeNode q) {
        // If both nodes are null, they are structurally the same
        if (p == null && q == null) return true;

        // If one is null and the other is not, they are not the same
        if (p == null || q == null) return false;

        // If values differ, trees are not the same
        if (p.val != q.val) return false;

        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        _100SameTree solution = new _100SameTree();

        // Example 1: p = [1,2,3], q = [1,2,3] → true
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);
        System.out.println("Test Case 1: " + solution.isSameTree(p1, q1)); // Expected: true

        // Example 2: p = [1,2], q = [1,null,2] → false
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);
        System.out.println("Test Case 2: " + solution.isSameTree(p2, q2)); // Expected: false

        // Example 3: p = [1,2,1], q = [1,1,2] → false
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);
        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);
        System.out.println("Test Case 3: " + solution.isSameTree(p3, q3)); // Expected: false

        // Edge Case 4: Both trees are null
        TreeNode p4 = null;
        TreeNode q4 = null;
        System.out.println("Test Case 4: " + solution.isSameTree(p4, q4)); // Expected: true

        // Edge Case 5: One tree is null
        TreeNode p5 = new TreeNode(10);
        TreeNode q5 = null;
        System.out.println("Test Case 5: " + solution.isSameTree(p5, q5)); // Expected: false
    }
}
