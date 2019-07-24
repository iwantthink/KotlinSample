package com.ryan.kotlinsample.study

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(val context: Context) : SQLiteOpenHelper(
    context, DB_NAME, null, DATABASE_VERSION
) {

    companion object {
        private const val DB_NAME: String = "test.db"
        const val USER_TABLE_NAME = "user"
        const val JOB_TABLE_NAME = "job"
        private const val DATABASE_VERSION = 1
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS $JOB_TABLE_NAME(_id INTEGER PRIMARY KEY AUTOINCREMENT,JOB TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
