package com.kritsn.leetcode.stack

import java.util.*

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

// Driver code
fun main() {
    val s = MyStack()
    s.push(1)
    s.push(2)
    s.push(3)


    println("current size: ${s.size()}")
    println(s.top())
    s.pop()
    println(s.top())
    s.pop()
    println(s.top())
    println("current size: ${s.size()}")
}


class MyStack {
    private val q1: Queue<Int> = LinkedList()
    private val q2: Queue<Int> = LinkedList()

    fun pop() {
        if (q1.isEmpty()) return

        // Leave one element in q1 and push others in q2.
        while (q1.size != 1) {
            q2.add(q1.poll())
        }

        // Pop the only left element from q1
        q1.poll()

        // swap the names of two queues
        val temp = q1
        q1.clear()
        q1.addAll(q2)
        q2.clear()
        q2.addAll(temp)
    }

    fun push(x: Int) {
        q1.add(x)
    }

    fun top(): Int {
        if (q1.isEmpty()) return -1

        while (q1.size != 1) {
            q2.add(q1.poll())
        }

        // last pushed element
        val temp = q1.peek()

        // to empty the auxiliary queue after last operation
        q1.poll()

        // push last element to q2
        q2.add(temp)

        // swap the two queues names
        val tempQueue = q1
        q1.clear()
        q1.addAll(q2)
        q2.clear()
        q2.addAll(tempQueue)
        return temp
    }

    fun size(): Int {
        return q1.size
    }
}