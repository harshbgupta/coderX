package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _222CountCompleteTreeNodes {
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
     * https://youtu.be/d4zLyf32e3I?feature=shared
     * <p>
     * 🧠 Optimized Recursive Approach (O(log^2 N)):
     * - For each subtree, compare the depth of left-most and right-most paths.
     * - If equal → it's a perfect binary subtree ⇒ return 2^depth - 1 nodes.
     * - If not equal → recursively count left and right subtree sizes.
     * <p>
     * Time: O(log^2 N), Space: O(log N) for recursion
     */
    int countNodes(TreeNode root) {
        if (root == null) return 0;

        int lh = leftHeight(root);
        int rh = rightHeight(root);

        if (lh == rh) {
            // Tree is perfect ⇒ total nodes = 2^height - 1
            return (1 << lh) - 1;
        } else {
            // Otherwise, recursively count left and right subtrees
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    // Get left-most height
    private int leftHeight(TreeNode node) {
        int h = 0;
        TreeNode curr = node;
        while (curr != null) {
            h++;
            curr = curr.left;
        }
        return h;
    }

    // Get right-most height
    private int rightHeight(TreeNode node) {
        int h = 0;
        TreeNode curr = node;
        while (curr != null) {
            h++;
            curr = curr.right;
        }
        return h;
    }

    /**
     * Simply count the nodes
     */
    int countNodesOther(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return 1 + leftCount + rightCount;
    }

    public static void main(String[] args) {
        _222CountCompleteTreeNodes tree = new _222CountCompleteTreeNodes();

        // 🧪 Test Case 1: Complete tree [1,2,3,4,5,6]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        System.out.println("Test Case 1: Expected = 6, Got = " + tree.countNodes(root1));

        // 🧪 Test Case 2: Single node
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test Case 2: Expected = 1, Got = " + tree.countNodes(root2));

        // 🧪 Test Case 3: Null tree
        System.out.println("Test Case 3: Expected = 0, Got = " + tree.countNodes(null));

        // 🧪 Test Case 4: Full tree of height 3
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        root4.right = new TreeNode(3);
        root4.right.left = new TreeNode(6);
        root4.right.right = new TreeNode(7);
        System.out.println("Test Case 4: Expected = 7, Got = " + tree.countNodes(root4));
    }
}
