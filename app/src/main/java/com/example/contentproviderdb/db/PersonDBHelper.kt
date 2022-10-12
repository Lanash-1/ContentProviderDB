package com.example.contentproviderdb.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PersonDBHelper(
    context: Context?
) : SQLiteOpenHelper(context, "PERSON.DB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE PERSON(ID INTEGER PRIMARY KEY, NAME TEXT NOT NULL, PLACE TEXT NOT NULL)"
        db?.execSQL(createTable)
        db?.execSQL("INSERT INTO PERSON(NAME, PLACE) VALUES('lanash', 'chennai')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}