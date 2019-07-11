package com.ryan.kotlinsample.basic

import android.annotation.TargetApi
import com.ryan.kotlinsample.lll


// 属性必须初始化
// 或者提供get set
// val 和var 类似，只是前者只是可读，所以只用提供get
var name: String? = null

var name2
    get() = "123"
    set(value) {
    }

var name3 = 123
    get() = 11
    set(value) {
        // 如果使用了field  那么必须进行初始化
        field = 123
        name3 = value
    }


class Jack {

    var name: String = "sb"
        set(value) {
            lll("set value = $value")
            // 不要写如下的代码，会造成死循环
//            name = value
            // 幕后字段 field
            // field只能在属性访问器中使用
            field = value
        }
    // 如果在初始化时赋值，那只不会再改变了
    // 如果需要实时计算值，那需要通过get
    var nameLth: Int = name.length

    // 属性声明为非空则必须在构造函数中进行初始化
    var age: Int

    var age2: Int? = null

    constructor(age: Int) {
        this.age = age
    }


    var job: String = "jack"
        private set
        @TargetApi(1) get


    var num: Int = -1
        get() = 123
        set(value) {
            field = 123
            //save
        }

    object A {
        const val x = 'a'
    }

    fun f() = object {

    }

    lateinit var lastName: String

    fun getLN(): String {
        // 判断lateinit 是否被初始化过
        ::lastName.isInitialized
        return lastName
    }

}

open class A4 {
    open var name: String = "ryan"
}

class A3 : A4 {

    constructor() : super() {

    }
}