package com.kritsn.lld.hashmap;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A hand-rolled thread-safe map using per-bucket locking — the same
 * fundamental idea Java's real java.util.concurrent.ConcurrentHashMap uses
 * (segment/bin-level locking rather than one lock for the whole map).
 *
 * Design decision stated up front: bucket count is FIXED at construction.
 * Supporting resize is a legitimate, common follow-up (see notes below) but
 * deliberately out of scope for the base implementation, to keep the core
 * locking logic clear first.
 */
class MyConcurrentHashMap<K, V> {
    private static final int DEFAULT_BUCKET_COUNT = 16;

    private static class Node<K, V> {
        final K key;
        volatile V value; // volatile: a get() on another thread must see the latest write
        volatile Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Node<K, V>[] buckets;
    private final ReentrantReadWriteLock[] locks;

    @SuppressWarnings("unchecked")
    MyConcurrentHashMap(int bucketCount) {
        buckets = (Node<K, V>[]) new Node[bucketCount];
        locks = new ReentrantReadWriteLock[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    MyConcurrentHashMap() {
        this(DEFAULT_BUCKET_COUNT);
    }

    private int bucketIndex(K key) {
        // spread the hash a bit (like real HashMap does) to reduce clustering
        int h = key.hashCode();
        h ^= (h >>> 16);
        return Math.abs(h) % buckets.length;
    }

    public V get(K key) {
        int idx = bucketIndex(key);
        locks[idx].readLock().lock(); // multiple readers on the SAME bucket proceed together
        try {
            Node<K, V> node = buckets[idx];
            while (node != null) {
                if (node.key.equals(key)) return node.value;
                node = node.next;
            }
            return null;
        } finally {
            locks[idx].readLock().unlock();
        }
    }

    public void put(K key, V value) {
        int idx = bucketIndex(key);
        locks[idx].writeLock().lock(); // exclusive — blocks reads/writes on THIS bucket only
        try {
            Node<K, V> node = buckets[idx];
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value; // update in place
                    return;
                }
                node = node.next;
            }
            // insert at head — O(1), no need to walk to the end
            Node<K, V> newNode = new Node<>(key, value);
            newNode.next = buckets[idx];
            buckets[idx] = newNode;
        } finally {
            locks[idx].writeLock().unlock();
        }
    }

    public void remove(K key) {
        int idx = bucketIndex(key);
        locks[idx].writeLock().lock();
        try {
            Node<K, V> node = buckets[idx];
            Node<K, V> prev = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    if (prev == null) buckets[idx] = node.next; // removing the head
                    else prev.next = node.next;
                    return;
                }
                prev = node;
                node = node.next;
            }
        } finally {
            locks[idx].writeLock().unlock();
        }
    }

    /** Approximate size — deliberately NOT lock-free-safe across ALL buckets
     *  simultaneously; a truly consistent size() would need to lock every
     *  bucket at once, defeating the whole point of per-bucket locking. This
     *  tradeoff is exactly what real ConcurrentHashMap accepts too. */
    public int approximateSize() {
        int count = 0;
        for (int i = 0; i < buckets.length; i++) {
            locks[i].readLock().lock();
            try {
                Node<K, V> node = buckets[i];
                while (node != null) { count++; node = node.next; }
            } finally {
                locks[i].readLock().unlock();
            }
        }
        return count;
    }
}

public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        MyConcurrentHashMap<String, Integer> map = new MyConcurrentHashMap<>();

        Runnable writer1 = () -> { for (int i = 0; i < 1000; i++) map.put("key" + i, i); };
        Runnable writer2 = () -> { for (int i = 1000; i < 2000; i++) map.put("key" + i, i); };

        Thread t1 = new Thread(writer1);
        Thread t2 = new Thread(writer2);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("key500 = " + map.get("key500"));
        System.out.println("key1500 = " + map.get("key1500"));
        System.out.println("approx size = " + map.approximateSize());
    }
}