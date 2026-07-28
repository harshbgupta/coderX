package com.kritsn.ivs.walmart.round1;


import com.kritsn.utils.TreeNode;
import com.kritsn.utils.TreePrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 28, 2026
 */

public class FormTreeFromPreOrderAndsInOrder {

    public static void main(String[] args) {
        int[] inorder = {3,1,4,0,2,5};
        int[] preOrder = {0,1,3,4,2,5};

        TreeNode tree = buildTree(preOrder, inorder);
        TreePrinter.printLevelOrder(tree);
        System.out.println("----------------");
        TreePrinter.printVertical(tree);
        System.out.println("----------------");
        TreePrinter.printInOrder(tree);
        System.out.println("----------------");
        TreePrinter.printPreOrder(tree);

    }

    ///////////////////////////////////////////////////////////////////////////
    // code starts
    ///////////////////////////////////////////////////////////////////////////
    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) return null;

        // HashMap for O(1) lookup of element position in inOrder
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        return build(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1, inOrderMap);
    }

    private static TreeNode build(int[] preOrder, int preStart, int preEnd,
                           int[] inOrder, int inStart, int inEnd,
                           Map<Integer, Integer> inOrderMap) {
        if (preStart > preEnd) return null;

        // First element in preOrder is root
        int rootValue = preOrder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inOrder
        int inRootIdx = inOrderMap.get(rootValue);

        // Elements to left of root in inOrder = left subtree size
        int leftSize = inRootIdx - inStart;

        // Recursively build left subtree
        root.setLeft(build(preOrder, preStart + 1, preStart + leftSize,
                inOrder, inStart, inRootIdx - 1, inOrderMap));

        // Recursively build right subtree
        root.setRight(build(preOrder, preStart + leftSize + 1, preEnd,
                inOrder, inRootIdx + 1, inEnd, inOrderMap));

        return root;
    }

}
