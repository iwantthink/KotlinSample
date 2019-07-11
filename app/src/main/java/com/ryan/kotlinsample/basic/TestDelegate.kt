package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


interface Creature {
    fun say()

    fun run()
}

class FakeDog : Creature {
    override fun say() {
        lll("假狗叫")
    }

    override fun run() {
        lll("假狗跑")
    }

}

class Dog : Creature {

    override fun say() {
        lll("狗汪!")
        run()
    }

    override fun run() {
        lll("狗跑!")
    }
}

class SilentDog constructor(d: Dog) : Creature by d {

    /**
     * 委托对象无法调用到这个重写的run()方法
     * 它能调用的还是那个委托类自身的run()方法
     */
    override fun run() {
        lll("哑巴狗跑!")
    }
}


class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "获取了来自Delegate的值"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        lll("设置来自${property.name} 的值 $value")
    }

}


val lazyValue: String by lazy {
    lll("初始化延迟数据")
    "hello"
}

val lazyValue1: String = lazy {
    lll("初始化延迟数据1")
    "hello1"
}.toString()


var observableStr: String by Delegates.observable("") { prop, old, new ->

    lll("p = ${prop.name}")
    lll("new = $new")
    lll("old = $old")

}


var vetoable: String by Delegates.vetoable("init value") { property, oldValue, newValue ->
    return@vetoable newValue.contains("aaa")
}

class User(map: MutableMap<String, Any>) {
    // User 的实例的name属性会从 构造函数传入的map 中获取name
    val name: String by map
    val age: Int by map
    var gender: Boolean by map
}

fun foo10() {
    val sd = SilentDog(Dog())
    sd.say()
    sd.run()

    var x: String by Delegate()

    lll("x = $x")
    x = "dd"

    lll("lazyValue = $lazyValue")
    lll("lazyValue1 = $lazyValue1")

    lll("lazyValue = $lazyValue")
    lll("lazyValue1 = $lazyValue1")

    lll("observableStr = $observableStr")
    observableStr = "1"
    observableStr = "2"
    observableStr = "3"

    vetoable = "1"
    lll("vetoable = $vetoable")
    vetoable = "1"
    lll("vetoable = $vetoable")
    vetoable = "aaa1"
    lll("vetoable = $vetoable")
    vetoable = "aaab"
    lll("vetoable = $vetoable")
    vetoable = "aaac"
    lll("vetoable = $vetoable")


    val map = mutableMapOf<String, Any>(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "name" to "name",
        "age" to 1243

    )
    val user = User(map)
    lll("user = ${user.name}")
    lll("user = ${user.age}")

}