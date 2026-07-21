package com.kritsn.leetCodeJava.medium;

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
 * Leetcode 117 - Populating Next Right Pointers in Each Node II
 * Given a binary tree, connect each node with its next right node.
 * This version works for trees that are not perfect.
 */
public class _116PopulatingNextRightPointersInEachNode {

    // Node definition
    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * 🧠 Algorithm & Approach:
     * - We use level-order traversal (BFS) using a queue.
     * - For each level, we link all nodes using the `next` pointer.
     * - Last node in the level should point to `null`.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N) for queue
     */
    Node connect(Node root) {
        if (root == null) return null;

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node curr = queue.removeFirst();

                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }

            // Last node in the level points to null
            if (prev != null) prev.next = null;
        }

        return root;
    }

    /**
     * Helper function to print the tree level by level including `next` pointers.
     * Prints output in the same format as Leetcode: [[1],[2,3],[4,5,6,7]]
     */
    void printTreeWithNext(Node root) {
        Node levelStart = root;

        List<List<Integer>> result = new ArrayList<>();

        while (levelStart != null) {
            Node current = levelStart;
            List<Integer> level = new ArrayList<>();

            while (current != null) {
                level.add(current.val);
                current = current.next;
            }

            result.add(level);

            levelStart = levelStart.left;
        }

        System.out.println("Output with next pointers: " + result);
    }

    public static void main(String[] args) {
        _116PopulatingNextRightPointersInEachNode solution = new _116PopulatingNextRightPointersInEachNode();

        // Sample Tree:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Node connectedRoot = solution.connect(root);

        // Expected Leetcode format output: [[1],[2,3],[4,5,6,7]]
        solution.printTreeWithNext(connectedRoot);
    }
}
