package com.ryan.internaltest

/**
 * public
 */
fun f() {}

/**
 * public
 */
class Zoo {}

/**
 * private
 */
private fun f1() {}

/**
 * private 顶层函数，只能在当前文件中使用
 */
private class Zoo1 {}

/**
 * internal
 */
internal fun f2() {
    f1()
    Zoo1()
}

/**
 * internal
 */
internal class Zoo2 {

}

//protected 不适合顶层

//protected fun f3(){
//
//}


open class TestInternal {

    // 类中的private仅能在当前类中使用，子类都不行
    private val name: String = ""
    val size: Int = name.length

    // protected 与 private 相似,额外的子类还可以
    protected val lastName: String = ""

    internal val firstName: String = ""

    // 只有全局变量 函数 类 等才能拥有可见性修饰符
    protected class Nested {
        val e: Int = 5
    }

    var age = 123


    fun main(args: Array<String>) {
        name
        firstName

        // 局部变量，函数，类 不能拥有可见性修饰符
        class InnerClass {}

    }

}

class SubTestInternal : TestInternal() {

    fun main() {
        lastName
        firstName
        Nested().e

    }

}


