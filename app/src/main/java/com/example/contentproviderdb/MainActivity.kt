package com.example.contentproviderdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.contentproviderdb.db.ExampleDBHandler
import com.example.contentproviderdb.db.PersonDBHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<EditText>(R.id.name_edit_text)
        val placeText = findViewById<EditText>(R.id.place_edit_text)
        val submit = findViewById<Button>(R.id.submitBtn)


//        submit.setOnClickListener {
//            if(nameText.text.isNotEmpty() && placeText.text.isNotEmpty()){
//                val db = ExampleDBHandler(this)
//                val user = User(nameText.text.toString(), placeText.text.toString())
//                db.insertData(user)
//            }
//        }

        submit.setOnClickListener {
            var personHelper = PersonDBHelper(applicationContext)
            var db = personHelper.readableDatabase
            var result = db.rawQuery("SELECT * FROM PERSON", null)
            if(result.moveToFirst()){
                println("${result.getInt(0)} - ${result.getString(1)} - ${result.getString(2)}")
            }

        }
    }
}