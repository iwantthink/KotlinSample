package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.TestJava
import com.ryan.kotlinsample.lll
import java.util.*

fun mainTestCallJava(): Unit {
    val c = Calendar.getInstance()
    c.firstDayOfWeek = 123
    val x = c.clear()
    lll("x = $x")

    val tj = TestJava<String>()
    tj.name
    tj.type
    tj.type = ""
    tj.isSb("2")


}

class CCCC {
    companion object {
        @JvmStatic fun callStatic() {}
        fun callNonStatic() {}
    }
}