package com.ryan.kotlinsample.basic

import com.Person
// 顶层属性
import com.name
// 顶层函数
import com.testImport
// 对象声明中的函数
import com.Person.getName
// 对象声明中的属性
import com.Person.age
import com.ryan.kotlinsample.lll
import com.testImport


private fun testNestedReturn() {
    // 直接使用return 将直接 结束整个函数的执行
    // forEach 接收一个lambda 表达式
//        listOf<Int>(1, 2, 3, 4).forEach {
//            lll("index = $it")
//            if (it == 2) {
//                return
//            }
//        }
//        lll("this point is unreachable")


    listOf<Int>(1, 2, 3, 4).forEach end@{
        if (it == 2) {
            // 返回lambda的调用者，即forEach处,开始下一个lambda
            return@end

            // 可以直接使用 调用lambda的函数名称,在这里就是forEach
//                return@forEach
        }
        lll("index = $it")
    }
    lll("this point is reachable1")


    listOf(9, 9, 4345, 56).forEach(fun(value: Int) {
        if (value == 3) return
        lll("value = $value")
    })

    lll("this point is reachable2")


    run loop@{
        listOf(8, 78, 8, 78, 98).forEach {
            // 结束run函数 ，而不是整个函数
            if (it == 78) return@loop
            lll("it = $it")
        }
    }

    lll("this point is reachable3")

    val result = getAge()
    lll("result = $result")

}

private fun getAge(): String {
    // 可以借助标签返回值
    return kotlin.run lab@{
        return@lab "18"
    }
}


private fun testLabel() {
    loop@ for (i in 1..10) {
        for (j in 20..30) {
            lll("i = $i, j = $j")
            // i = 5时 跳出俩个循环
            if (i == 5) {
                // 如果只使用break ，那只能跳出内部循环
                break@loop
            }
            // j = 22 时，结束内部循环， 开始下一个外部循环
            if (j == 22) {
                continue@loop
            }
        }
    }

}

private fun testContinue() {
    for (i in 1..19) {
        if (i % 2 == 0) {
            continue
        }
        lll("i = $i")
    }


}

private fun testBreak() {
    for (intRange in arrayOf(1, 2, 3, 3, 45, 4, 5, 4, 5)) {

        lll("intRage = $intRange")
        if (intRange == 5) {
            break
        }
    }
    lll("Break会跳出循环，而不是函数")

}

private fun testReturn() {
    // return 从 直接包围它的函数或匿名函数返回
//        for (i in 1..10) {
//            lll("i = $i")
//            if (i == 5) {
//                return
//            }
//        }

    // return 可以在表达式中使用
    val name: String? = "a"
    // 表达式是Nothing类型
    val s = name ?: return
    lll("s = $s")

}

private fun testWhile(x: Int) {
    // x 是 val

    var y = 11
    while (y > 0) {
        lll("y = $y")
        y--
    }
}

private fun testFor() {

    for (i: Int in 1..10) {
        lll("i = $i")
    }

    for (i in 6 downTo 3 step 2) {
        lll("i = $i")
    }

    val a1 = arrayOf(14, 62, 83, 64, 53)
    for (index in a1.indices) {
        lll("a1[$index] = ${a1[index]}")
    }

    // 或者直接使用 "(index,value)" 去接收
    for (withIndex in a1.withIndex()) {
        lll("withIndex = ${withIndex.index}")
        lll("withIndexValue = ${withIndex.value}")

    }

}

private fun testWhen(x: Any) {
    // [Ljava.lang.Integer;
    val validNumber = arrayOf(123)
    lll("valid number = ${validNumber.javaClass}")
    // 无法被用作 when的条件  [I
    val validNumber2 = intArrayOf(1, 2, 34)
    lll(" validNumber = ${validNumber.javaClass}")

    when (x) {
        true -> lll("抓到内容 true")
        is Person -> x.age // 由于智能转换,因此可以直接使用对象中的属性或函数
        in validNumber -> lll("内容位于数组中")
        in 1..10 -> lll("内容位于 1..10 之间(闭区间)")
        2, 3, 4 -> lll("抓到内容 2 or 3 or 4")
        1 -> lll("抓取到内容 = 1")
        0x1123 -> lll("抓取到内容 = 0x1123")
        else -> {
            lll("未匹配到内容")
        }
    }

    val a: Int = 123
    // 当不传入参数时，会根据条件的结果判断是否执行
    when {
        true -> lll("执行true")
        else -> lll("无法执行")
    }

    val result = when (val resp = getType(11)) {
        is Boolean -> "Boolean"
        is Long -> "Long"
        else -> {
            "Other"
        }
    }
    lll("result = $result")

}

