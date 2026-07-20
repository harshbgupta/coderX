package com.kritsn.ivs.coupang;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 18, 2025
 */

public class Coupang {

    private static Map<Integer, TreeData> values = new HashMap();

    private static void bottomViewOfTreeWithCoordinate(TreeNode node, int x, int y) {
        if (node == null) {
            return;
        }

        //left
        if (values.isEmpty() || !values.containsKey(x) || y > values.get(x).y) {
            values.put(x, new TreeData(x, y, node.val));
        }
        bottomViewOfTreeWithCoordinate(node.left, x - 1, y + 1);
        bottomViewOfTreeWithCoordinate(node.right, x + 1, y + 1);
    }
}

class TreeData {
    int x;
    int y;
    int value;

    public TreeData(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}