package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

open class Zombie {

}

class SmallZombie : Zombie() {

}

fun Zombie.foo() {
    lll("Zombie 的 foo")
}

fun SmallZombie.foo() {
    lll("SmallZombie 的 foo")
}

fun zombieTest(z: Zombie, sz: SmallZombie) {
    z.foo()
    sz.foo()
}

class WalkingDead {

    companion object {
        val gender: Boolean = false
    }

    fun foo() {
        lll("walking dead!")
    }
}

class FirstC() {
    fun fooF() {
        lll("FirstC  foo!!!")
    }

    fun foo() {

    }
}

open class SeconedC {
    fun SeconedC.saySb() {
        foo111()
    }

    fun foo() {

    }

    private fun foo111() {}


    fun fooS() {
        lll("SeconedC foo!!!")
    }

    // SeconedC 会被称为分发接收者
    open fun FirstC.foo() {
        fooS()
        fooF()
        // 如果分发接收者 和扩展接收者 出现冲突，那么优先取扩展分发者
        foo()
        // 但是可以借助this 限定符来调用 分发接收者
        this@SeconedC.foo()

        lll("SeconedC extension foo!!!")
    }

    open fun FirstC.sayHello() {
        lll("extension from seconed c")
    }


    fun call(d: FirstC) {
        // d 就被称为扩展接收者
        d.sayHello()
    }
}

class ThirdC : SeconedC() {
    override fun FirstC.sayHello() {
        lll("extension from third c")
    }
}

fun SeconedC.sayHi() {
    this.foo()
    // 无法访问private的
//    this.foo111()
}