private fun getType(input: Int): Any {
    return if (input % 2 == 0) {
        true
    } else {
        lll("long")
        123345L
    }

}

private fun testIf() {
    var max = 0
    var a = 1
    var b = 2

    if (a > b) {
        max = a
    } else {
        max = b
    }
    // if表达式
    // 不可以使用return,最后的表达式会作为值返回
    val maxPlus = if (a > b) a else b

}

fun getMaxNum(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}


private fun testUseImport() {
    testImport()
    name
    Person.getName()
    Person.age
}

private fun testObjectDeclare() {


}

private fun testString() {
    "sbqyj".forEach {
        lll("it = $it")
    }
    for (i: Char in "sbqyj") {
        lll("i = $i")
    }

    val a1 = "abc" + 123
    lll("a1 = $a1")

    val s1 = "test|\n|test"
    lll(s1)
    val s2 = """123
            |456   123
            |678
            |999
        """.trimMargin()
    lll(s2)

    lll("${'$'}s1")
    lll("""${'$'}s1,'$'s1""")

}

@ExperimentalUnsignedTypes
private fun testUnsignedInt() {
    Byte
    Short
    Int
    Long
    Float
    Double
    val a1 = 1
    lll("a1 = ${a1.javaClass}")

    val a2: ULong = 0xFFFF_FFFF_FFFFu // ULong：未提供预期类型，常量不适于 UInt
    lll("a2 = ${a2.javaClass}")


    val a3 = 123uL
    val a4 = 123uL
    lll("a3 = ${a3.javaClass}")
}

@ExperimentalUnsignedTypes
private fun testArray() {
    // 注意区分 arrayListOf,它是创建ArrayList的
    val a1: Array<Int> = arrayOf(1, 2, 3)
    lll("a1 = $a1")
    a1.forEach {
        lll("it = $it")
    }

    val a2 = arrayOfNulls<Int>(5)
    lll("a2[0] = ${a2[0]}")
    lll("a2[0] = ${a2.get(0)}")

    val a3 = Array(5) { it * 2 }
    for (i in a3) {
        lll("i = $i")
    }


    val bArray: ByteArray = byteArrayOf(1)
    lll("bArray type = ${bArray.javaClass}")
    val iArray: IntArray = intArrayOf(1, 2, 3)
    lll("iArray type = ${iArray.javaClass}")

    ubyteArrayOf(1u, 2u, 3u, 1u, 1u)

}

private fun testChar() {
    lll("'1' type = ${'1'.javaClass}")

    lll("s${'\n'}b")

}

private fun testInterval() {
    var f = 1..3
    lll("1..3 = ${f.javaClass}}")

    var f2 = decimalDigitValue('1')
    lll("f2 = $f2")

}

fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9') throw IllegalArgumentException("Out of range")
    return c.toInt() - '0'.toInt() // 显式转换为数字
}

private fun testBitOperator() {
    var f = 1 shl 2
    lll("f = $f , type = ${f.javaClass}")
    var f1 = 16 shr 4
    lll("f = $f1 , type = ${f1.javaClass}")
    var f2 = 32 ushr 4
    lll("f = $f2 , type = ${f2.javaClass}")

    lll("1/4 = ${1 / 4}")


}

private fun test等号() {
    val b = 123
//        lll(b === b) // 输出true
    val b2: Int? = b
    val b22: Int? = b
    lll(b2 === b22) // 输出true

    val a = 10000
//        lll(a === a) // 输出“true”
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    lll(boxedA === anotherBoxedA) // 输出false
}