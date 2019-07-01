package com.ryan.kotlinsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var username = null
    val TAG: String = "MainActivity"

    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_test.text = "测试View Binding!"


//        test等号()

//        testBitOperator()

//        testInterval()

//        testChar()

//        testArray()

//        testUnsignedInt()

        testString()

    }

    private fun testString() {
        "sbqyj".forEach {
            lll("it = $it")
        }
        for (i: Char in "sbqyj") {
            lll("i = $i")
        }

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
}

fun lll(target: Any?) {
    Log.e("MainActivity", "target  = $target")
}

fun printType(target: Any) {
    Log.e("MainActivity", "target type  = ${target.javaClass}")
}
