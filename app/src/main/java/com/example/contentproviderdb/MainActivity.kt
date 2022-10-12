package com.example.contentproviderdb

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.contentproviderdb.provider.PersonProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<EditText>(R.id.name_edit_text)
        val placeText = findViewById<EditText>(R.id.place_edit_text)

        val next = findViewById<Button>(R.id.next)
        val previous = findViewById<Button>(R.id.previous)
        val insert = findViewById<Button>(R.id.insert)
        val update = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)
        val clear = findViewById<Button>(R.id.clear)

        val result = contentResolver?.query(PersonProvider.CONTENT_URI, arrayOf(PersonProvider.COLUMN_ID, PersonProvider.COLUMN_NAME, PersonProvider.COLUMN_PLACE), null, null, PersonProvider.COLUMN_NAME)


        next.setOnClickListener {
            if(result?.moveToNext()!!){
                nameText.setText(result.getString(1))
                placeText.setText(result.getString(2))
            }
        }

        previous.setOnClickListener {
            if(result?.moveToPrevious()!!){
                nameText.setText(result.getString(1))
                placeText.setText(result.getString(2))
            }
        }

        insert.setOnClickListener {
            val cv = ContentValues()
            cv.put(PersonProvider.COLUMN_NAME, nameText.text.toString())
            cv.put(PersonProvider.COLUMN_PLACE, placeText.text.toString())
            contentResolver.insert(PersonProvider.CONTENT_URI, cv)
            result?.requery()
        }

        update.setOnClickListener {
            val cv = ContentValues()
            cv.put(PersonProvider.COLUMN_PLACE, placeText.text.toString())
            contentResolver.update(PersonProvider.CONTENT_URI, cv, "NAME = ?", arrayOf(nameText.text.toString()))
            result?.requery()
        }

        delete.setOnClickListener {
            contentResolver.delete(PersonProvider.CONTENT_URI, "NAME = ?",arrayOf(nameText.text.toString()))
            result?.requery()
        }

        clear.setOnClickListener {
            nameText.setText("")
            placeText.setText("")
            nameText.requestFocus()
        }

    }
}