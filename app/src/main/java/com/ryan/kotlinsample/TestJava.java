package com.ryan.kotlinsample;

import java.lang.reflect.Constructor;

public class TestJava<T> {

    public <E, T> void getName(E e, T t) {
    }


    public String name;

    public String getType() {
        return "type";
    }

    public void setType(String type) {

    }

    public boolean isSb(String sb) {
        return false;
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


        final int a = 123;

        final String b = "";

        final TestJava tj = new TestJava();
        tj.name = "123";

        return null;
    }
}