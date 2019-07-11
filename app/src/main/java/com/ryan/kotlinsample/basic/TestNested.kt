package com.ryan.kotlinsample.basic

import android.drm.DrmStore

class TestNested {
    val xyz = 1

    // 没有对外部类的隐式引用
    class InnerTestNested {
        fun f() {}
    }

    // 拥有对外部类的隐式引用
    inner class InnerTestNested2 {
        fun f() {
            xyz
        }
    }
}

interface Action1 {
    fun foo()
}

fun foo2(action1: Action1) {

}
