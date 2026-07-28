package com.kritsn.lld.hashmap;
import java.util.NoSuchElementException;

/**
 * A from-scratch HashMap using separate chaining for collisions and
 * dynamic resizing at a 0.75 load factor — mirroring java.util.HashMap's
 * actual design decisions, single-threaded (no locking; that's a distinct
 * follow-on problem, not part of this core implementation).
 */
class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next; // collision chain

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int size = 0;
    private int capacity;

    @SuppressWarnings("unchecked")
    MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = new Entry[capacity];
    }

    /**
     * Spreads the hash (mixes high bits into the low bits) before masking
     * to the bucket count. Without this, keys whose hashCode() only
     * differs in high bits (a common pattern for poorly-written
     * hashCode() implementations, or sequential Integer keys shifted left)
     * would all collide into the same few low-order buckets.
     */
    private int hash(K key) {
        if (key == null) return 0;
        int h = key.hashCode();
        h ^= (h >>> 16);
        return Math.abs(h) % capacity;
    }

    public void put(K key, V value) {
        int idx = hash(key);
        Entry<K, V> node = buckets[idx];

        while (node != null) {
            if (keysEqual(node.key, key)) {
                node.value = value; // key already exists — update, size unchanged
                return;
            }
            node = node.next;
        }

        // key not found — insert new entry at the head of this bucket's chain
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[idx];
        buckets[idx] = newEntry;
        size++;

        if ((double) size / capacity > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    public V get(K key) {
        int idx = hash(key);
        Entry<K, V> node = buckets[idx];
        while (node != null) {
            if (keysEqual(node.key, key)) return node.value;
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int idx = hash(key);
        Entry<K, V> node = buckets[idx];
        Entry<K, V> prev = null;

        while (node != null) {
            if (keysEqual(node.key, key)) {
                if (prev == null) buckets[idx] = node.next; // removing chain head
                else prev.next = node.next;
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null; // key not found
    }

    public boolean containsKey(K key) {
        int idx = hash(key);
        Entry<K, V> node = buckets[idx];
        while (node != null) {
            if (keysEqual(node.key, key)) return true;
            node = node.next;
        }
        return false;
    }

    public int size() { return size; }

    private boolean keysEqual(K a, K b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }

    /**
     * THE critical operation: doubling capacity is only half the work —
     * every existing entry's bucket index depends on `capacity` (via
     * hash() % capacity), so changing capacity WITHOUT rehashing leaves
     * every entry unreachable in its old, now-incorrect bucket.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        capacity = capacity * 2;
        buckets = new Entry[capacity];
        size = 0; // will be re-incremented as we re-insert via put()

        for (Entry<K, V> head : oldBuckets) {
            Entry<K, V> node = head;
            while (node != null) {
                put(node.key, node.value); // re-hash into the new, bigger bucket array
                node = node.next;
            }
        }
    }
}

public class MyHashMapDemo {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        for (int i = 0; i < 20; i++) { // 20 entries will cross the 0.75 threshold on a 16-bucket map, triggering resize
            map.put("key" + i, i);
        }

        System.out.println("size = " + map.size());
        System.out.println("key5 = " + map.get("key5"));
        System.out.println("key19 = " + map.get("key19"));

        map.remove("key5");
        System.out.println("key5 after remove = " + map.get("key5"));
        System.out.println("containsKey(key5) = " + map.containsKey("key5"));
        System.out.println("final size = " + map.size());
    }
}