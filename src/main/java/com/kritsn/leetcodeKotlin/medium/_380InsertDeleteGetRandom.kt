package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 12, 2025
///////////////////////////////////////////////////////////////////////////

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
class _380InsertDeleteGetRandom {

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
    class RandomizedSet {
        private val valueIndexMap = mutableMapOf<Int, Int>() // val -> index in list
        private val values = mutableListOf<Int>() // actual data

        fun insert(`val`: Int): Boolean {
            if (`val` in valueIndexMap) return false

            // Add value to the list and store its index in the map
            valueIndexMap[`val`] = values.size
            values.add(`val`)
            return true
        }

        fun remove(`val`: Int): Boolean {
            val index = valueIndexMap[`val`] ?: return false

            // Swap with last element
            val lastVal = values.last()
            values[index] = lastVal
            valueIndexMap[lastVal] = index

            // Remove the last element and the value from the map
            values.removeAt(values.size - 1)
            valueIndexMap.remove(`val`)
            return true
        }

        fun getRandom(): Int {
            return values.random() // Kotlin built-in random
        }
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val set = _380InsertDeleteGetRandom.RandomizedSet()

    println("Insert 1: ${set.insert(1)}") // Expected: true
    println("Insert 2: ${set.insert(2)}") // Expected: true
    println("Insert 1 again: ${set.insert(1)}") // Expected: false
    println("Remove 1: ${set.remove(1)}") // Expected: true
    println("Remove 3 (not present): ${set.remove(3)}") // Expected: false
    println("Insert 2 again: ${set.insert(2)}") // Expected: false

    // Try getRandom multiple times
    repeat(5) {
        println("Random element #$it: ${set.getRandom()}") // Expected: random among current elements (only 2)
    }
}
