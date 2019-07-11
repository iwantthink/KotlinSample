package com.ryan.kotlinsample.basic

// data 数据类必须有一个主构造函数，
// 并且主构造函数中需要至少有一个参数
data class TestData constructor(var name: String, var age: Int) {
}

// 设置了默认值之后，就会生成一个无参构造函数,以供外部使用
data class TestData1 constructor(var name: String = "", var age: Int = 1)

open class TestDataA {
    open fun foo() {

    }

    final override fun toString(): String {
        return "TestData AAAAAA"
    }
}

// 编译器只会使用 主构造函数中的属性去生成哪些 自动生成的函数 例如 hashCode toString 等..
data class TestData2 constructor(var name: String = "") : TestDataA() {

    // 可以重写一些data类会自动生成的函数，这将会覆盖自动生成的函数
    override fun hashCode(): Int {
        return 123
    }

    // TestDataA 中的toString()方法，如果被final修饰，那么也会覆盖TestData2
    // componentN 和copy 函数是无法被重写的 并且也不允许

}