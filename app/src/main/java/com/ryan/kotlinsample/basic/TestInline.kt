package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

// 内联类
// 包装一个原生类型，包装类可能会导致性能开销
// 内联类必须含有唯一的一个在主构造函数中初始化的可读属性
// 运行时，将使用这个属性表示内联类实例！！！
// 内联类不能继承只能实现接口
inline class TestInline constructor(val name: String) {

    // 内联类中不允许存在init块
//    init {
//
//    }

    constructor(age: Int) : this("")

    // 内联类内部不允许拥有幕后字段
    // 因此必须通过get 或set提供或设置值
    val length: Int
        get() = 123

    fun foo() {
        lll("hello this is $length")
    }
}

class SDKFJKJ {

    // 次构造函数中的参数无法使用val 或者var
    constructor(age: String)
}

fun foo9(): Unit {
    val sss = TestInline("my name is ryan!")
    lll("sss = ${sss.javaClass}")
    lll("sss = ${sss is TestInline}")
//    lll("sss = ${sss is String}")

    TestInline("").foo()


    compute(111)
    val xxx = UInt(222)
    compute(xxx)
}


inline class UInt(val x: Int)

fun compute(x: Int) {
    lll("int compute = $x")
}

fun compute(x: UInt) {
    lll("Uint compute = $x")
}
