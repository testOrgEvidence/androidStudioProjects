package com.example.registerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var txtUserName=findViewById<TextView>(R.id.txtUserName)
        val welcomeText=findViewById<TextView>(R.id.txtWelcomeNote)
        var txtUserCollege=findViewById<TextView>(R.id.txtUserCollege)
        var txtUserCourse=findViewById<TextView>(R.id.txtUserCourse)
        var txtUserAge=findViewById<TextView>(R.id.txtUserAge)
        var txtUserEmail=findViewById<TextView>(R.id.txtUserEmail)
        var txtUserPhoneNo=findViewById<TextView>(R.id.txtUserPhoneNo)
        var txtUserState=findViewById<TextView>(R.id.txtUserState)
        var txtUserGender=findViewById<TextView>(R.id.txtUserGender)
        var txtUserBacklog=findViewById<TextView>(R.id.txtUserBacklog)
        var txtUserJobPreference=findViewById<TextView>(R.id.txtUserJobPreference)

        val userName=intent.getStringExtra("userName")
        val userCollege=intent.getStringExtra("userCollege")
        val userCourse1=intent.getStringExtra("userCourse")
        val userAge=intent.getStringExtra("userAge")
        val userEmail=intent.getStringExtra("userEmail")
        val userPhoneNo=intent.getStringExtra("userContactNo")
        val userState=intent.getStringExtra("userState")
        val userGender=intent.getStringExtra("userGender")
        val userBacklogIndex=intent.getStringExtra("userBacklog")
        val userJobPreference=intent.getStringExtra("userJobPreference")
        welcomeText.text=getString(R.string.welcome_note,userName.toString())
        val userBacklog=when(userBacklogIndex.toString()){
            "1"->"YES"
            else->"NO"
        }
        txtUserName.text=userName.toString()
        txtUserCollege.text=userCollege.toString()
        txtUserAge.text=userAge.toString()
        txtUserCourse.text=userCourse1.toString()
        txtUserEmail.text=userEmail.toString()
        txtUserPhoneNo.text=userPhoneNo.toString()
        txtUserState.text=userState.toString()
        txtUserGender.text= userGender.toString()
        txtUserBacklog.text=userBacklog.toString()
        txtUserJobPreference.text=userJobPreference.toString()


    }
}