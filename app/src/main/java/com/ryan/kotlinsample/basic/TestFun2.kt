package com.ryan.kotlinsample.basic

fun cat() {
    val x = {
        // 这里无法使用return
//        return
    }

}


inline fun cat1(msg: String, noinline tag: () -> Unit): Unit {

}

inline fun inlined(block: () -> Unit) {
    println("hi!")
}

fun foo() {
    inlined {
        return // OK：该 lambda 表达式是内联的
    }

    val clazz: Class<String>

    A::class.java

}
