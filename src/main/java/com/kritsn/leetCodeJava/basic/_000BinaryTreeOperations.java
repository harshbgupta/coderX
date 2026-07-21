package com.kritsn.leetCodeJava.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * https://youtu.be/-DzowlcaUmE?feature=shared
 * 📄 Goal:
 * Build and manipulate a Binary Tree from preorder sequence (-1 indicates null). Implement:
 * - Tree construction
 * - Preorder, Inorder, Postorder, Level Order traversals
 * - Height, Node count, Node sum
 * - Tree diameter (2 methods)
 * - Subtree check
 */
public class _000BinaryTreeOperations {

    // 🌲 Node definition
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    // Used to maintain the current index while building tree recursively
    private int index = -1;

    /**
     * 🧠 Build tree from preorder sequence where -1 indicates null
     * Time: O(N), Space: O(N) for recursion stack
     */
    Node buildTree(int[] preorder) {
        index++;
        if (index >= preorder.length || preorder[index] == -1) return null;

        Node node = new Node(preorder[index]);
        node.left = buildTree(preorder);
        node.right = buildTree(preorder);
        return node;
    }

    // 🧭 Preorder traversal (Root, Left, Right)
    // TC: O(n)
    // SC: O(1)
    void preorder(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // 🧭 Inorder traversal (Left, Root, Right)
    // TC: O(n)
    // SC: O(1)
    void inorder(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🧭 Postorder traversal (Left, Right, Root)
    // TC: O(n)
    // SC: O(1)
    void postorder(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // 🧭 Level Order Traversal using Queue and null marker
    // TC: O(n)
    // SC: O(n)
    void levelOrder(Node root) {
        if (root == null) return;
        // java.util.ArrayDeque disallows null elements (unlike Kotlin's ArrayDeque),
        // so we use a dedicated sentinel node to mark level boundaries instead.
        Node levelMarker = new Node(Integer.MIN_VALUE);
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        queue.add(levelMarker);

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            if (current == levelMarker) {
                System.out.println();
                if (!queue.isEmpty()) queue.add(levelMarker);
                else break;
            } else {
                System.out.print(current.data + " ");
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
    }

    /**
     * 🧠 Height of the Tree
     * Time: O(N), Space: O(H)
     */
    int height(Node root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 🧠 Count total nodes in the tree
     * Time: O(N), Space: O(H)
     */
    int countOfNodes(Node root) {
        if (root == null) return 0;
        int leftCount = countOfNodes(root.left);
        int rightCount = countOfNodes(root.right);
        return leftCount + rightCount + 1;
    }

    /**
     * 🧠 Sum of all nodes in the tree
     * Time: O(N), Space: O(H)
     */
    int sumOfNodes(Node root) {
        if (root == null) return 0;
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    /**
     * 🧠 Diameter (Longest Path) - Approach 1 (O(N^2))
     */
    int diameterSlow(Node root) {
        if (root == null) return 0;

        int diamLeft = diameterSlow(root.left);
        int diamRight = diameterSlow(root.right);
        int height = height(root.left) + height(root.right) + 1;

        return Math.max(height, Math.max(diamLeft, diamRight));
    }

    // Helper record for optimized diameter
    record TreeInfo(int height, int diameter) {
    }

    /**
     * 🧠 Diameter (Longest Path) - Optimized O(N) approach
     */
    TreeInfo diameterFast(Node root) {
        if (root == null) return new TreeInfo(0, 0);

        TreeInfo left = diameterFast(root.left);
        TreeInfo right = diameterFast(root.right);

        int height = Math.max(left.height(), right.height()) + 1;
        int rootDiameter = left.height() + right.height() + 1;
        int maxDiameter = Math.max(rootDiameter, Math.max(left.diameter(), right.diameter()));

        return new TreeInfo(height, maxDiameter);
    }

    /**
     * 🧠 Check if one tree is a subtree of another
     */
    boolean isSubtree(Node root, Node subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        if (isIdentical(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isIdentical(Node n1, Node n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        if (n1.data != n2.data) return false;
        return isIdentical(n1.left, n2.left) && isIdentical(n1.right, n2.right);
    }

    public static void main(String[] args) {
        _000BinaryTreeOperations treeOps = new _000BinaryTreeOperations();
        int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        Node root = treeOps.buildTree(preorder);
        System.out.println("✅ Preorder Traversal:");
        treeOps.preorder(root);
        System.out.println("\n✅ Inorder Traversal:");
        treeOps.inorder(root);
        System.out.println("\n✅ Postorder Traversal:");
        treeOps.postorder(root);
        System.out.println("\n✅ Level Order Traversal:");
        treeOps.levelOrder(root);

        System.out.println("\n📏 Height of Tree: " + treeOps.height(root));
        System.out.println("🔢 Total Nodes: " + treeOps.countOfNodes(root));
        System.out.println("➕ Sum of Nodes: " + treeOps.sumOfNodes(root));
        System.out.println("📏 Diameter (Slow): " + treeOps.diameterSlow(root));
        System.out.println("⚡ Diameter (Fast): " + treeOps.diameterFast(root).diameter());

        // Subtree test
        Node subTree = treeOps.buildTree(new int[]{2, 4, -1, -1, 5, -1, -1});
        System.out.println("🌳 Is Subtree Present? " + treeOps.isSubtree(root, subTree));
    }
}
