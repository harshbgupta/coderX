package com.kritsn.ivs.redElk

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

class LambdaReturn {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val t = LambdaReturn()
            println("--------- test ----------")
            t.test()
            println("--------- test0 ----------")
            t.test0()
            println("--------- test1 ----------")
            t.test1()
            println("--------- test2 ----------")
            t.test2()
            println("--------- test3 ----------")
            t.test3()
            println("--------- test4 ----------")
            t.test4()
        }
    }


    fun test() {
        listOf(1, 2, 3, 4).filter {
            return //break the whole method
        }
        println("saaasa") // This will not be printed
    }

    fun test0() {
        listOf(1, 2, 3, 4).forEach {
            if (it == 3) return  // Only exits the lambda for this iteration, break the whole method
            println(it)
        }
        println("This line will NOT be printed if 3 is found") // This will not be printed as once `return` statement found
    }

    fun test1() {
        listOf(1, 2, 3, 4).forEach {
            if (it == 3) return@forEach  // Only exits the lambda for this iteration, break the only for loop
            println(it)
        }
        println("Done") // This will be printed
    }

    fun test2() {
        val mapped = listOf(1, 2, 3).map {
            if (it == 2) {
                println(0)
                return@map 0
            } else {
                println(it * 2)
            }
        }.toList()
        mapped.forEach { println("tst: $it") }
    }


    fun test3() {
        val numbers = listOf(1, 2, 3, 4)
        numbers.forEach label@{
            if (it == 3) return@label
            println(it)
        }
        println("tst 3") // This will be printed
    }

    fun test4() {
        val runnable = Runnable {
            // return  // ❌ Compilation error: Cannot make a non-local return
        }
    }
}