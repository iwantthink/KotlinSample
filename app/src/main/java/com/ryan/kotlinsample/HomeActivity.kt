package com.ryan.kotlinsample

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.ContactsContract
import android.util.Log
import android.view.GestureDetector
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.ryan.kotlinsample.study.*
import io.reactivex.Observable
import io.reactivex.Observer
import kotlinx.android.synthetic.main.activity_home.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


fun pt(msg: String, tag: String = "TEST") {
    Log.e(tag, msg)
}


class HomeActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        pt("onNewIntent")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        pt("onCreate")
        btn_content.setOnClickListener { view ->
            val dialog = AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("message")
                .setPositiveButton("yes") { dialog: DialogInterface, which: Int ->

                }.setNegativeButton("no") { dialog: DialogInterface, which: Int ->

                }.create()
            dialog.show()
        }

        btn_acitivty.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // standard singleTop singleInstance singleTask
            applicationContext.startActivity(intent)
        }

        btn_service.setOnClickListener {
            //            val intent = Intent(this, ServiceLifecycle::class.java)
            //startService(intent)

//            bindService(intent, object : ServiceConnection {
//                override fun onServiceDisconnected(name: ComponentName?) {
//
//                }
//
//                 如果是跨进程 Service 需要使用AIDL
//                override fun onServiceConnected(
//                    name: ComponentName?,
//                    service: IBinder?
//                ) {
//
//                }
//
//            }, Service.BIND_AUTO_CREATE)

            val intent = Intent(this, IntentServiceSample::class.java)
            startService(intent)
        }

        btn_broadcast.setOnClickListener {
            sendBroadcast(Intent(this, MyReceiver::class.java))
        }

        btn_content_provider.setOnClickListener {
            //            getContact()
            getMyContentProvider()
        }

        myClock.setOnClickListener {
            //            viewAnimation(btn_content)
//            propertyAnimation(btn_content)
//            valueAnimator(btn_content,btn_content.height)
//            viewAnimation(btn_content)
//            propertyAnimation2(btn_content)
//            propertyAnimation3(btn_content)
            propertyAnimation5(btn_content)
        }

        btn_connect.setOnClickListener {
            val function = fun() {
                NetworkUtils.get(object : NetworkUtils.Companion.IRequest {
                    override fun getBaseUrl(): String {
                        return "https://www.baidu.com"
                    }

                    override fun getMethod(): String {
                        return "Get"
                    }

                    override fun getEncrypt(): NetworkUtils.Companion.IEncrypt {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun getParam(): HashMap<String, String> {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })
            }

            function.invoke()
        }

        TestRxjava().main(arrayOf(""))

    }

    lateinit var bitmap: Bitmap

    private fun setBitmap2View(view: View): Unit {
        // Bitmap内存占用 = 横向像素数量 * 纵向像素数量 * 单个像素的字节大小
        // Bitmap内存占用 ≈ 像素数据总大小 = 图片宽 × 图片高× (设备分辨率密度/资源目录分辨率密度)^2 × 每个像素的字节大小
//        view.background = BitmapFactory.decodeResource(
//            resources,
//            R.mipmap.ic_launcher
//        ).toDrawable(resources)

        bitmap = BitmapFactory.decodeResource(
            resources,
            R.mipmap.ic_launcher
        )

        pt("当前手机屏幕密度 = ${resources.displayMetrics.density}")
        pt("当前手机屏幕密度 = ${resources.displayMetrics.xdpi}")
        pt("当前手机屏幕密度 = ${resources.displayMetrics.ydpi}")
    }


    private fun getMyContentProvider() {
        val cursor = contentResolver.query(
            Uri.parse("content://com.ryan.provider/user"),
            arrayOf("_id", "name"),
            null,
            null,
            null
        )

        val result = cursor?.columnCount ?: 0
        pt("result = $result")
        if (result > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                pt("id = $id , name = $name")
            }
        }

        cursor?.close()

        val values: ContentValues = ContentValues()
        values.put("_id", 56)
        values.put("name", "Tiger")
        contentResolver.insert(
            Uri.parse("content://com.ryan.provider/user"),
            values
        )

    }

    private fun getContact() {
        val uri = ContactsContract.Contacts.CONTENT_URI
        pt("uri = $uri")
        pt("type = ${contentResolver.getType(uri)}")
        val projection = arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        pt("cursor?.count = ${cursor?.count}")
    }

    override fun onStart() {
        super.onStart()
        pt("onStart")
    }

    override fun onResume() {
        super.onResume()
        pt("onResume")
    }

    override fun onPause() {
        super.onPause()
        pt("onPause")
    }

    override fun onStop() {
        super.onStop()
        pt("onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        pt("onDestroy")

    }

    override fun onRestart() {
        super.onRestart()
        pt("onRestart")
    }

    override fun onSaveInstanceState(
        outState: Bundle?,
        outPersistentState: PersistableBundle?
    ) {
        super.onSaveInstanceState(outState, outPersistentState)
        pt("onSaveInstanceState 2")
    }

    /**
     * 屏幕旋转,配置发生变化后会调用
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        pt("onSaveInstanceState")
        outState?.putString("ryan", "帅哥")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        pt("onRestoreInstanceState")
        val name: String? = savedInstanceState?.getString("ryan")
        if (name != null) btn_content.text = name
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        pt("onRestoreInstanceState 2")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        pt("onCreateOptionMenu")
        return super.onCreateOptionsMenu(menu)
    }

}


