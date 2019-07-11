package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

fun main(): Unit {

    val obj = TestComponent()
    val (name, age) = obj

    val (name1, age1) = TestComponent()

    val (name2, age2) = TestComponent1("", 1)

    val (_, _) = TestComponent()
    val m = mapOf<Int, String>(1 to "1", 2 to "2")
    m.mapValues { (key: Int, value: String) ->
        return@mapValues value
    }

    fun String.test() {
        lll("test!!!!!")
    }

    "".test()
    val funTest = fun String.() {

    }

    val ff = fun(i: String) {

    }


    "".funTest()

    funTest("abc")

    "1 ".equals("")

    var xx: String? = "123"

    lll(xx?.length ?: "this is null")
    lll(xx?.length)
    xx = null
    lll(xx?.length ?: "this is null")
    lll(xx?.length)
//    lll(xx.length)


    val x1 = TestComponent1("ryan", 1)
    val x2 = TestComponent1("ryan", 1)
    val x3 = TestComponent1("ryan", 2)
    lll(x1 == x2)
    lll(x1 == x3)
    lll(x2 == x3)


}


class TestComponent {
    operator fun component1(): Any {
        return name
    }

    operator fun component2(): Int {
        return age
    }

    val name = ""
    val age = 123
}

data class TestComponent1(val name: String, val age: Int) {

    fun equals(other: TestComponent1?): Boolean {
        return true
    }
}