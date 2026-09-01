package com.kritsn.utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 28, 2026
 */

public class TreePrinter {

    /**
     * FORMAT 1: VERTICAL (Most visual — best for interviews)
     * <p>
     * 3
     * / \
     * 9  20
     * / \
     * 15  7
     */
    public static void printVertical(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        printHelper(root, "", true);
    }

    private static void printHelper(TreeNode node, String indent, boolean last) {
        if (node == null) return;

        System.out.print(indent);
        if (last) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(node.val);

        if (node.left != null || node.right != null) {
            if (node.left != null) {
                printHelper(node.left, indent, node.right == null);
            } else {
                System.out.println(indent + "├─null");
            }

            if (node.right != null) {
                printHelper(node.right, indent, true);
            } else {
                System.out.println(indent + "└─null");
            }
        }
    }

    /**
     * FORMAT 2: LEVEL-ORDER (BFS — clean and compact)
     * <p>
     * Level 0: [3]
     * Level 1: [9, 20]
     * Level 2: [null, null, 15, 7]
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": [");

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    System.out.print("null");
                } else {
                    System.out.print(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }

                if (i < levelSize - 1) System.out.print(", ");
            }

            System.out.println("]");
            level++;
        }
    }

    /**
     * FORMAT 4: SIMPLE INORDER TRAVERSAL (For quick debug)
     * <p>
     * InOrder: 9 3 15 20 7
     */
    public static void printInOrder(TreeNode root) {
        System.out.print("InOrder: ");
        inOrderTraversal(root);
        System.out.println();
    }

    private static void inOrderTraversal(TreeNode node) {
        if (node == null) return;
        inOrderTraversal(node.left);
        System.out.print(node.val + " ");
        inOrderTraversal(node.right);
    }

    /**
     * FORMAT 5: PREORDER TRAVERSAL
     * PreOrder: 3 9 20 15 7
     */
    public static void printPreOrder(TreeNode root) {
        System.out.print("PreOrder: ");
        preOrderTraversal(root);
        System.out.println();
    }

    private static void preOrderTraversal(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /**
     * HELPER: Get tree height
     */
    private static int getHeight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}