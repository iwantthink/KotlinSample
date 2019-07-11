package com.ryan.kotlinsample.review

import com.ryan.kotlinsample.lll

fun main(args: Int) {

    MyTTT.name
    MyTTT.TTT.name
    MyTTT.name

    target = "yyy"
    lll("Tiger.name = ${Tiger.name}")
    lll("chicken = ${chicken.name}")

}

class MyTTT {

    val name: String = "xxxx"

    // 伴生对象与 静态初始化块语义匹配
    companion object TTT {
        val name: String = "!23"
    }

}


typealias IntArr<T> = ArrayList<T>
typealias Ahaha = () -> Boolean

var target: String = "xxx"

// 对象声明 属于延迟初始化！！！！
// 在第一次被访问到时 才进行初始化
object Tiger {
    val name = target
}

// 对象表达式 会在使用的地方立即进行初始化
private val chicken = object {
    val name = target
}

