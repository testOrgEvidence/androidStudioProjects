package com.example.greetingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var greetUser=findViewById<EditText>(R.id.etName)
        var greetText=findViewById<TextView>(R.id.tvName)
        val btnSubmit=findViewById<Button>(R.id.btnSubmit)
        val btnNext=findViewById<Button>(R.id.btnNext)
        var enteredName=""
        btnNext.visibility= INVISIBLE
        btnSubmit.setOnClickListener {
            enteredName=greetUser.text.toString()
            if(enteredName==""){
                greetText.text=""
                btnNext.visibility=INVISIBLE
                Toast.makeText(this@MainActivity,"please enter name",Toast.LENGTH_SHORT).show()
            }else{
                greetText.text="Hello $enteredName"
                btnNext.visibility= VISIBLE
            }
        }

        btnNext.setOnClickListener {
            val intent= Intent(this,SecondPage::class.java)
            startActivity(intent)
        }

    }
}