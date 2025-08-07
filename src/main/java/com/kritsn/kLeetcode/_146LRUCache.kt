package com.kritsn.kLeetcode


///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 21, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 📄 Problem Statement:
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 *   - LRUCache(int capacity): Initialize the cache with positive size.
 *   - int get(int key): Return the value if key exists, else -1.
 *   - void put(int key, int value): Update or insert the key-value. If capacity is exceeded, evict LRU key.
 * Functions must run in O(1) average time complexity.
 */

class _146LRUCache {

    //https://youtu.be/z9bJUPxzFOw?feature=shared
    class LRUCache(private val capacity: Int) {
        /**
         * Doubly linked list node class to store key-value pair along with prev and next pointers.
         */
        private class Node(val key: Int, var value: Int) {
            var prev: Node? = null
            var next: Node? = null
        }

        private val map = HashMap<Int, Node>() // HashMap for O(1) access
        private val head = Node(0, 0) // Dummy head for most recently used
        private val tail = Node(0, 0) // Dummy tail for least recently used

        init {
            // Initialize the doubly linked list with dummy head and tail connected
            head.next = tail
            tail.prev = head
        }

        /**
         * Get value from cache. If found, move it to head (most recently used).
         */
        fun get(key: Int): Int {
            val node = map[key] ?: return -1
            remove(node)         // Remove from current position
            insertToHead(node)   // Move to head as it's recently used
            return node.value
        }

        /**
         * Put key-value into cache. If key exists, update and move to head.
         * If new, insert and remove tail if over capacity.
         */
        fun put(key: Int, value: Int) {
            if (map.containsKey(key)) {
                val node = map[key]!!
                node.value = value
                remove(node)
                insertToHead(node)
            } else {
                val newNode = Node(key, value)
                map[key] = newNode
                insertToHead(newNode)
                if (map.size > capacity) {
                    // Remove least recently used node (before tail)
                    val lru = tail.prev!!
                    remove(lru)
                    map.remove(lru.key)
                }
            }
        }

        /**
         * Removes a node from the doubly linked list.
         */
        private fun remove(node: Node) {
            val prevNode = node.prev
            val nextNode = node.next
            prevNode?.next = nextNode
            nextNode?.prev = prevNode
        }

        /**
         * Inserts a node right after the dummy head (most recently used position).
         */
        private fun insertToHead(node: Node) {
            val nextNode = head.next
            node.prev = head
            node.next = nextNode
            head.next = node
            nextNode?.prev = node
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lruCache = LRUCache(2)
            lruCache.put(1, 1)                 // cache: {1=1}
            lruCache.put(2, 2)                 // cache: {1=1, 2=2}
            println("Get 1: ${lruCache.get(1)}") // returns 1, cache: {2=2, 1=1}
            lruCache.put(3, 3)                 // evicts key 2, cache: {1=1, 3=3}
            println("Get 2: ${lruCache.get(2)}") // returns -1 (not found)
            lruCache.put(4, 4)                 // evicts key 1, cache: {3=3, 4=4}
            println("Get 1: ${lruCache.get(1)}") // returns -1
            println("Get 3: ${lruCache.get(3)}") // returns 3
            println("Get 4: ${lruCache.get(4)}") // returns 4
        }
    }

}
