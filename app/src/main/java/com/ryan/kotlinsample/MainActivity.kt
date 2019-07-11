package com.ryan.kotlinsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ryan.internaltest.TestInternal
import com.ryan.internaltest.Zoo
import com.ryan.internaltest.f
import com.ryan.kotlinsample.basic.*
import com.ryan.kotlinsample.review.TestSealed
import kotlinx.android.synthetic.main.activity_main.*
import com.ryan.kotlinsample.review.TestImport2.name as ti2name

// 扩展属性不能拥有初始化器
val WalkingDead.size: Int
    get() = 123

// 伴生对象也可以扩展属性或函数
var WalkingDead.Companion.height: Int
    get() = 123
    set(value) {}

fun WalkingDead.Companion.sayHi() {
    lll("扩展 添加的 问候!")
}

class MainActivity : AppCompatActivity() {

    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_test.text = "测试View Binding!"

        tv_test.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                tv_test.setText("傻逼")
            }
        })

//        testExtension()

//        testClass()

//        testData()

//        testGenerics()

//        testEnum()

//        testObject()

//        testInline()

//        foo10()

//        testFun()
//        com.ryan.kotlinsample.review.main(123)
//        testCollectionMain()
        com.ryan.kotlinsample.basic.main()
    }

    private fun testFun() {
        // 参数默认会按照顺序赋值
        // 所以如果拥有默认值的参数在一个没有默认值的参数后,那么需要通过命名参数
        sayHi(tag = "tag")
        sayHi("123", tag = "")
        // 在位置参数和命名参数混合使用时，必须优先传入位置参数
//        sayHi1(msg = "", "")
//        sayHi1(msg = "", "")
        Piggy().eat()
        // 参数如果是函数，那么可以需要将函数参数单独放到圆括号外
        Piggy().foo({ lll("小猪配齐") })
        Piggy().foo { lll("我是小猪佩丘") }
        Piggy().foo(haha = { lll("我是大猪佩丘") })

        // 可以通过 伸展操作符(*)将数组转换成可变参数
        sayHi2(*arrayOf("1", "2", "3"), age = 1)
        sayHi2("1", "2", "3", age = 123)

        // 中缀表示法
        Piggy() run false

        testFold()

        Pig9().invoke()

        repeatFun("", 3)
    }

    private fun testInline() {
        foo9()
    }

    private fun testObject() {
        foo6()
    }

    private fun testEnum() {
        foo4()
    }

    private fun testGenerics() {
        // 如果填写了 变量的类型，那么必须声明泛型的类型
        // 如果可以推测出变量的类型，那么允许不加上 类型
        val b1: Box<Int> = Box(1)

        val s1: Source<String> = object : Source<String> {

        }

        val s2: Source<Any> = s1


        val a1 = Array(5) {
            if (it.div(2) == 0) {
                return@Array 123
            } else {
                return@Array false
            }
        }

        val a3: Array<Int> = arrayOf(1, 2, 3)
        var a2 = Array<Any>(3) {}
        // 这里主要的问题就是不型变！！！ Array<Any>与Array<Int>不相等
        // a3 这里的参数 copy1函数期待这个参数时 Array<Any>
        // 但是实际获取是 Array<Int>
//        copy(a3, a2)

        // a4 类型为 Array<String>
        val a4 = arrayOf("2", "3", "4")
        // 不型变 导致错误!
//        copy(a4, a2)
        // 但是这里 类型为Array<Any>
        copy(arrayOf("2", "#", "4"), a2)
        copy(arrayOf(1, 1, 2), a2)
        copy(arrayOf(true, true, true), a2)


        // 这里的arrayOf返回的是泛型为Any
        copy(arrayOf(1, 3, 4), a2)

        a2.forEach {
            lll("it = $it")
        }

        val cup = Cup<String>()

        val subCupA = CupA()
//        val a = AAAAA<CupA>()
        val b = AAAAA<SubCupA>()
        val c = AAAAA<SubSubCupA>()
//        a.foo(subCupA)


    }


    private fun testClass() {
//        val stu2 = Stu2("Ryan", 123, false)
//        val stu3 = Stu2("ryan", 123, false, "style")

//        val banker = Banker("ryan", "manager")
//        val banker1 = Banker1()
//        val banker2 = Banker2()
//        banker2.name
//
//        val ryan = Ryan()
//        var temp = ryan.javaClass.superclass
//        while (temp != null) {
//            lll("temp = $temp")
//            temp = temp.superclass
//
//        }

//        val fakePainter = FakePainter()
//        fakePainter.fuck()
//        fakePainter.draw()

//        val sub = SubClass("ryan", "ma")

//        val baz = Bar().Baz()
//        baz.g()

//        val b = Bo()
//        b.sb()
//
//        Bo.ObjectTest.objecTest
//
//        lll("Bo.companionYYYY = ${Bo.companionYYYY}")

//        testProperty()

//        testScope()

//        testExtension()

    }

    private fun testData() {
        val td = TestData("ryan", 123)
        lll("td.hash = ${td.hashCode()}")
        lll("td.tostring = $td")

        val td1 = TestData1()
        val td2 = TestData2()
        lll("td2.hashcode = ${td2.hashCode()}")
        lll("td2.tostring = $td2")

        val td3 = td2.copy(name = "111")
        lll("td3 = $td3")


    }

    private fun testExtension() {
        // 使用fun 可以扩展一个类
        // 但是扩展并不是真正的修改类！！！！
        // 只是通过点表达式去调用这个 被添加的函数
        fun TestInternal.addNewMethod() {
            lll("给TestInternal添加的一个新方法")
            // this 就是指代 调用该方法的对象！
            this.age = 555
        }


        val z = Zombie()
        val sz = SmallZombie()
        //调用的扩展函数只取决于zombieTest的参数 的声明类型
        zombieTest(sz, sz)
        zombieTest(z, sz)

        // 局部的扩展函数是可以的
        // 但是只能在定义的范围内使用，不能超过
        fun SmallZombie.foo() {
            lll("extension foo say hi")
        }

        val wd = WalkingDead()
        wd.foo()
        // 扩展已经存在的拥有相同参数的函数没有效果！
        // 参数不同 可以！
        fun WalkingDead.foo() {
            lll("extension from walking dead")
        }

        wd.foo()

        // 可以为可能为空的对象设置扩展
        fun WalkingDead?.walk() {
            lll("this == null? = ${this == null}")
            lll("walk walk walk!")
        }

        val emptyWd: WalkingDead? = null
        emptyWd.walk()

        // 可以为空对象也设置扩展函数
        fun Any?.foo() {

        }

        val h: Int? = null

        h.foo()

        // 局部扩展属性是不允许的
//        val WalkingDead.size:Int

        // 调用伴生对象 通过扩展添加的函数
        WalkingDead.sayHi()
        // 调用伴生对象 通过扩展添加的属性
        WalkingDead.height
        // 调用伴生对象 原来就存在的属性
        WalkingDead.gender

        lll("-----")
        val c = FirstC()
        val callC = SeconedC()
        callC.call(c)
        val callC2 = ThirdC()
        callC2.call(c)

    }

    private fun testScope() {
        // 通过引入库模块，可以使用其中的public 方法函数等。。
        Zoo()
        f()

        // private internal 肯定是不允许的
//    Zoo1()
//    Zoo2()

    }

    private fun testProperty() {
        lll("name3 = ${name3}")

        var j = Jack(1)
//        lll("j = ${j.name}")
//        lll("j = ${j.nameLth}")
//        j.name = "sjba"
//        lll("j = ${j.name}")
//        lll("j = ${j.nameLth}")

//        val lastName = j.getLN()
//        lll("lastName = $lastName")


    }


    fun testBasic() {
        //        test等号()

//        testBitOperator()

//        testInterval()

//        testChar()

//        testArray()

//        testUnsignedInt()

//        testString()

//        testObjectDeclare()

//        testUseImport()

//        testIf()

//        testWhen(1)

//        testFor()

//        testWhile(123)

//        testReturn()

//        testBreak()

//        testContinue()

//        testLabel()

//        testNestedReturn()
    }

}

fun lll(target: Any?) {
    Log.e("MainActivity", "$target")
}

fun printType(target: Any) {
    Log.e("MainActivity", "target type  = ${target.javaClass}")
}
