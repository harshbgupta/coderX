package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 19, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 */

class _155MinStack {

    /**
     * 🧠 Algorithm & Approach:
     * We use two stacks:
     * 1. mainStack - stores all pushed values.
     * 2. minStack - stores the minimum value at the time each element is pushed.
     *
     * For every push, we also push the current minimum to minStack.
     * For every pop, we pop both stacks to keep them in sync.
     * This way, getMin() is always available at the top of minStack in O(1).
     *
     * Time Complexity for all operations: O(1)
     * Space Complexity: O(n)
     */
    class MinStack {
        private val mainStack = ArrayDeque<Int>()
        private val minStack = ArrayDeque<Int>()

        fun push(`val`: Int) {
            mainStack.addLast(`val`)
            val min = if (minStack.isEmpty()) `val` else kotlin.math.min(`val`, minStack.last())
            minStack.addLast(min)
        }

        fun pop() {
            if (mainStack.isNotEmpty()) {
                mainStack.removeLast()
                minStack.removeLast()
            }
        }

        fun top(): Int {
            return mainStack.last()
        }

        fun getMin(): Int {
            return minStack.last()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val minStack = MinStack()

            println("Pushing values: 2, 0, 3, 0")
            minStack.push(2)
            minStack.push(0)
            minStack.push(3)
            minStack.push(0)

            println("Current Min: ${minStack.getMin()}") // Expected: 0
            minStack.pop()
            println("After one pop, Min: ${minStack.getMin()}") // Expected: 0
            minStack.pop()
            println("After another pop, Min: ${minStack.getMin()}") // Expected: 0
            minStack.pop()
            println("After another pop, Min: ${minStack.getMin()}") // Expected: 2

            println("Top element: ${minStack.top()}") // Expected: 2
        }
    }
}
