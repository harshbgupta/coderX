package com.kritsn.leetCodeJava.basic;


import java.util.*;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 24, 2026
 */

public class _0004ViewOfBTree {

    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = q.poll();
                if (i == 0) {
                    result.add(currentNode.data);
                }
                if (currentNode.left != null) {
                    q.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    q.offer(currentNode.right);
                }
            }
        }
        return result;
    }

    public static List<Integer> rightView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (i == size - 1)
                    result.add(curr.data);

                if (curr.left != null)
                    q.offer(curr.left);

                if (curr.right != null)
                    q.offer(curr.right);

            }
        }
        return result;
    }

    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node.data);
            }
            if (curr.node.left != null) {
                q.offer(new Pair(curr.node.left, curr.hd - 1));
            }
            if (curr.node.right != null) {
                q.offer(new Pair(curr.node.right, curr.hd + 1));
            }
        }
        result.addAll(map.values());
        return result;
    }

    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            map.put(curr.hd, curr.node.data);
            if (curr.node.left != null) {
                q.offer(new Pair(curr.node.left, curr.hd - 1));
            }
            if (curr.node.right != null) {
                q.offer(new Pair(curr.node.right, curr.hd + 1));
            }
        }
        result.addAll(map.values());
        return result;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    static class Pair {
        TreeNode node;
        int hd; //horizontal Distance

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }

    }

    public static void main(String[] args) {
        /*
              1
            /   \
           2     3
          / \   / \
         4   5 6   7
              \
               8
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(8);

        _0004ViewOfBTree demo = new _0004ViewOfBTree();
        System.out.println("============== LEFT VIEW ==============");
        System.out.println("Expected : [1, 2, 4, 8]");
        System.out.println("Actual   : " + demo.leftView(root));
        System.out.println();

        System.out.println("============== RIGHT VIEW ==============");
        System.out.println("Expected : [1, 3, 7, 8]");
        System.out.println("Actual   : " + demo.rightView(root));
        System.out.println();

        System.out.println("============== TOP VIEW ==============");
        System.out.println("Expected : [4, 2, 1, 3, 7]");
        System.out.println("Actual   : " + demo.topView(root));
        System.out.println();

        System.out.println("============== BOTTOM VIEW ==============");
        System.out.println("Expected : [4, 2, 6, 8, 7]");
        System.out.println("Actual   : " + demo.bottomView(root));
    }
}
