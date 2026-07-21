package com.kritsn.leetCodeJava.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
138. Copy List with Random Pointer

You are given a linked list of length n such that each node contains an additional random pointer,
which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list
such that the pointers in the original list and copied list represent the same list state.

Return the head of the copied linked list.
*/
public class _138CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
        }
    }

    /*
    https://youtu.be/8ze7Zopdsaw?feature=shared
    🧠 Algorithm & Approach:
    Step 1: Clone nodes in-between original list nodes.
    Step 2: Set the random pointer of copied nodes.
    Step 3: Separate the interleaved list into original and copied lists.

    Time Complexity: O(n)
    Space Complexity: O(1) (no hashmap used, purely in-place)
    */
    Node copyRandomList(Node node) {
        if (node == null) return null;

        Node curr = node;

        // Step 1: Clone each node and insert the copy right after the original node
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers to the copied nodes
        curr = node;
        while (curr != null) {
            if (curr.random != null) curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        // Step 3: Separate original and copied nodes
        curr = node;
        Node pseudoHead = new Node(0);
        Node copyCurr = pseudoHead;

        while (curr != null) {
            Node copy = curr.next;
            Node nextOrig = copy.next;

            copyCurr.next = copy;
            copyCurr = copy;

            curr.next = nextOrig; // restore original list
            curr = nextOrig;
        }

        return pseudoHead.next;
    }

    /*
    https://youtu.be/8ze7Zopdsaw?feature=shared
    🧠 Algorithm & Approach:
    Step 1: Clone nodes in-between original list nodes.
    Step 2: save in map for future use
    Step 3: now again transeverse to old node, take the new value from map

    Time Complexity: O(n)
    Space Complexity: O(n) (used hashmap used, no purely in-place)
    */
    Node copyRandomListNoOptimised(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> originalToCopy = new HashMap<>();

        Node currentNode = node;
        Node prevNode = null;
        while (currentNode != null) {
            Node newNode = new Node(currentNode.val);
            if (currentNode.random == null) {
                newNode.random = null;
            }
            if (prevNode != null) prevNode.next = newNode;
            originalToCopy.put(currentNode, newNode);

            prevNode = newNode;
            currentNode = currentNode.next;
        }

        currentNode = node;

        while (currentNode != null) {
            if (currentNode.random != null) {
                Node newNode = originalToCopy.get(currentNode);
                Node randomNode = originalToCopy.get(currentNode.random);
                newNode.random = randomNode;
            }
            currentNode = currentNode.next;
        }

        return originalToCopy.get(node);
    }

    public static void main(String[] args) {
        // Create sample linked list with random pointers
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        _138CopyListWithRandomPointer solution = new _138CopyListWithRandomPointer();
        Node copiedHead = solution.copyRandomList(node1);

        // Print copied list to verify
        Node curr = copiedHead;
        System.out.println("Copied list: [val, random_val]");
        while (curr != null) {
            String randomVal = curr.random != null ? String.valueOf(curr.random.val) : "null";
            System.out.println("[" + curr.val + ", " + randomVal + "]");
            curr = curr.next;
        }
    }
}
