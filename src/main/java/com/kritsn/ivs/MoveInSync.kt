package com.kritsn.ivs


fun myFun() {
    System.out.println("code inside inline function")
}

fun inlineFun(myFun: () -> Unit) {

}

fun main() {
    println("code inside inline function")
//    inlineFun({ System.out.println("calling inline functions")})
}