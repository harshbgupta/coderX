package com.kritsn.leetCodeJava;

import java.util.*;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 14, 2026
 */
/**
 * Implement the RandomizedSet class:
 *
 * - RandomizedSet() Initializes the RandomizedSet object.
 * - bool insert(int val) Inserts an item val into the set if not present.
 * - bool remove(int val) Removes an item val from the set if present.
 * - int getRandom() Returns a random element from the current set of elements.
 *
 * All operations should work in average O(1) time complexity.
 */
public class _380InsertDeleteGetRandom {

    ///////////////////////////////////////////////////////////////////////////
    // To support O(1) time for all operations, we use:
    // 1. A HashMap to store value -> index mapping.
    // 2. A MutableList to store values for quick random access.
    //
    // - insert(val):
    //     If val not in map:
    //         - Add to end of list.
    //         - Store val -> index in map.
    //
    // - remove(val):
    //     If val in map:
    //         - Swap it with last element in list.
    //         - Remove last element from list.
    //         - Update index of swapped element in map.
    //         - Remove val from map.
    //
    // - getRandom():
    //     - Return a random index from the list.
    ///////////////////////////////////////////////////////////////////////////

    public static class RandomizedSet {
        private final Map<Integer, Integer> valueIndexMap = new HashMap<>(); // val -> index in list
        private final List<Integer> values = new ArrayList<>(); // actual data
        private final Random random = new Random();

        public boolean insert(int val) {
            if (valueIndexMap.containsKey(val)) return false;
            valueIndexMap.put(val, values.size());
            values.add(val);
            return true;
        }

        public boolean remove(int val) {
            Integer indexObj = valueIndexMap.get(val);
            if (indexObj == null) return false;
            int index = indexObj;

            // Swap with last element (if it's not already the last)
            int lastVal = values.get(values.size() - 1);
            values.set(index, lastVal);
            valueIndexMap.put(lastVal, index);

            // Remove last element and the value from the map
            values.remove(values.size() - 1);
            valueIndexMap.remove(val);
            return true;
        }

        public int getRandom() {
            if (values.isEmpty()) throw new NoSuchElementException("RandomizedSet is empty");
            return values.get(random.nextInt(values.size()));
        }


    }

    // Simple demo
    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        System.out.println(set.insert(1)); // true
        System.out.println(set.remove(2)); // false
        System.out.println(set.insert(2)); // true
        System.out.println(set.getRandom()); // 1 or 2
        System.out.println(set.remove(1)); // true
        System.out.println(set.insert(2)); // false
        System.out.println(set.getRandom()); // 2
    }
}
