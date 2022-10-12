package com.example.contentproviderdb.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.contentproviderdb.db.PersonDBHelper

class PersonProvider: ContentProvider() {

    companion object {
        private const val PROVIDER_NAME = "com.example.contentproviderdb.provider/PersonProvider"
        private const val URL = "content://$PROVIDER_NAME/PERSON"
        val CONTENT_URI = Uri.parse(URL)!!
        const val COLUMN_NAME = "NAME"
        const val COLUMN_PLACE = "PLACE"
        const val COLUMN_ID = "ID"
    }

    private lateinit var db: SQLiteDatabase


    override fun onCreate(): Boolean {
        val personHelper = PersonDBHelper(context)
        db = personHelper.writableDatabase
        return true
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri {
        db.insert("PERSON", null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }


    override fun update(uri: Uri, cv: ContentValues?, condition: String?, conditionValues: Array<out String>?): Int {
        val count = db.update("PERSON", cv, condition, conditionValues)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, condition: String?, conditionValues: Array<out String>?): Int {
        val count = db.delete("PERSON", condition, conditionValues)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }


    override fun query(
        uri: Uri,
        columns: Array<out String>?,
        condition: String?,
        conditionValues: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("PERSON", columns, condition, conditionValues, null, null, order)
    }

    override fun getType(p0: Uri): String {
        return "vnd.android.cursor.dir/vnd.example.PERSON"
    }
}