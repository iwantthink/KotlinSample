package com.ryan.kotlinsample

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(args: Array<String>): Unit {
    val job = GlobalScope.launch {
        delay(2222)
        println("等待2222")
    }
    println("执行结束")
}