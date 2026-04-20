package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener

class MainActivity : AppCompatActivity() {
    var courses = arrayOf("MCA", "MSC", "MBA", "MTECH")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var selectedCourse=""
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.spinner_course)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCourse= courses[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Select The Course", Toast.LENGTH_SHORT).show()
            }

        }
        val etName = findViewById<EditText>(R.id.etName)
        val etCollege = findViewById<EditText>(R.id.etCollege)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etJob=findViewById<EditText>(R.id.etJob)
        val etState=findViewById<EditText>(R.id.etState)
        val spinnerCourse=findViewById<Spinner>(R.id.spinner_course)
        val btnSubmit=findViewById<Button>(R.id.btnSubmit)
        val genderRadioGroup=findViewById<RadioGroup>(R.id.genderRadioGroup)
        val backlogRadioGroup=findViewById<RadioGroup>(R.id.backlogRadioGroup)
        val txtJobPreference=findViewById<EditText>(R.id.etJob)

        btnSubmit.setOnClickListener{
            val userGenderIndex:Int=genderRadioGroup!!.checkedRadioButtonId
            val userGender:RadioButton=findViewById(userGenderIndex)
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent
                .putExtra("userName",etName.text.toString())
                .putExtra("userCollege",etCollege.text.toString())
                .putExtra("userAge",etAge.text.toString())
                .putExtra("userEmail",etEmail.text.toString())
                .putExtra("userContactNo",etPhone.text.toString())
                .putExtra("userJob",etJob.text.toString())
                .putExtra("userState",etState.text.toString())
                .putExtra("userCourse",selectedCourse)
                .putExtra("userGender",userGender.text.toString())
                .putExtra("userBacklog",backlogRadioGroup.checkedRadioButtonId.toString())
                .putExtra("userJobPreference",txtJobPreference.text.toString())
            )
        }

    }
}