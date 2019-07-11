package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

class Invoice {

    val name: String = "Ryan"
}

class Invoice2


class Student private constructor(name: String)

//constructor 如果没有注解或者可见性修饰符，则可以被省略,否则不能被省略
class Stu1(name: String)

// 默认主构造函数为public
class Stu2 constructor(name: String, age: Int, val gender: Boolean) {

    val mFirstProperty: String = "First property : $age".also(::lll)

    val mSeconedProperty: String = "Seconed property: ${mFirstProperty.length}".also(::lll)

    val mName: String

    // 主构造函数的参数可以在初始化块中使用
    init {
        lll("Stu2 init block")
        mName = name
    }

    init {
        lll("Stu2 init block 2")
        lll("mName = $mName")
    }

    // 默认的Kotlin 会提供get 和 set
    fun getGender2(): Boolean {
        return gender
    }

    // 如果类存在主构造函数，那么每个次构造函数必须委托给主构造函数
    // 这个可以通过this 来实现（直接委托或者间接委托都可以)
    constructor(name: String, age: Int, gender: Boolean, style: String) :
            this(name, age, gender) {
        lll("style = $style")
    }

}

class Banker constructor() {

    // 初始化块中的代码会作为主构造函数的一部分
    init {
        lll("初始化代码块")
    }

    // 次构造函数 在调用主构造函数之后，主构造函数会优先执行!!
    constructor(name: String, job: String) : this() {

        lll("次构造函数")
    }
}

class Banker1 {

    init {
        // 先执行
        lll("初始化代码块")
    }

    // 即使未定义主构造函数,委托仍然会隐式发生，初始化代码块仍然会被调用
    constructor() {
        // 后执行
        lll("次构造函数")
    }
}

// 如果主构造函数的所有参数都有默认值，那么编译器会额外生成一个无参构造函数，其参数使用默认值
class Banker2 constructor(val name: String = "Ryan") {

    class Manager constructor(val name: String)

}

// 对于没有显示声明父类型的类，默认继承自Any
open class Person constructor(name: String) {
    constructor() : this("ryan")
}

// 如果父类型有一个主构造函数，那么子类必须实现
class Ryan : Person("Ryan")

open class Person2 {
    constructor(name: String)
}

// 简而言之 如果继承了父类，那么其构造函数必须实现 this 或者 super去调用同级或父级的构造函数
class Ryan2 : Person2 {
    constructor() : super("Ryan")

    constructor(age: Int) : this()

    constructor(life: String) : this(123)
}


open class Painter(open var age: Int) {

    constructor(name: String) : this(1)

    fun draw() {
        lll("Painter draw")
    }

    // 对于可覆盖的成员，必须显示声明open，否则子类无法覆盖
    open fun fuck() {
        lll("Painter fuck")
    }


    open fun say() {

    }
}

// 可以将对父类的实现交给子构造函数完成
open class FakePainter : Painter {


    open val gender: String = "male"
    open var sex: String = ""

    constructor() : super("ryan")

    // 覆盖子父类的函数必须显示声明override
    // override 的函数 本身也是开放的,子类可以覆盖
    override fun fuck() {
        super.fuck()
        lll("FakePainter fuck")
    }

    // 被final修饰后，子类无法覆盖,
    final override fun say() {
        super.say()
    }
}

class SubFakePainter : FakePainter {

    override var age: Int
        get() = super.age
        set(value) {}


    // 可以通过 get set 进行设置
    // 或者通过初始化器进行设置
    override var gender: String
        get() = "real-male"
        set(value) {
            // 子类可以使用var覆盖父类的val 属性
        }

    override var sex: String
        get() = super.sex
        set(value) {
            super.sex = value
        }

    constructor() : super()

    override fun fuck() {
        super.fuck()
    }


//    override fun say() {
//        super.say()
//    }
}

open class Base(val name: String) {

    init {
        lll("基类init")
    }

    open val size: Int = name.length.also {
        lll("基类size属性")
    }

}

// 如果子类覆盖了父类中的属性或者方法
// 那么其会在 父类的 构造函数或 init  之后 才会被真正的重写
// 如果这时使用 open成员，就可能出现错误
class SubClass(name: String, val lastName: String) : Base(name.also { lll("子类调用父类主构造函数") }) {

    init {
        lll("子类init")
    }

    override val size: Int
        get() = super.size.also { lll("子类size属性") }

}

open class Foo {
    open fun f() {
        lll("Foo.f")
    }

    open val x: Int
        get() = 123
}

class Bar : Foo() {

    override fun f() {
        super.f()
        lll("Bar.f")
    }

    inner class Baz {
        fun g() {
            f()
            lll("-------")
            // 在内部类访问外部类的超类
            super@Bar.f()
            super@Bar.x
        }
    }
}


open class A {
    open fun f() {
        lll("A  f")
    }

    open fun a() {
        lll("A  a")
    }
}

// 接口默认中的属性和方法默认都是open
interface B {
    fun f() {
        lll("B f")
    }

    fun b() {
        lll("B b")
    }
}

class C : A(), B {

    // 编译器会强制子类实现冲突的方法
    // 对于存在多个实现的 父类方法，必须指定方法的父类，才能进行调用
    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}


open class Ma {
    open fun m() {}
}

abstract class Ren : Ma() {
    abstract override fun m()

    open fun g() {
        lll("输出g")
    }

    fun p() {
        // 对象表达式
        val m = object : Ma(), B {

            override fun m() {
                super.m()
            }
        }

        lll("私有的p")
    }
}


class Bo constructor() {

    // 对象声明 无法用在内部类中或者局部作用域
    inner class BBB {
//        object InnerObj{
//
//        }
    }

    // 对象声明,无法用作赋值语句右边!!!!
    // 对象声明可以拥有父类型
    object ObjectTest {
        val objecTest = 1
    }

    // 一个类中只允许存在一个companion
    // companion 允许继承
    companion object : Ma() {

        override fun m() {
            super.m()
            lll("覆盖父类方法")
        }

        val y = 123
        val companionYYYY = 123123
    }


    // 私有函数，返回类型是匿名对象类型
    private fun f() = object {
        val x = 1
    }

    // 公开函数，返回类型是Any，所以无法访问
    fun f1() = object {
        val x = 2
    }

    fun sb() {
        val s1 = f()
        // 允许访问
        s1.x
        lll("s1 = ${s1.javaClass}")
        val s2 = f1()
        // 无法解析
//        s2.x
        lll("s2 = ${s2.javaClass}")

        companionYYYY

        m()
    }
}



