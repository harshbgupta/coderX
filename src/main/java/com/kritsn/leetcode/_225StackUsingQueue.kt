package com.kritsn.leetcode

/**
 * 225. Implement Stack using Queues
 * Easy
 * Topics
 * Companies
 * Amazon
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 */

/**
 * MyStack implements a stack (LIFO) using two queues.
 *
 * This class provides the standard stack operations:
 * - push(x): Pushes element x onto the stack.
 * - pop(): Removes and returns the element on the top of the stack.
 * - top(): Returns the element on the top of the stack without removing it.
 * - empty(): Returns true if the stack is empty, false otherwise.
 *
 * Internally, two custom queues (MyQueue) are used to simulate stack behavior.
 * The push operation reverses the order of elements to maintain LIFO order.
 *
 * Example usage:
 * val stack = MyStack()
 * stack.push(1)
 * stack.push(2)
 * println(stack.top()) // 2
 * println(stack.pop()) // 2
 * println(stack.empty()) // false
 */

class MyStack() {
    private val first = MyQueue<Int>()
    private val second = MyQueue<Int>()

    /**
     * Pushes element x onto the stack.
     * This is done by first moving all elements from 'first' to 'second',
     * adding the new element to 'first', then moving all elements back from 'second' to 'first'.
     * This ensures the newest element is always at the front of 'first',
     * simulating LIFO stack behavior using queues.
     *
     * @param x The element to push onto the stack.
     */
    fun push(x: Int) {
        while (first.peek() != null) {
            second.add(first.remove()!!)
        }
        first.add(x)
        while (second.peek() != null) {
            first.add(second.remove()!!)
        }
    }

    /**
     * Removes and returns the element on the top of the stack.
     * If the stack is empty, returns -1.
     *
     * @return The top element of the stack, or -1 if the stack is empty.
     */
    fun pop(): Int {
        return first.remove() ?: -1
    }

    /**
     * Returns the element on the top of the stack without removing it.
     * If the stack is empty, returns -1.
     *
     * @return The top element of the stack, or -1 if the stack is empty.
     */
    fun top(): Int {
        return first.peek() ?: -1
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    fun empty(): Boolean {
        return first.isEmpty()
    }

}

/**
 * Custom singly linked list node for queue implementation.
 * @param T The type of value stored in the node.
 * @property value The value stored in the node.
 * @property next Reference to the next node in the list.
 */
data class MyLinkedList<T>(
    var value: T,
    var next: MyLinkedList<T>? = null
)

/**
 * Custom queue implementation using a singly linked list.
 * Supports standard queue operations: add (enqueue), peek (front), remove (dequeue), and isEmpty.
 * @param T The type of elements held in this queue.
 */
class MyQueue<T> {
    private var last: MyLinkedList<T>? = null // Points to the last node (tail) of the queue
    private var first: MyLinkedList<T>? = null // Points to the first node (head) of the queue

    /**
     * Adds an element to the end of the queue.
     * @param value The value to be added.
     */
    fun add(value: T) {
        if (first == null) {
            first = MyLinkedList(value)
            last = first
        } else {
            last?.next = MyLinkedList<T>(value)
            last = last?.next
        }
    }

    /**
     * Returns the value at the front of the queue without removing it.
     * @return The value at the front, or null if the queue is empty.
     */
    fun peek(): T? {
        return first?.value
    }

    /**
     * Removes and returns the value at the front of the queue.
     * @return The removed value, or null if the queue is empty.
     */
    fun remove(): T? {
        if (first == null) {
            return null
        }

        val temp = first?.value
        first = first?.next

        if (first == null) {
            last = null // If the queue is now empty, reset last as well
        }

        return temp
    }

    /**
     * Checks if the queue is empty.
     * @return True if the queue is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return first == null
    }
}