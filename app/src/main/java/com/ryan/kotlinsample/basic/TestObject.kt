package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

val tobj = object {

}

object NamedObj {
    val x = 1
    fun f() {

    }
}


fun foo5() {
    NamedObj.f()

    object {
        val innerx = 123
    }
}


open class TOC {

    open fun foo(): Any {
        return object {}
    }

    // 返回值类型为Any
    fun foo1() = object {
        val x = 123456
    }

    // 返回值类型是匿名函数
    private fun foo2() = object {
        val x = 123
    }

    // 返回值类型是TOC
    fun foo3() = object : TOC() {
        val x = 222
    }


    fun main() {
        // 因为返回值类型是Any,所以无法调用x
//        foo1().x
        // 可以知道返回值的类型是 匿名函数，并且可以调用
        foo2().x
        // 返回值类型是TOC
//        foo3().x

        var xz = 123
        fun fooo() {}

        val zz = object : TOC() {

            override fun foo() {
                xz
                fooo()
            }

        }
    }

}

// 对象声明 能够得到一个单例
object TestObject {
    val x = 123

    // 对象声明可以嵌套在另外一个对象声明内部
    // 但是不能在局部作用域中(即嵌套在函数内部)
    object InnerTestObject {

    }
}

class TBCC {
    var height = 123

    object InnerTBCC1 {
        var size = 123
    }

    // 一个类中只能存在一个 伴生对象
    // 伴生对象看起来像是静态成员，但是在运行时还是对象的实例成员
    // 伴生对象也可以实现接口和类
    // 在JVM平台，使用@JvmStatic 可以使得半生对象的成员称为真正的静态方法和字段

    companion object InnerTBCC : A() {
        // 真正的静态方法
        @JvmStatic
        var size = 123

        override fun a() {
            super.a()
        }
    }

//    companion object InnerTBCC2{
//
//    }
}

var tempInt1 = 11111

interface TempTest1 {
    var tempInt: Int
}

// 对象表达式 如果没有继承和实现任何接口或类，那么其赋值给变量的类型就是Any
// 因此就无法调用其中声明的字段和函数，因为这样类型不对！
val lazyObj = object : TempTest1 {

    override var tempInt: Int
        get() = tempInt1
        set(value) {}
}


// 对象声明时第一次被访问到时延迟初始化
object InstantObj {
    val instantInt
        get() = tempInt1
}


fun foo6() {
    TestObject.x
    // 调用伴生对象中的属性或者函数时，可以省略伴生对象的名称
    TBCC.InnerTBCC.size
    // 这里调用的就是伴生对象的属性 而不是对象声明中的！！！
    TBCC.size
    // 如果定义时没有给companion 添加名称，那么可以使用 类名+Companion关键字 调用
    TBCC.InnerTBCC.a()

    // 调用对象声明中的size
    TBCC.InnerTBCC1.size

    lll("lazyObj.tempInt = ${lazyObj.tempInt}")
    lll("instantObj.tempInt = ${InstantObj.instantInt}")
    tempInt1 = 33333
    //会输出3333  但是这是因为它的get..
    lll("lazyObj.tempInt = ${lazyObj.tempInt}")
    lll("instantObj.tempInt = ${InstantObj.instantInt}")
}