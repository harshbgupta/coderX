package com.kritsn.ivs.kaleidofin;

import com.kritsn.utils.TreeNode;
import com.kritsn.utils.TreePrinter;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 17, 2026
 */


/*

Flatten a given binary tree in preorder traversal.
For Eg:
 input:
        1
      /    \
      2    5
    /  \    \
    3  4      6

o/p tree:
   1
   |
   2
   |
   3
   |
   4
   |
   5
   |
   6
// you can keep left (or right) as null for all nodes (not mentioned null in output, if want you can have)
 */

public class KaleidoFinRound1 {

    public static void main(String[] args) {
        //testcase1
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(6);

        //testcase1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(4);

        TreePrinter.printLevelOrder(root);
        flatten(root);
    }

    /**
     * APPROACH: Morris Traversal - modify tree in-place using tree pointers
     *
     * STEPS:
     * 1. Find rightmost of left subtree - Why: Need to connect right subtree here
     * 2. Connect right subtree to rightmost - Why: Preserve right subtree in chain
     * 3. Move left subtree to right - Why: Flatten left before continuing
     * 4. Clear left pointer - Why: Only right pointers form linked list
     * 5. Move to next node - Why: Process all nodes
     *
     * TIME: O(N) | SPACE: O(1)
     */
    public static void flatten(TreeNode root) {

        // Step 1: Traverse tree using right pointers
        // Why: We're building flattened structure as we go
        TreeNode curr = root;

        while (curr != null) {

            // Step 2: Check if left subtree exists
            // Why: Only process if there's something to flatten
            if (curr.left != null) {

                // Step 3: Find rightmost node in left subtree
                // Why: This is where we'll attach the right subtree
                TreeNode rightmost = curr.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;  // Keep going right
                }

                // Step 4: Connect right subtree to rightmost node
                // Why: Preserve the right subtree in flattened order
                rightmost.right = curr.right;

                // Step 5: Move left subtree to right position
                // Why: Flatten hierarchy - left becomes next in chain
                curr.right = curr.left;

                // Step 6: Clear left pointer
                // Why: Linked list only uses right pointers
                curr.left = null;
            }

            // Step 7: Move to next node in flattened structure
            // Why: Continue flattening remaining nodes
            curr = curr.right;
        }
    }

}
