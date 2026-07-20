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
Leetcode 173: Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST).
It should return the nodes in ascending (in-order) order one at a time.
*/
public class _173BSTIterator {

    // ✅ Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Iterator {
        // Stack to simulate in-order traversal
        private final Deque<TreeNode> stack = new ArrayDeque<>();

        Iterator(TreeNode root) {
            // Initially push all the way left from the root
            pushLeftBranch(root);
        }

        /**
         * Push all left children from the current node to the stack
         * This simulates going to the next smallest element in BST
         */
        private void pushLeftBranch(TreeNode node) {
            TreeNode current = node;
            while (current != null) {
                stack.addLast(current);
                current = current.left;
            }
        }

        /** @return the next smallest number */
        int next() {
            // Pop the next node in in-order sequence
            TreeNode node = stack.removeLast();

            // If the node has a right child, push its left subtree
            if (node.right != null) {
                pushLeftBranch(node.right);
            }

            return node.val;
        }

        /** @return whether we have a next smallest number */
        boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        // Create BST: [7, 3, 15, null, null, 9, 20]
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        Iterator iterator = new Iterator(root);

        // 🧪 Expected Output: 3, 7, 9, 15, 20
        List<Integer> results = new ArrayList<>();
        while (iterator.hasNext()) {
            results.add(iterator.next());
        }

        System.out.println("In-order Traversal: " + results);
    }
}
