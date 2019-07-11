package com.ryan.kotlinsample.basic

interface Base1 {
    fun print()
}

class BaseImpl(val x: Int) : Base1 {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base1) : Base1 by b

class SingleTest() : Base1 by BaseImpl(1) {

}

