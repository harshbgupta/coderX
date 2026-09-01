package com.kritsn;

import com.kritsn.utils.TreeNode;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 01, 2026
 */

public class X {
    public static void main(String[] args) {
        X x = new X();

        // ===== VALID BINARY SEARCH TREE (BST) =====
        /*
               4
              / \
             2   6
            / \ / \
           1  3 5  1   ← Problem here!

           ❌ INVALID:
           - Node 1 (right child of 6) violates BST property
           - 1 is in RIGHT subtree of 4, but 1 < 4 (should be > 4)
           - Range violation: Node at 4's right should be in range (4, ∞)
       */
        TreeNode validRoot = new TreeNode(4);
        validRoot.left = new TreeNode(2);
        validRoot.right = new TreeNode(6);
        validRoot.left.left = new TreeNode(1);
        validRoot.left.right = new TreeNode(3);
        validRoot.right.left = new TreeNode(5);
        validRoot.right.right = new TreeNode(7);  // Changed from 1 → 7


        // ===== INVALID BINARY SEARCH TREE =====
        /*
               4
              / \
             2   6
            / \ / \
           1  3 5  1   ← Problem here!

           ❌ INVALID:
           - Node 1 (right child of 6) violates BST property
           - 1 is in RIGHT subtree of 4, but 1 < 4 (should be > 4)
           - Range violation: Node at 4's right should be in range (4, ∞)
        */
        TreeNode invalidRoot = new TreeNode(4);
        invalidRoot.left = new TreeNode(2);
        invalidRoot.right = new TreeNode(6);
        invalidRoot.left.left = new TreeNode(1);
        invalidRoot.left.right = new TreeNode(3);
        invalidRoot.right.left = new TreeNode(5);
        invalidRoot.right.right = new TreeNode(1);  // ❌ INVALID: 1 < 6 but NOT < 4

        System.out.println("status: " +( x.isValidBST(validRoot) ? "Valid" : "Invalid"));
        System.out.println("status: " +( x.isValidBST(invalidRoot) ? "Valid" : "Invalid"));
    }

    public boolean isValidBST(TreeNode root) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return isValid(root, min, max);
    }

    public boolean isValid(TreeNode node, int min, int max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
}
