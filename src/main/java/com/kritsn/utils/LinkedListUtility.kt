package com.kritsn.utils

import com.kritsn.leetcodeKotlin._206ReverseLinkedList.ListNode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 20, 2025
///////////////////////////////////////////////////////////////////////////

class LinkedListUtility {
    companion object{
        fun createList(values: List<Int>): ListNode? {
            val dummy = ListNode(0)
            var current = dummy
            for (v in values) {
                current.next = ListNode(v)
                current = current.next!!
            }
            return dummy.next
        }

        fun printList(head: ListNode?) {
            var node = head
            val result = mutableListOf<Int>()
            while (node != null) {
                result.add(node.`val`)
                node = node.next
            }
            println(result)
        }
    }
}