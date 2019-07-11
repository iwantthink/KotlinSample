package com

import android.util.Log

class TestImport {
    val name = 'j'

}

object Person {

    fun getName(): String {
        testImport()
        return "Jack"
    }

    val age = 18
}

fun testImport() {
    Log.e("TestImport", "测试import")
}

val name: String = "jack ma"

// 文件私有
private val gender = "male"