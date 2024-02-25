package com.vertical.ivs

import toJsonString

fun main() {
    quesFindMedianFromTwoArray()
}

///////////////////////////////////////////////////////////////////////////
// Interview round 1
// Question: Given two array (arr1, arr2)  find the median of merged sorted array
// e.g. 1: arr1 = [9,1,5,3], arr2 = [6,1, 3,4], ans  = 4.5
// explanation: final merged sorted sortedArray = [1,3,4,5,6,9] (duplication is not allowed), med = (4+5)/2
//
// e.g. 2: arr1 = [9,1,5,3], arr2 = [6,1, 3,4,10], ans  = 4.5
// explanation: final merged sorted sortedArray = [1,3,4,5,6,9,10], med = 5
///////////////////////////////////////////////////////////////////////////
fun quesFindMedianFromTwoArray() {
    val sortedArray = mergeArray(intArrayOf(9, 1, 5, 3), intArrayOf(6, 1, 3, 4, 10))
    println(findMedian(sortedArray))
}

fun mergeArray(array1: IntArray, array2: IntArray): IntArray {
    val hashSet = hashSetOf<Int>()
    array1.forEach { data ->
        hashSet.add(data)
    }
    array2.forEach { data ->
        hashSet.add(data)
    }
    println(toJsonString(hashSet))
    return hashSet.toIntArray()
}

fun findMedian(sortedArray: IntArray): Double {
    val length = sortedArray.size
    return if (length % 2 == 0) {
        val med: Int = (length / 2) - 1
        val median = ((sortedArray[med].toDouble() + sortedArray[med + 1]) / 2)
        median
    } else {
        val med = (length / 2)
        (sortedArray[med]).toDouble()
    }
}

