package com.kritsn.ques.hashmap;

import java.util.LinkedList;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 13, 2025
 */

public class MyHashMapSimpler<K, V> {

    // Inner class to store key-value pairs
    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 16; // Default bucket size
    private LinkedList<Entry<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashMapSimpler() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // Simple hashing function
    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    // Add or update key-value
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update existing key
                return;
            }
        }
        buckets[index].add(new Entry<>(key, value)); // Add new key
    }

    // Get value by key
    public V get(K key) {
        int index = getBucketIndex(key);
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Not found
    }


    public class Main {
        public static void main(String[] args) {
            MyHashMap<String, Integer> map = new MyHashMap<>();
            map.put("Apple", 3);
            map.put("Banana", 5);
            map.put("Orange", 2);

            System.out.println("Apple count: " + map.get("Apple"));
            System.out.println("Banana count: " + map.get("Banana"));
            System.out.println("Orange count: " + map.get("Orange"));
        }
    }
}