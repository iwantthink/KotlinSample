package com.ryan.kotlinsample.review

import android.view.View
import com.ryan.kotlinsample.lll

object TestImport2 {
    val name: String = "jack"
}

fun main() {
    val x = 123
    when (x) {
        1 -> lll("111")
        isRight() -> lll("确认是right")
        else -> lll("else")
    }

    val x1 = when {
        true -> 4444
        else -> {
            55555
        }
    }

    when (val reuslt = executePost()) {
        200 -> lll("请求成功")
        250 -> lll("请求失败")
        else -> {
            lll("其他类型")
        }

    }

    // 通过downTo 可以指定最小值,step指定间隔
    for (i in 10 downTo 0 step 2) {

    }

    for (withIndex in intArrayOf(1, 2, 3).withIndex()) {
        withIndex.index
        withIndex.value
    }

    label@ for (i in 1..20) {
        for (j in 21..40) {
            if (j == 22) {
                continue@label
            }
            lll("i = $i , j = $j")
        }
    }

    fun MutableList<String>.swap() {
        // this 指向接收者对象
        this.size
    }

    val ml = MutableList<Int>(10) {
        return
    }

//    m1.swap()
    val m2 = MutableList<String>(111) {
        it.toString()
    }

    m2.swap()

    val enum = enumValueOf<Direction>("NORTH")
    val enum1 = enumValues<Direction>()


}

data class Person4(val name: String) {

    var age: Int = 123
}


fun isRight(): Int {
    return 4
}

fun executePost(): Int {
    return 250
}

class LittleElephant : Elephant() {

    object QQQ {
        val name = "string"
    }

}

open class Elephant {

    lateinit var size: String

    val isInit = ::size.isInitialized

    constructor(name: String) {
        LittleElephant.QQQ.name
    }

    constructor()
}


interface Animal {
    val species: String
        get() = ""

    fun run()

    fun breath() {

    }
}

open class Snake() : Animal {

    protected open fun slide() {
        lll("滑行")
    }

    override fun run() {

    }

    override val species: String
        get() = super.species
}

open class BabySnake : Snake {
    constructor() {}

    override fun slide() {
        super.slide()
    }

    internal fun fuck() {

    }

}


class BabyMaleSnake : BabySnake() {

    override fun breath() {
        super.breath()
        slide()

    }
}


sealed class TestSealed {

}

class SubSealed1 : TestSealed() {

}


// ? extends T
class Panda<out T> {

}

open class T1 {

}

open class T2 : T1() {

}

class T3 : T2()


fun mmm() {
    val pd1 = Panda<T2>()
    val pd2: Panda<T1> = pd1


    val gn1 = E1<T1>()
    val gn2 = E1<T2>()
    val gn3 = E1<T3>()
    gn1.getName(gn1)
    gn1.getName(gn2)
    gn1.getName(gn3)
    gn1.getName(E1<String>())

}

class E1<in T> {

    fun getName(str: E1<*>) {

    }

    // 与泛型* 相比，前者表示可以接收任何类型，后者表示元素只能是一种类型
    fun getName1(str: E1<Any?>) {
    }
}


fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String> where T : CharSequence,
                                                                         T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}


interface Creature {
    fun eat(): Unit
}

class OuterDog {


    val sb2 = View.OnClickListener {

    }
    val sb = object : InnerDog() {
        override fun main() {
            super.main()
            lll("")
        }
    }

    val name: String = "OuterDog"

    fun bark() {}

    open inner class InnerDog {
        val name: String = "InnerDob"

        open fun main(): Unit {
            bark()
            this@OuterDog.name
        }

    }
}

enum class Direction(val d: String) {
    NORTH(""), SOUTH(""), EAST(""), WEST("")
}