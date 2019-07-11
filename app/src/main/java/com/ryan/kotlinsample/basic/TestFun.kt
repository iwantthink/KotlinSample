package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

fun sayHi(msg: String = "hello kotlin!", tag: String) {

}

fun sayHi1(msg: String, tag: String = "Taggg") {}

// 只有一个参数可以被声明为可变参数
// 如果可变参数后有一个位置参数，那么需要以命名参数语法来调用
fun sayHi2(vararg msg: String, age: Int) {
    msg.forEach {
        lll("it = $it")
    }

    // 如果返回值为Unit，可以省略
    return
}

// 函数体可以使用表达式替代
// 当编译器能够自动推断出表达式类型时，不用声明返回值类型
fun sayHi3(): Int = 1 * 2


open class Pig {

    open fun eat(food: String = "cabbage") {
        lll("pig eat $food")
    }
}

class Piggy : Pig() {

    // 重写带有默认值参数的函数时，其参数不能拥有默认值
    override fun eat(food: String) {
        super.eat(food)
        lll("piggy eat! $food")
    }

    fun foo(haha: () -> Unit): Unit {
        haha()

        run(false)
        // 在类内部使用中缀表示法调用 函数时，必须指定this,这是为了避免混淆
        this run false

        val x = 123
        fun innerFoo() {
            lll("函数可以在另外一个函数内部声明")
            // 内部函数可以调用外部函数中的局部变量
            x

            eat()
        }

        innerFoo()

    }
}

// 必须得是成员函数 或者是扩展函数
// 必须且只能接受一个参数
// 参数不能是可变参数，并且不允许拥有默认值
infix fun Piggy.run(fast: Boolean) {
    lll("快跑啊!!!!杀猪了!!!")
}

fun <T, R> Collection<T>.fold(
    initial: R,
    // nextElement 表示当前集合中的元素
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun testFold(): Unit {
    val result = "123".fold(123) { acc, nextElement ->
        acc.toString() + "" + nextElement
        123
    }

    lll("result = $result")
}

var pig1: (a: Int) -> String = fun(a: Int): String {
    return "123"
}

var pig2: String.(Boolean) -> Int = {

    123
}

// 可以通过? 指定该函数类型的变量，但是必须得将函数类型 放入 圆括号中
var pig3: (() -> Unit)? = null

// 默认的箭头表示法是 右结合!!!
// 即 下面的代码和 pig6 中的相同
var pig4: () -> (() -> Unit) = {

    //    fun(): String {
//    }

    {
    }
}

var pig6: () -> () -> Unit = { {} }

var pig5: (Int) -> (Int) -> Unit = {
    {

        val x = 123
        // 不支持获取局部属性的引用
//        ::x
    }
}

//可以使用类型别名对函数进行重命名
typealias DoulbeFun = () -> Unit


// Lambda表达式 和 匿名函数都可以 对函数进行实例化
var pig7: () -> Unit = fun() {

}

var pig8: () -> Unit = {
    // 如果获取的函数引用 所指向的函数 拥有重载函数
    // 那么可以通过提供上下文 来指定获取的函数！！！！！
    ::Ryan
}

// 函数类型可以作为类的父类

class Pig9 : () -> Unit {
    override fun invoke() {
        lll("invoke ")
        pig10()
    }

}

// 函数类型可以有一个额外的接收者类型
fun pig10(): Pig9.(Int) -> Unit = { input ->

    invoke()

    lll("input =$input")
    String().pig2(false)
    String().repeatFun(123)
}

// 有点像给 String对象添加了一个扩展函数
// String对象变成了 隐式引用
val repeatFun: String.(Int) -> String = { times: Int -> this.repeat(times) }
val twoParameters: (String, Int) -> String = repeatFun // OK

fun runTransformation(f: (String, Int) -> String): String {
    repeatFun.invoke("", 123)
    repeatFun("", 1)
    return f("hello", 3)
}

val result = runTransformation(repeatFun) // OK