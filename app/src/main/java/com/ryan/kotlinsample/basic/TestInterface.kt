package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

abstract class TestInterface {
    fun f() {}

    abstract fun f2()

}

class H1 : TestInterface() {

    override fun f2() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

interface I1 {
    // 接口中的属性默认是抽象的
    // 或者 提供访问器的实现
    val name: String
    // 接口中的属性不能使用幕后字段(field)
    val name1: String
        get() = "123"

    fun f1()

    fun f2()

    // 如果方法提供了默认的实现，那么就不是abstract了
    fun f3() {}
}

interface I2 {

    fun f1()

    fun f3() {}
}


class H2(override val name: String) : I1, I2 {
    override fun f3() {
        // 不能够调用父类的抽象方法！！！
//        super<I2>.f1()

        // 可以通过设置泛型来指定调用的父类
        super<I1>.f3()
        super<I2>.f3()
    }

    override fun f1() {

    }

    override fun f2() {

    }
}

