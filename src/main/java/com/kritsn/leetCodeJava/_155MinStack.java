package com.kritsn.leetCodeJava;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 */
public class _155MinStack {

    /**
     * 🧠 Algorithm & Approach:
     * We use two stacks:
     * 1. mainStack - stores all pushed values.
     * 2. minStack - stores the minimum value at the time each element is pushed.
     * <p>
     * For every push, we also push the current minimum to minStack.
     * For every pop, we pop both stacks to keep them in sync.
     * This way, getMin() is always available at the top of minStack in O(1).
     * <p>
     * Time Complexity for all operations: O(1)
     * Space Complexity: O(n)
     */
    static class MinStack {
        private final Deque<Integer> mainStack = new ArrayDeque<>();
        private final Deque<Integer> minStack = new ArrayDeque<>();

        void push(int val) {
            mainStack.addLast(val);
            int min = minStack.isEmpty() ? val : Math.min(val, minStack.peekLast());
            minStack.addLast(min);
        }

        void pop() {
            if (!mainStack.isEmpty()) {
                mainStack.removeLast();
                minStack.removeLast();
            }
        }

        int top() {
            return mainStack.peekLast();
        }

        int getMin() {
            return minStack.peekLast();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        System.out.println("Pushing values: 2, 0, 3, 0");
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);

        System.out.println("Current Min: " + minStack.getMin()); // Expected: 0
        minStack.pop();
        System.out.println("After one pop, Min: " + minStack.getMin()); // Expected: 0
        minStack.pop();
        System.out.println("After another pop, Min: " + minStack.getMin()); // Expected: 0
        minStack.pop();
        System.out.println("After another pop, Min: " + minStack.getMin()); // Expected: 2

        System.out.println("Top element: " + minStack.top()); // Expected: 2
    }
}
