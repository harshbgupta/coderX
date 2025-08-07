package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 22, 2025
///////////////////////////////////////////////////////////////////////////

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

/**
 * 📄 Goal:
 * Build and manipulate a Binary Tree from preorder sequence (-1 indicates null). Implement:
 * - Tree construction
 * - Preorder, Inorder, Postorder, Level Order traversals
 * - Height, Node count, Node sum
 * - Tree diameter (2 methods)
 * - Subtree check
 */
class _000BinaryTreeOperations {

    // 🌲 Node definition
    data class Node(var data: Int, var left: Node? = null, var right: Node? = null)

    // Used to maintain the current index while building tree recursively
    private var index = -1

    /**
     * 🧠 Build tree from preorder sequence where -1 indicates null
     * Time: O(N), Space: O(N) for recursion stack
     */
    fun buildTree(preorder: IntArray): Node? {
        index++
        if (index >= preorder.size || preorder[index] == -1) return null

        val node = Node(preorder[index])
        node.left = buildTree(preorder)
        node.right = buildTree(preorder)
        return node
    }

    // 🧭 Preorder traversal (Root, Left, Right)
    // TC: O(n)
    // SC: O(1)
    fun preorder(root: Node?) {
        if (root == null) {
            print("-1 ")
            return
        }
        print("${root.data} ")
        preorder(root.left)
        preorder(root.right)
    }

    // 🧭 Inorder traversal (Left, Root, Right)
    // TC: O(n)
    // SC: O(1)
    fun inorder(root: Node?) {
        if (root == null) {
            print("-1 ")
            return
        }
        inorder(root.left)
        print("${root.data} ")
        inorder(root.right)
    }

    // 🧭 Postorder traversal (Left, Right, Root)
    // TC: O(n)
    // SC: O(1)
    fun postorder(root: Node?) {
        if (root == null) {
            print("-1 ")
            return
        }
        postorder(root.left)
        postorder(root.right)
        print("${root.data} ")
    }

    // 🧭 Level Order Traversal using Queue and null marker
    // TC: O(n)
    // SC: O(n)
    fun levelOrder(root: Node?) {
        if (root == null) return
        val queue: ArrayDeque<Node?> = ArrayDeque()
        queue.add(root)
        queue.add(null)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current == null) {
                println()
                if (queue.isNotEmpty()) queue.add(null) else break
            } else {
                print("${current.data} ")
                if (current.left != null) queue.add(current.left)
                if (current.right != null) queue.add(current.right)
            }
        }
    }

    /**
     * 🧠 Height of the Tree
     * Time: O(N), Space: O(H)
     */
    fun height(root: Node?): Int {
        if (root == null) return 0
        val leftHeight = height(root.left)
        val rightHeight = height(root.right)
        return maxOf(leftHeight, rightHeight) + 1
    }

    /**
     * 🧠 Count total nodes in the tree
     * Time: O(N), Space: O(H)
     */
    fun countOfNodes(root: Node?): Int {
        if (root == null) return 0
        val leftCount =  countOfNodes(root.left)
        val rightCount = countOfNodes(root.right)
        return leftCount + rightCount + 1
    }

    /**
     * 🧠 Sum of all nodes in the tree
     * Time: O(N), Space: O(H)
     */
    fun sumOfNodes(root: Node?): Int {
        if (root == null) return 0
        val leftSum =  sumOfNodes(root.left)
        val rightSum = sumOfNodes(root.right)
        return leftSum + rightSum + root.data
    }

    /**
     * 🧠 Diameter (Longest Path) - Approach 1 (O(N^2))
     */
    fun diameterSlow(root: Node?): Int {
        if (root == null) return 0

        val diamLeft = diameterSlow(root.left)
        val diamRight = diameterSlow(root.right)
        val height = height(root.left) + height(root.right) + 1

        return maxOf(height, maxOf(diamLeft, diamRight))
    }

    // Helper data class for optimized diameter
    data class TreeInfo(val height: Int, val diameter: Int)

    /**
     * 🧠 Diameter (Longest Path) - Optimized O(N) approach
     */
    fun diameterFast(root: Node?): TreeInfo {
        if (root == null) return TreeInfo(0, 0)

        val left = diameterFast(root.left)
        val right = diameterFast(root.right)

        val height = maxOf(left.height, right.height) + 1
        val rootDiameter = left.height + right.height + 1
        val maxDiameter = maxOf(rootDiameter, maxOf(left.diameter, right.diameter))

        return TreeInfo(height, maxDiameter)
    }

    /**
     * 🧠 Check if one tree is a subtree of another
     */
    fun isSubtree(root: Node?, subRoot: Node?): Boolean {
        if (subRoot == null) return true
        if (root == null) return false
        if (isIdentical(root, subRoot)) return true
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
    }

    private fun isIdentical(n1: Node?, n2: Node?): Boolean {
        if (n1 == null && n2 == null) return true
        if (n1 == null || n2 == null) return false
        if (n1.data != n2.data) return false
        return isIdentical(n1.left, n2.left) && isIdentical(n1.right, n2.right)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val treeOps = _000BinaryTreeOperations()
            val preorder = intArrayOf(1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1)

            val root = treeOps.buildTree(preorder)
            println("✅ Preorder Traversal:")
            treeOps.preorder(root)
            println("\n✅ Inorder Traversal:")
            treeOps.inorder(root)
            println("\n✅ Postorder Traversal:")
            treeOps.postorder(root)
            println("\n✅ Level Order Traversal:")
            treeOps.levelOrder(root)

            println("\n📏 Height of Tree: ${treeOps.height(root)}")
            println("🔢 Total Nodes: ${treeOps.countOfNodes(root)}")
            println("➕ Sum of Nodes: ${treeOps.sumOfNodes(root)}")
            println("📏 Diameter (Slow): ${treeOps.diameterSlow(root)}")
            println("⚡ Diameter (Fast): ${treeOps.diameterFast(root).diameter}")

            // Subtree test
            val subTree = treeOps.buildTree(intArrayOf(2, 4, -1, -1, 5, -1, -1))
            println("🌳 Is Subtree Present? ${treeOps.isSubtree(root, subTree)}")
        }
    }
}
