package com.ryan.kotlinsample.study

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ryan.kotlinsample.pt

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val name = intent.getStringExtra("ryan")
        pt("OnReceive name = $name")

    }
}
