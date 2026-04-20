package com.example.imageapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.adapter.DogCardAdapter
import com.example.imageapp.const.Layout

class GridListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_list)
        val recyclerView=findViewById<RecyclerView>(R.id.grid_recycler_view)
        recyclerView.adapter=DogCardAdapter(applicationContext,Layout.GRID)
        recyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}