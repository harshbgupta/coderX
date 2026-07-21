package com.kritsn.leetcodeKotlin.basic
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////

/**
 *
 * https://youtu.be/RuF7dPfj27Q?feature=shared
 * 📄 Binary Search Tree Operations in Kotlin
 *
 * A Binary Search Tree (BST) is a binary tree in which each node follows the rule:
 * - All values in the left subtree < node’s value
 * - All values in the right subtree > node’s value
 * - No duplicate values
 */

class _000BinarySearchTreeOperations {

    // Definition for a binary tree node
    data class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * ✅ 1. Search in BST
     * Returns true if the key exists in the BST.
     */
    fun search(root: TreeNode?, key: Int): Boolean {
        var curr = root
        while (curr != null) {
            when {
                key == curr.`val` -> return true
                key < curr.`val` -> curr = curr.left
                else -> curr = curr.right
            }
        }
        return false
    }

    /**
     * ✅ 2. Insert into BST
     * Inserts a new value and returns the updated root.
     */
    fun insert(root: TreeNode?, key: Int): TreeNode {
        if (root == null) return TreeNode(key)
        if (key < root.`val`) root.left = insert(root.left, key)
        else root.right = insert(root.right, key)
        return root
    }

    /**
     * ✅ 3. Delete from BST
     * Deletes the node with the given key and returns the updated root.
     */
    fun delete(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null
        when {
            key < root.`val` -> root.left = delete(root.left, key)
            key > root.`val` -> root.right = delete(root.right, key)
            else -> {
                // Case 1 & 2: Node has 0 or 1 child
                if (root.left == null) return root.right
                if (root.right == null) return root.left

                // Case 3: Node has 2 children — find inorder successor
                val minLargerNode = findMin(root.right)
                root.`val` = minLargerNode.`val`
                root.right = delete(root.right, minLargerNode.`val`)
            }
        }
        return root
    }

    /**
     * Helper: Find minimum node in a subtree (leftmost node)
     */
    fun findMin(node: TreeNode?): TreeNode {
        var curr = node
        while (curr?.left != null) curr = curr.left
        return curr!!
    }

    /**
     * ✅ 4. Validate BST
     * Checks if a binary tree is a valid BST using min/max constraints.
     */
    fun isValidBST(root: TreeNode?): Boolean {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun validate(node: TreeNode?, min: Long, max: Long): Boolean {
        if (node == null) return true

        // Current value must be in (min, max) range
        if (node.`val`.toLong() <= min || node.`val`.toLong() >= max) return false

        // Recursively validate subtrees with updated range
        return validate(node.left, min, node.`val`.toLong()) &&
                validate(node.right, node.`val`.toLong(), max)
    }

    /**
     * ✅ 5. Floor in BST
     * Returns the largest value ≤ key, or null if none found.
     */
    fun findFloor(root: TreeNode?, key: Int): Int? {
        var node = root
        var floor: Int? = null
        while (node != null) {
            if (node.`val` == key) return key
            if (node.`val` > key) node = node.left
            else {
                floor = node.`val`
                node = node.right
            }
        }
        return floor
    }

    /**
     * ✅ 6. Ceil in BST
     * Returns the smallest value ≥ key, or null if none found.
     */
    fun findCeil(root: TreeNode?, key: Int): Int? {
        var node = root
        var ceil: Int? = null
        while (node != null) {
            if (node.`val` == key) return key
            if (node.`val` < key) node = node.right
            else {
                ceil = node.`val`
                node = node.left
            }
        }
        return ceil
    }

    /**
     * ✅ Main method to test all BST operations
     */
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val bst = _000BinarySearchTreeOperations()
            var root: TreeNode? = null

            val values = listOf(10, 5, 20, 3, 7, 15)
            for (v in values) {
                root = bst.insert(root, v)
            }

            println("Search 7: ${bst.search(root, 7)}")           // true
            println("Search 100: ${bst.search(root, 100)}")       // false
            println("Floor of 12: ${bst.findFloor(root, 12)}")    // 10
            println("Ceil of 12: ${bst.findCeil(root, 12)}")      // 15

            root = bst.delete(root, 10) // delete root node
            println("Search 10 after delete: ${bst.search(root, 10)}") // false

            println("Is valid BST: ${bst.isValidBST(root)}")      // true
        }
    }
}