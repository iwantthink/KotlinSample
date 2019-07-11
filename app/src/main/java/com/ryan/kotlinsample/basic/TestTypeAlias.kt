package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

// 类型别名有助于缩短拥有较长泛型的类型名称
typealias IntArrayList = ArrayList<Int>

fun foo7() {
    val ial = intArrayOf(1, 2, 3)
    // 编译器提示类型错误
//    val ial2 = intArrayOf("1","2","3")

    val x: SB = { index, name ->
        lll("index = $index")
        lll("name = $name")
    }

    val haha: TArrayList<String> = arrayListOf("1", "12")
    // 编译器提示类型错误,需要的是String，传入的是Int
//    val haha1: TArrayList<String> = arrayListOf(1, 2, 3)

    val hehe: TArrayList<Int> = arrayListOf(1, 2, 3)
    // 类型错误
//    val hehe1:TArrayList<Int> = arrayListOf("1")
}

// 通过类型别名 还能够为函数类型提供另外的别名

typealias SB = (Int, String) -> Unit

class Boring {

    inner class InnerBoring {

    }
}

typealias BoringInner = Boring.InnerBoring

// 类型别名也可以使用泛型
typealias TArrayList<T> = ArrayList<T>