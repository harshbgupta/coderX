package com.kritsn.ivs.moveInSync


fun myFun() {
    System.out.println("code inside inline function")
}

fun inlineFun(myFun: () -> Unit) {

}

fun main() {
    println("code inside inline function")
//    inlineFun({ System.out.println("calling inline functions")})
}