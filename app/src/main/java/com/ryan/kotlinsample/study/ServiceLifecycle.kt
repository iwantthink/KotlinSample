package com.ryan.kotlinsample.study

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.ryan.kotlinsample.pt

class ServiceLifecycle : Service() {

    override fun onCreate() {
        super.onCreate()
        pt("onCreate", "ServiceLifecycle")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        pt("onStartCommand", "ServiceLifecycle")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        pt("onStart", "ServiceLifecycle")

    }

    override fun onBind(intent: Intent?): IBinder? {
        pt("onBind", "ServiceLifecycle")

        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        pt("onUnbind", "ServiceLifecycle")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        pt("onDestroy", "ServiceLifecycle")

    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        pt("onRebind", "ServiceLifecycle")
    }

}

interface MyIBinder {

    fun getName(): String
}

class MyBinder : Binder(), MyIBinder {

    override fun getName(): String {
        return "ryan"
    }
}