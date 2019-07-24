package com.ryan.kotlinsample.study

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.ryan.kotlinsample.pt

class MyContentProvider : ContentProvider() {

    lateinit var mDBHelper: DBHelper

    lateinit var mDB: SQLiteDatabase


    companion object {

        private const val AUTHORITY = "com.ryan.provider"

        private const val USER_CODE = 1

        private const val JOB_CODE = 2

        private val mUriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            // uri = content://com.ryan.provider/user 则返回1
            mUriMatcher.addURI(AUTHORITY, "user", USER_CODE)
            mUriMatcher.addURI(AUTHORITY, "job", JOB_CODE)
        }
    }

    override fun onCreate(): Boolean {
        mDBHelper = DBHelper(context)
        mDB = mDBHelper.writableDatabase

        mDB.execSQL("delete from user")
        mDB.execSQL("insert into user values(1,'Carson')")
        mDB.execSQL("insert into user values(2,'Jack')")

        mDB.execSQL("delete from job")
        mDB.execSQL("insert into job values(1,'Android')")
        mDB.execSQL("insert into job values(2,'iOS')")

        return true
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val tableName = getTableName(uri)
        if (tableName != "null") {
            mDB.insert(tableName, null, values)
            context.contentResolver.notifyChange(uri, null)
        }
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val tableName = getTableName(uri)
        if (tableName != "null") {
            return mDB.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null)
        }
        return null
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {

        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }


    private fun getTableName(uri: Uri): String {

        return when (mUriMatcher.match(uri)) {
            USER_CODE -> DBHelper.USER_TABLE_NAME
            JOB_CODE -> DBHelper.JOB_TABLE_NAME
            else -> {
                "null"
            }
        }
    }

}