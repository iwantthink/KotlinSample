package com.ryan.kotlinsample.study

import android.content.Context
import android.os.Environment
import java.io.File

class DiskLruCacheUtil {

    fun getDiskCacheDIr(context: Context, uniqueName: String): File {
        return when {
            Environment.MEDIA_MOUNTED ==
                    Environment.getExternalStorageState() ->
                File(context.externalCacheDir.path + File.separator + uniqueName)
            else -> {
                File(context.cacheDir.path + File.separator + uniqueName)
            }
        }
    }


}