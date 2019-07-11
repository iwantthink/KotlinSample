package com.ryan.kotlinsample.review

import kotlin.properties.Delegates

inline class TestInline(val name: String)


interface Base {
    val message: String
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override val message = "BaseImpl: x = $x"

    override fun print() {
        println(message)
    }
}

class Derived(b: Base) : Base by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message = "Message of Derived"
}

fun main() {
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.print()
    println(derived.message)
}

// 不使用锁进行同步！
val cc by lazy(LazyThreadSafetyMode.NONE) {
    return@lazy 123
}

val dd by Delegates.observable(1) { p, oldValue, newValue ->

}

val gg by Delegates.vetoable<Int>(1) { p, oldValue, newValue ->
    sbbb(fun(input: Int) {

    })

    false

}

fun sbbb(x: (Int) -> Unit): Unit {

    val xdd = 1.addPlus(2)
    addPlus(1, 2)

    val list: List<String> = listOf<String>()

    val mmm = mapOf<String, String>()
    mmm.values
}


val addPlus: Int.(Int) -> Int = {
    this + it
}