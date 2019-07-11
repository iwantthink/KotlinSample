package com.ryan.kotlinsample.basic

sealed class TestSealed {
    abstract fun foo()
}

class SubSealed : TestSealed() {
    override fun foo() {

    }

}