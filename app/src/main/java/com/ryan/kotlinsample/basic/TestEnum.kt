package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

interface BBB {
    fun getBBB()
}


/**
 * 每一个枚举都是其枚举类的实例!
 */
enum class TestEnum constructor(val num: Int) : BBB {
    // 因此可以这样进行初始化
    ONE(1),
    TWO(2),
    //枚举类可以声明自己的匿名类
    THREE(3) {
        override fun toString(): String {
            return "123"
        }
    };
    // 上面必须有一个分号,如果枚举类存在自己的方法

    fun getStyle() {

    }

    /**
     * 枚举类可以实现接口，但是不能继承类
     * 一旦实现了接口，可以选择枚举类中统一为枚举提供实现
     * 也可以单独为每一个枚举提供实现
     */
    override fun getBBB() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

open class ABC {
    fun getName() {

    }
}

fun foo4() {
    val x1 = TestEnum.ONE.num
    TestEnum.ONE.getStyle()

    val x2 = TestEnum.THREE.toString()
    lll("x2 = $x2")
    lll("x1 = $x1")

    val x = object : ABC() {

    }

    TestEnum.values().forEach {
        lll("it  = $it")
    }

    val y = TestEnum.valueOf("ONE")



}