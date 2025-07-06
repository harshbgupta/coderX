package com.kritsn.leetcode

/**
 * QUESTION:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 *
 * CONSTRAINTS:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */

fun main() {
    val l1 = createList(2, 4, 3) // 342
    val l2 = createList(5, 6, 4) // 465
    val result = addTwoNumbers(l1, l2)
    printList(result) // Output: 7 -> 0 -> 8 -> null
}

fun createList(vararg digits: Int): ListNode? {
    val dummy = ListNode(0)
    var current = dummy
    for (digit in digits) {
        current.next = ListNode(digit)
        current = current.next!!
    }
    return dummy.next
}

fun printList(node: ListNode?) {
    var current = node
    while (current != null) {
        print("${current.`val`} -> ")
        current = current.next
    }
    println("null")
}


class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummyHead = ListNode(0)
    var p = l1
    var q = l2
    var current = dummyHead
    var carry = 0

    while (p != null || q != null || carry != 0) {
        val x = p?.`val` ?: 0
        val y = q?.`val` ?: 0
        val sum = x + y + carry
        carry = sum / 10

        current.next = ListNode(sum % 10)
        current = current.next!!

        p = p?.next
        q = q?.next
    }

    return dummyHead.next
}

///////////////////////////////////////////////////////////////////////////
// Sol 2 Recursion
///////////////////////////////////////////////////////////////////////////
private fun solAddTwoNumbersRecursion(l1: LinkedListNode?, l2: LinkedListNode?, carry: Int = 0): LinkedListNode? {
    if (l1 == null && l2 == null && carry == 0) return null

    val sum = (l1?.digit ?: 0) + (l2?.digit ?: 0) + carry
    val result = LinkedListNode(sum % 10)
    result.next = solAddTwoNumbersRecursion(l1?.next, l2?.next, sum / 10)
    return result
}

class LinkedListNode(var digit: Int) {
    var next: LinkedListNode? = null
}
