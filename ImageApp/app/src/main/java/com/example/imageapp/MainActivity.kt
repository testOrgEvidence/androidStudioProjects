package com.example.imageapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity(){
private lateinit var listIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnGrid=findViewById<Button>(R.id.btnGrid)
        val btnHorizontal=findViewById<Button>(R.id.btnHorizontal)
        val btnVertical=findViewById<Button>(R.id.btnVertical)
        btnGrid.setOnClickListener { launchGrid() }
        btnHorizontal.setOnClickListener { launchHorizontal() }
        btnVertical.setOnClickListener { launchVertical() }
    }

    private fun launchVertical() {
        listIntent=Intent(this,VerticalListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchHorizontal() {
        listIntent=Intent(this,HorizontalListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchGrid() {
        listIntent=Intent(this,GridListActivity::class.java)
        startActivity(listIntent)
    }
}