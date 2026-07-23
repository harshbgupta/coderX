package com.kritsn.scaler.ques.hashmap;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

public class MyCustomHash<K, V> {

    //class represents key value pair
    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private static int DEFAULT_SIZE = 16;

    public MyCustomHash() {
        buckets = new LinkedList[DEFAULT_SIZE];
    }

    /**
     * Hash function to calculate index in the array for a given key.
     */
    private int getIndexKey(K key) {
        return Math.abs(Objects.hashCode(key) % buckets.length);
    }

    /**
     * Inserts a key-value pair into the hash table.
     */
    public void put(K key, V value) {
        int index = getIndexKey(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;  // Update existing key
                return;
            }
        }

        // Key not found, insert new entry
        buckets[index].add(new Entry<>(key, value));
    }

    /**
     * Retrieves the value associated with the given key.
     */
    public V get(K key) {
        int index = getIndexKey(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    /**
     * Removes the key-value pair for the given key.
     */
    public void remove(K key) {
        int index = getIndexKey(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        if (bucket != null) {
            bucket.removeIf(entry -> entry.key.equals(key));
        }
    }

    /**
     * Main method to test the custom HashMap implementation.
     */
    public static void main(String[] args) {
        MyCustomHash<CustomKey, Integer> map = new MyCustomHash<>();

        CustomKey k1 = new CustomKey("apple");
        CustomKey k2 = new CustomKey("banana");
        CustomKey k3 = new CustomKey("apple"); // same key as k1 logically

        map.put(k1, 10);
        map.put(k2, 20);

        System.out.println("Value for apple: " + map.get(k3)); // Should return 10

        map.remove(k1);
        System.out.println("After removing 'apple', value: " + map.get(k3)); // Should return null
    }
}

class CustomKey {
    String name;

    public CustomKey(String name) {
        this.name = name;
    }

    // ✅ Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomKey)) return false;
        CustomKey that = (CustomKey) o;
        return Objects.equals(this.name, that.name);
    }

    // ✅ Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
