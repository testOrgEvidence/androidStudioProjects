package com.example.imageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.adapter.DogCardAdapter
import com.example.imageapp.const.Layout

class VerticalListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_list)
        val recyclerView = findViewById<RecyclerView>(R.id.vertical_recycler_view)
        recyclerView.adapter = DogCardAdapter(applicationContext, Layout.VERTICAL)
        recyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
