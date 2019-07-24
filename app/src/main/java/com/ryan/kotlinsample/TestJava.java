package com.ryan.kotlinsample;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import com.ryan.kotlinsample.basic.CCCC;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;

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

    public static String getAndroidId(Context context) {
        try {
            String androidid = Settings.Secure.getString(
                    context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (androidid == null) {
                androidid = "";
            }
            return androidid;
        } catch (NullPointerException e) {
        }
        return "";
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

        CCCC c = new CCCC();
        CCCC.Companion.callNonStatic();
        CCCC.callStatic();

        Handler handler = new Handler();

        Observable<Long> observable =

                Observable.defer((Callable<ObservableSource<Long>>)
                        () -> Observable.just(System.currentTimeMillis()));

        Observable.empty().subscribe(i -> System.out.print("next"),
                i -> System.out.print("error"),
                () -> System.out.print("complete"));

        return null;
    }
}