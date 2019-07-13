package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
annotation class TestAnnotation(val name: String)

@TestAnnotation("测试类注解")
class TestAnnotation1 constructor() {

    @field:TestAnnotation("测试字段注解")
    val size: Int = 123

    @Deprecated(
        "gg mtfker",
        replaceWith = ReplaceWith("getAge()"),
        level = DeprecationLevel.WARNING
    )
    fun getName(): String {
        return "sb"
    }

    fun getAge(): String {
        return "big sb"
    }

    val c = TestAnnotation::class

}

fun mainta(): Unit {

    val t1 = TestAnnotation1()
    val reuslt = t1.getName()
    lll("result = $result")

}