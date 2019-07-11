package com.ryan.kotlinsample.basic

import com.ryan.internaltest.SubTestInternal
import com.ryan.internaltest.TestInternal
import com.ryan.internaltest.Zoo
import com.ryan.internaltest.f


fun test() {
    // 通过引入库模块，可以使用其中的public 方法函数等。。
    Zoo()
    f()
    val subClass = SubTestInternal()
    subClass.main()
    val testInternal = TestInternal()
    //不允许访问，因为是internal的,只能在它所在的模块访问
//    testInternal.lastName

    // private internal 肯定是不允许的
//    Zoo1()
//    Zoo2()
}
