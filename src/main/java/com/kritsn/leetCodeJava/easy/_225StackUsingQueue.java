package com.kritsn.leetCodeJava.easy;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 225. Implement Stack using Queues
 * Easy
 * Topics
 * Companies
 * Amazon
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 * <p>
 * Implement the MyStack class:
 * <p>
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 * <p>
 * You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 */
public class _225StackUsingQueue {

    /**
     * Custom singly linked list node for queue implementation.
     *
     * @param <T> The type of value stored in the node.
     */
    static class MyLinkedList<T> {
        T value;
        MyLinkedList<T> next;

        MyLinkedList(T value) {
            this.value = value;
        }
    }

    /**
     * Custom queue implementation using a singly linked list.
     * Supports standard queue operations: add (enqueue), peek (front), remove (dequeue), and isEmpty.
     *
     * @param <T> The type of elements held in this queue.
     */
    static class MyQueue<T> {
        private MyLinkedList<T> last;  // Points to the last node (tail) of the queue
        private MyLinkedList<T> first; // Points to the first node (head) of the queue

        /**
         * Adds an element to the end of the queue.
         *
         * @param value The value to be added.
         */
        void add(T value) {
            if (first == null) {
                first = new MyLinkedList<>(value);
                last = first;
            } else {
                last.next = new MyLinkedList<>(value);
                last = last.next;
            }
        }

        /**
         * Returns the value at the front of the queue without removing it.
         *
         * @return The value at the front, or null if the queue is empty.
         */
        T peek() {
            return first != null ? first.value : null;
        }

        /**
         * Removes and returns the value at the front of the queue.
         *
         * @return The removed value, or null if the queue is empty.
         */
        T remove() {
            if (first == null) {
                return null;
            }

            T temp = first.value;
            first = first.next;

            if (first == null) {
                last = null; // If the queue is now empty, reset last as well
            }

            return temp;
        }

        /**
         * Checks if the queue is empty.
         *
         * @return True if the queue is empty, false otherwise.
         */
        boolean isEmpty() {
            return first == null;
        }
    }

    /**
     * MyStack implements a stack (LIFO) using two queues.
     * <p>
     * This class provides the standard stack operations:
     * - push(x): Pushes element x onto the stack.
     * - pop(): Removes and returns the element on the top of the stack.
     * - top(): Returns the element on the top of the stack without removing it.
     * - empty(): Returns true if the stack is empty, false otherwise.
     * <p>
     * Internally, two custom queues (MyQueue) are used to simulate stack behavior.
     * The push operation reverses the order of elements to maintain LIFO order.
     * <p>
     * Example usage:
     * MyStack stack = new MyStack();
     * stack.push(1);
     * stack.push(2);
     * System.out.println(stack.top()); // 2
     * System.out.println(stack.pop()); // 2
     * System.out.println(stack.empty()); // false
     */
    static class MyStack {
        private final MyQueue<Integer> first = new MyQueue<>();
        private final MyQueue<Integer> second = new MyQueue<>();

        /**
         * Pushes element x onto the stack.
         * This is done by first moving all elements from 'first' to 'second',
         * adding the new element to 'first', then moving all elements back from 'second' to 'first'.
         * This ensures the newest element is always at the front of 'first',
         * simulating LIFO stack behavior using queues.
         *
         * @param x The element to push onto the stack.
         */
        void push(int x) {
            while (first.peek() != null) {
                second.add(first.remove());
            }
            first.add(x);
            while (second.peek() != null) {
                first.add(second.remove());
            }
        }

        /**
         * Removes and returns the element on the top of the stack.
         * If the stack is empty, returns -1.
         *
         * @return The top element of the stack, or -1 if the stack is empty.
         */
        int pop() {
            Integer removed = first.remove();
            return removed != null ? removed : -1;
        }

        /**
         * Returns the element on the top of the stack without removing it.
         * If the stack is empty, returns -1.
         *
         * @return The top element of the stack, or -1 if the stack is empty.
         */
        int top() {
            Integer peeked = first.peek();
            return peeked != null ? peeked : -1;
        }

        /**
         * Checks if the stack is empty.
         *
         * @return True if the stack is empty, false otherwise.
         */
        boolean empty() {
            return first.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top()); // 2
        System.out.println(stack.pop()); // 2
        System.out.println(stack.empty()); // false
    }
}
