package com.example.contentproviderdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.contentproviderdb.db.ExampleDBHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<EditText>(R.id.name_edit_text)
        val placeText = findViewById<EditText>(R.id.place_edit_text)
        val submit = findViewById<Button>(R.id.submitBtn)


        submit.setOnClickListener {
            if(nameText.text.isNotEmpty() && placeText.text.isNotEmpty()){
                val db = ExampleDBHandler(this)
                val user = User(nameText.text.toString(), placeText.text.toString())
                db.insertData(user)
            }
        }
    }
}