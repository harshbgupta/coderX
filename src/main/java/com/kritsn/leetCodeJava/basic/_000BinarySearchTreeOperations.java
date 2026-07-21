package com.kritsn.leetCodeJava.basic;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * https://youtu.be/RuF7dPfj27Q?feature=shared
 * 📄 Binary Search Tree Operations
 *
 * A Binary Search Tree (BST) is a binary tree in which each node follows the rule:
 * - All values in the left subtree < node's value
 * - All values in the right subtree > node's value
 * - No duplicate values
 */
public class _000BinarySearchTreeOperations {

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * ✅ 1. Search in BST
     * Returns true if the key exists in the BST.
     */
    boolean search(TreeNode root, int key) {
        TreeNode curr = root;
        while (curr != null) {
            if (key == curr.val) return true;
            curr = key < curr.val ? curr.left : curr.right;
        }
        return false;
    }

    /**
     * ✅ 2. Insert into BST
     * Inserts a new value and returns the updated root.
     */
    TreeNode insert(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);
        if (key < root.val) root.left = insert(root.left, key);
        else root.right = insert(root.right, key);
        return root;
    }

    /**
     * ✅ 3. Delete from BST
     * Deletes the node with the given key and returns the updated root.
     */
    TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = delete(root.left, key);
        } else if (key > root.val) {
            root.right = delete(root.right, key);
        } else {
            // Case 1 & 2: Node has 0 or 1 child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: Node has 2 children — find inorder successor
            TreeNode minLargerNode = findMin(root.right);
            root.val = minLargerNode.val;
            root.right = delete(root.right, minLargerNode.val);
        }
        return root;
    }

    /**
     * Helper: Find minimum node in a subtree (leftmost node)
     */
    TreeNode findMin(TreeNode node) {
        TreeNode curr = node;
        while (curr.left != null) curr = curr.left;
        return curr;
    }

    /**
     * ✅ 4. Validate BST
     * Checks if a binary tree is a valid BST using min/max constraints.
     */
    boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        // Current value must be in (min, max) range
        if (node.val <= min || node.val >= max) return false;

        // Recursively validate subtrees with updated range
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    /**
     * ✅ 5. Floor in BST
     * Returns the largest value ≤ key, or null if none found.
     */
    Integer findFloor(TreeNode root, int key) {
        TreeNode node = root;
        Integer floor = null;
        while (node != null) {
            if (node.val == key) return key;
            if (node.val > key) {
                node = node.left;
            } else {
                floor = node.val;
                node = node.right;
            }
        }
        return floor;
    }

    /**
     * ✅ 6. Ceil in BST
     * Returns the smallest value ≥ key, or null if none found.
     */
    Integer findCeil(TreeNode root, int key) {
        TreeNode node = root;
        Integer ceil = null;
        while (node != null) {
            if (node.val == key) return key;
            if (node.val < key) {
                node = node.right;
            } else {
                ceil = node.val;
                node = node.left;
            }
        }
        return ceil;
    }

    /**
     * ✅ Main method to test all BST operations
     */
    public static void main(String[] args) {
        _000BinarySearchTreeOperations bst = new _000BinarySearchTreeOperations();
        TreeNode root = null;

        int[] values = {10, 5, 20, 3, 7, 15};
        for (int v : values) {
            root = bst.insert(root, v);
        }

        System.out.println("Search 7: " + bst.search(root, 7));           // true
        System.out.println("Search 100: " + bst.search(root, 100));       // false
        System.out.println("Floor of 12: " + bst.findFloor(root, 12));    // 10
        System.out.println("Ceil of 12: " + bst.findCeil(root, 12));      // 15

        root = bst.delete(root, 10); // delete root node
        System.out.println("Search 10 after delete: " + bst.search(root, 10)); // false

        System.out.println("Is valid BST: " + bst.isValidBST(root));      // true
    }
}
