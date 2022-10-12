package com.example.contentproviderdb.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.contentproviderdb.User

const val DATABASE_NAME = "Example.db"
const val TABLE_NAME = "User"
const val COL_NAME = "name"
const val COL_PLACE = "place"
const val COL_ID = "id"

class ExampleDBHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1 ) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT NOT NULL, $COL_PLACE TEXT NOT NULL);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user : User){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_PLACE, user.place)
        val result = db.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong()){
            println("error")
        }else{
            println("Success")
        }

    }




}