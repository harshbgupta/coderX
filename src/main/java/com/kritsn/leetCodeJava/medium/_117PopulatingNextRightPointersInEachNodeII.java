package com.kritsn.leetCodeJava.medium;

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
public class _117PopulatingNextRightPointersInEachNodeII {

    // ✅ Definition for a binary tree node with 'next' pointer.
    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 🧠 Algorithm & Approach:
     * - Use level-by-level traversal.
     * - Use a dummy node to build 'next' links for each level.
     * - Traverse current level using existing .next pointers.
     * - Constant space: no extra queue or list used.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1) (excluding output tree)
     */
    Node connect(Node root) {
        Node curr = root; // Start from root node

        while (curr != null) {
            Node dummy = new Node(0); // Dummy node to build next level
            Node tail = dummy;         // Tail pointer to connect children

            while (curr != null) {
                // If left child exists, attach it to the tail
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }

                // If right child exists, attach it as next
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }

                // Move to next node in the current level
                curr = curr.next;
            }

            // Move to the first node of the next level
            curr = dummy.next;
        }

        return root;
    }

    public static void main(String[] args) {
        _117PopulatingNextRightPointersInEachNodeII solver = new _117PopulatingNextRightPointersInEachNodeII();

        // Build tree:
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   7
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5)),
                new Node(3, null, new Node(7))
        );

        Node result = solver.connect(root);
        System.out.print(root.val + ", #, ");
        System.out.println("Next pointer of root.left (2): " + result.left.next.val + " (Expected: 3)");
        System.out.println("Next pointer of root.left.left (4): " + result.left.left.next.val + " (Expected: 5)");
        System.out.println("Next pointer of root.left.right (5): " + result.left.right.next.val + " (Expected: 7)");
    }
}
