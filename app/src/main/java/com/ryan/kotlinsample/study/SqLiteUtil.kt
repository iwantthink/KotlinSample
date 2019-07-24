package com.ryan.kotlinsample.study

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.ryan.kotlinsample.pt
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.FutureTask
import kotlin.concurrent.thread

class SqLiteUtil {
    fun testHandler(x: Any): Unit {
        Handler().sendMessage(Message.obtain().also {
            it.what = 123
            it.obj = 123
        })

        val handler = Handler()

        handler.sendMessage(Message.obtain().apply {

        })

        handler.sendMessage(Message().also {

        })

        val callable: Callable<String> = Callable {
            Thread.sleep(5000)
            "result"
        }

        val futureTask = FutureTask<String>(callable)
        Thread(futureTask).start()

        futureTask.get()

    }
}

class MyAsyncTask : AsyncTask<Void, Int, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        pt("onPreExecute")
    }


    override fun doInBackground(vararg params: Void?): String {
        repeat(10) {
            Thread.sleep(2000)
            pt("doInBackground")
            publishProgress(it)
        }

        return "Final Result!"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        pt("onProgressUpdate")
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        pt("onPostExecute")
    }
}