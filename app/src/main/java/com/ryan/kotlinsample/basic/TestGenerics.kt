package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

class Box<T>(t: T) {
    val size: T = t
}

// 声明处型变
// out 是型变注解，它允许 A<Derived> 赋值给 A<Any>
interface Source<out T> {

}

// 这条赋值表达式就是 协变!! 即 ? extends T
// 也就是说 协变指的是泛型 可以是 T 或者T的子类
// 因此这里的 变量x(List<Any>) 可以接收 List<Int>
val x: List<Any> = List<Int>(10) {
    return@List it
}

// 这条赋值表达式就是 逆变 ！！ 即 ? super T
// 逆变指的是 泛型可以是 T 或者T的父类
// 因此这里的 变量y(Comparable<Int>)可以接收Comparable<Number>
val y: Comparable<Int> = object : Comparable<Number> {
    override fun compareTo(other: Number): Int {
        return 123
    }
}

class Cup<T> {

    fun foo(t: T) {
        lll("t = $t")
    }
}

class Cup2 {

    fun <T> foo(t: T) {
        lll("声明处型变")
    }

    fun foo1(t: Cup<in String>) {
        lll("使用处型变")
    }
}


open class CupA {

}

open class SubCupA : CupA() {

}

open class SubSubCupA : SubCupA() {

}


/**
 * 这里接收的泛型是Any,但是由于不型变，因此Array<String>是不允许传递的
 * 那么这里就需要添加一个out 用来表示 协变，这样Array<String>就允许传递
 *
 */
fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

fun CupTest() {
    val from = Array<SubSubCupA>(5) { return@Array SubSubCupA() }
    cupExecute(from)
    val from2 = Array<SubCupA>(5) { return@Array SubCupA() }
    cupExecute(from2)

    val x = cupExecute2<String>()


}

/**
 * 如果这里没有添加in ，那么from2 是无法作为参数传入的，编译器会报错，因为不型变!!!
 *
 */
fun cupExecute(from: Array<in SubSubCupA>) {

}

/**
 * 如果指定了函数的泛型，那么使用的时候就必须在函数名后加上类型
 */
fun <T> cupExecute2() {
}

class AAAAA<in T : SubCupA> {

    fun f1(s: T) {

    }

//    fun foo(t: T) {
//        when (t) {
//            is CupA -> lll("类型是CupA")
//            is SubCupA -> lll("类型是SubCupA")
//            is SubSubCupA -> lll("类型是SubSubCupA")
//
//            else -> {
//                lll("未找到合适的类型")
//            }
//        }
//    }
}

/**
 * 如果同一个泛型需要多个上界，那么需要借助where
 * 下面的调用函数时，所传递的参数T 就必须满足where 的俩个条件才行
 */
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}

