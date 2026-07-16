package com.kritsn.z;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 07, 2026
 */

public class Practice {
    public static void main(String[] args) {



//        int[] arr = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
//        Node bTree = Tree.buildTree(arr);
//        System.out.println(bTree.data);
//        System.out.println();
//
//        Tree.preOrder(bTree);
//        System.out.println();
//
//        Tree.inOrder(bTree);
//        System.out.println();
//
//        Tree.postOrder(bTree);
//        System.out.println();
//
//        Tree.levelOrder(bTree);
//        System.out.println();
//
//        System.out.printf("Count of nodes: %d", Tree.countNodes(bTree));
//        System.out.println();
//
//        System.out.printf("Sum of nodes: %d", Tree.sumOfNodes(bTree));
//        System.out.println();
//
//        System.out.printf("Height of Tree: %d", Tree.heightOfTree(bTree));
//        System.out.println();
//
//
//        System.out.printf("diam of Tree: %d", Tree.diameter(bTree));
//        System.out.println();
//
//        System.out.printf("diam of Tree: %d", Tree.diameterFast(bTree).diam);
//        System.out.println();
    }

    static class Node {
        int data;
        Node right;
        Node left;

        Node(int data) {
            this.data = data;
        }
    }

    static class Tree {
        static int idx = -1;

        public static Node buildTree(int[] arr) {
            idx++;
            if (arr[idx] == -1) return null;
            Node root = new Node(arr[idx]);
            root.left = buildTree(arr);
            root.right = buildTree(arr);
            return root;
        }

        public static void preOrder(Node root) {
            if (root == null) {
                System.out.print("-1 ");
                return;
            }

            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }


        public static void inOrder(Node root) {
            if (root == null) {
                System.out.print("-1 ");
                return;
            }
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public static void postOrder(Node root) {
            if (root == null) {
                System.out.print("-1 ");
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }


        public static void levelOrder(Node root) {
            if (root == null) return;
            Queue<Node> q = new LinkedList<Node>();
            q.add(root);
            q.add(null);
            while (!q.isEmpty()) {
                Node node = q.poll();
                if (node == null) {
                    if (!q.isEmpty()) {
                        q.add(null);
                    } else {
                        break;
                    }
                    System.out.println();
                } else {
                    System.out.print(node.data + " ");
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
            }
        }

        public static int countNodes(Node root) {
            if (root == null) return 0;

            int left = countNodes(root.left);
            int right = countNodes(root.right);
            return left + right + 1;
        }

        public static int sumOfNodes(Node root) {
            if (root == null) return 0;

            int left = sumOfNodes(root.left);
            int right = sumOfNodes(root.right);
            return left + right + root.data;
        }

        public static int heightOfTree(Node root) {
            if (root == null) return 0;

            int left = heightOfTree(root.left);
            int right = heightOfTree(root.right);
            return Math.max(left, right) + 1;
        }

        public static int diameter(Node root) {
            if (root == null) return 0;

            int diam1 = diameter(root.left);
            int diam2 = diameter(root.right);
            int diam3 = heightOfTree(root.left) + heightOfTree(root.right) + 1;
            return Math.max(diam1, Math.max(diam2, diam3));
        }

        public static DiamData diameterFast(Node root) {
            if (root == null) return new DiamData(0, 0);

            DiamData diamLeft = diameterFast(root.left);
            DiamData diamRight = diameterFast(root.right);

            int height = Math.max(diamLeft.height, diamRight.height) + 1;

            int diam1 = diamLeft.diam;
            int diam2 = diamRight.diam;
            int diam3 = diamLeft.height + diamRight.height + 1;
            int diam = Math.max(diam1, Math.max(diam2, diam3));
            return new DiamData(diam, height);
        }

        static class DiamData {
            int diam;
            int height;

            DiamData(int diam, int height) {
                this.diam = diam;
                this.height = height;
            }
        }

    }
}

