package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.TestJava
import com.ryan.kotlinsample.lll
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.javaGetter

fun maintr(): Unit {
    val c = Seal::class
    lll(c)
    lll(c.java)
    lll(String::class)
    lll(String::class.java)
    lll(String::class.qualifiedName)


    val method = Seal::eat

    val isEmptyStringList: List<String>.() -> Boolean = List<String>::isEmpty

    listOf<String>().isEmptyStringList()


    val nameFromSeal = Seal::name
    val nameGetFromSeal = Seal::name.getter

    val method2: (String) -> Int = String::length

    val strs = listOf<String>("a", "bc", "def")
    // 因为 KProperty 继承自(T)->R
    // 而map 接收的参数要求符合 (T)->R
    lll(strs.map(String::length))

    lll(Seal::type.javaGetter)

    Seal::type.javaClass.kotlin


    val numberRegex = "\\d+".toRegex()
    val isNum = numberRegex::matches

    lll(isNum("123"))
    lll(isNum("aaa"))


    val r1 = "sbdx".let {
        "aaaa"
    }
    lll("r1 =$r1")

    val r2 = "sbdx".map {
        it.hashCode()
    }

    lll("r2 = $r2")


    val r11 = "111".run {
        "2222"
    }

    lll("r11 =$r11")

    val r22 = "111".apply {
        "222"
    }.apply {
        "333"
    }

    lll("r22 = $r22")

    val r33 = with("111", {
        lll("this = $this")
    })

    lll("r33 = $r33")

    val r44 = "111".let {
        "222"
    }
    lll("r44 =$r44")

    val r55 = "111".also {

    }

    lll("r55 = $r55")


    run {

    }


    val r6 = mutableListOf<String>("a", "b", "c").let(::println)
    lll("r6 = $r6")

    // 满足 lambda ，则返回值
    // 不满足lambda ，则返回null
    val r7 = "a".takeIf {
        it.startsWith("a")
    }

    // 满足 lambda ,则返回null
    // 不满足lambda ,则返回值
    val r8 = "a".takeUnless {
        it.startsWith("a")
    }
    lll("r7 = $r7 , r8 = $r8")


    val x: Int? = 123
    val x1: Int? = null

    val y = x as Long
    val y1 = x as? Long

    val z1 = x1 as Long
    val z2 = x1 as?Long

    val testJava = TestJava<String>()


}

class Seal(val name: String = "小海豹") {

    val type: Boolean
        get() = false

    fun eat(): Unit {
        lll(::type.get())
        lll("吃小鱼")
    }
}