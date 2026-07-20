package com.kritsn.leetCodeJava;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 📄 Problem Statement:
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * - LRUCache(int capacity): Initialize the cache with positive size.
 * - int get(int key): Return the value if key exists, else -1.
 * - void put(int key, int value): Update or insert the key-value. If capacity is exceeded, evict LRU key.
 * Functions must run in O(1) average time complexity.
 */
public class _146LRUCache {

    //https://youtu.be/z9bJUPxzFOw?feature=shared
    static class LRUCache {
        private final int capacity;

        /**
         * Doubly linked list node class to store key-value pair along with prev and next pointers.
         */
        private static class Node {
            final int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final Map<Integer, Node> map = new HashMap<>(); // HashMap for O(1) access
        private final Node head = new Node(0, 0); // Dummy head for most recently used
        private final Node tail = new Node(0, 0); // Dummy tail for least recently used

        LRUCache(int capacity) {
            this.capacity = capacity;
            // Initialize the doubly linked list with dummy head and tail connected
            head.next = tail;
            tail.prev = head;
        }

        /**
         * Get value from cache. If found, move it to head (most recently used).
         */
        int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            remove(node);         // Remove from current position
            insertToHead(node);   // Move to head as it's recently used
            return node.value;
        }

        /**
         * Put key-value into cache. If key exists, update and move to head.
         * If new, insert and remove tail if over capacity.
         */
        void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                remove(node);
                insertToHead(node);
            } else {
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                insertToHead(newNode);
                if (map.size() > capacity) {
                    // Remove least recently used node (before tail)
                    Node lru = tail.prev;
                    remove(lru);
                    map.remove(lru.key);
                }
            }
        }

        /**
         * Removes a node from the doubly linked list.
         */
        private void remove(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        /**
         * Inserts a node right after the dummy head (most recently used position).
         */
        private void insertToHead(Node node) {
            Node nextNode = head.next;
            node.prev = head;
            node.next = nextNode;
            head.next = node;
            nextNode.prev = node;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);                 // cache: {1=1}
        lruCache.put(2, 2);                 // cache: {1=1, 2=2}
        System.out.println("Get 1: " + lruCache.get(1)); // returns 1, cache: {2=2, 1=1}
        lruCache.put(3, 3);                 // evicts key 2, cache: {1=1, 3=3}
        System.out.println("Get 2: " + lruCache.get(2)); // returns -1 (not found)
        lruCache.put(4, 4);                 // evicts key 1, cache: {3=3, 4=4}
        System.out.println("Get 1: " + lruCache.get(1)); // returns -1
        System.out.println("Get 3: " + lruCache.get(3)); // returns 3
        System.out.println("Get 4: " + lruCache.get(4)); // returns 4
    }
}
