package com.example.imageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.adapter.DogCardAdapter
import com.example.imageapp.const.Layout

class HorizontalListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_list)
        val recyclerView=findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        recyclerView.adapter=DogCardAdapter(applicationContext,Layout.HORIZONTAL)
        recyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}