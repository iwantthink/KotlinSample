package com.ryan.kotlinsample;

import java.lang.reflect.Constructor;

public class TestJava<T> {

    public <E, T> void getName(E e, T t) {
    }
}


interface A<T> {

    T getName();
}

class B implements A<String> {

    @Override
    public String getName() {

        TestJava testJava = new TestJava();
        testJava.getName("", "");




        return null;
    }
}