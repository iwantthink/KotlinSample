package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun mainCoroutine(): Unit {
//    runBlocking {
//        // 不会阻塞main
//        coroutineScope {
//            delay(20000)
//            lll("wait 20 s !!!!!")
//        }
//        lll("coroutineScope 执行结束")
//    }
//    lll("runBlocking 执行结束")


//    GlobalScope.launch(Dispatchers.Main) {
//        launch {
//            // 运行在父协程的上下文中，即 runBlocking 主协程
//            lll("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
//        }
//        launch(Dispatchers.Unconfined) {
//            // 不受限的——将工作在主线程中
//            lll("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//        }
//        launch(Dispatchers.Default) {
//            // 将会获取默认调度器
//            lll("Default               : I'm working in thread ${Thread.currentThread().name}")
//        }
//        launch(context = newSingleThreadContext("MyOwnThread")) {
//            // 将使它获得一个新的线程
//            lll("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//        }
//    }

}

class TestCoroutine : CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}

val mainScope = MainScope()

fun mainCoroutine1() = runBlocking {
    //    val channel = Channel<Int>()
//    launch {
//        // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
//        for (x in 1..5) {
//            delay(2000)
//            channel.send(x * x)
//            if (x == 3) {
//                channel.close()
//                break
//            }
//        }
////        channel.close()
//    }
//// 这里我们打印了 5 次被接收的整数：
//    try {
//        repeat(5) { lll(channel.receive()) }
//    } catch (e: Exception) {
//        lll("捕获到一个异常")
//    }
//
//
////    for (i in channel) {
////        lll("receive = $i")
////    }
//
//    lll("Done!")

//    val nums = generateNum()
//    val squares = square(nums)
//    for (square in squares) {
//        delay(12000)
//        lll("square = $square")
//    }
//    lll("结束")
//    coroutineContext.cancelChildren()

//    launch {
//        delay(2222)
//        lll("执行结束")
//    }
//    lll("main 执行结束")

    GlobalScope.launch {

    }

    launch(context = CoroutineName("")) {  }

    repeat(10) {
        launch(Dispatchers.Default) {
            // 将使它获得一个新的线程
            lll("working in thread ${Thread.currentThread().name}")
        }
    }

}


fun CoroutineScope.generateNum(): ReceiveChannel<Int> = produce(capacity = 10) {
    var x = 1
    while (true) {
        lll("生产 x = $x")
        send(x++)
    }
}

fun CoroutineScope.square(numbs: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {

    for (numb in numbs) {
        lll("生产 numb = $numb")
        send(numb * numb)
    }
}