package com.ryan.kotlinsample.study

import android.app.IntentService
import android.content.Intent
import com.ryan.kotlinsample.pt

class IntentServiceSample : IntentService("TestIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        pt("处理Intent,线程 = ${Thread.currentThread().name}")
    }

}