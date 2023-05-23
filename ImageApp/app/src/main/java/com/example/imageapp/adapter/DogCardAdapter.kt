package com.example.imageapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.R
import com.example.imageapp.const.Layout.GRID
import com.example.imageapp.data.DataSource

class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private  val dogList=DataSource.dogs
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(private val view:View): RecyclerView.ViewHolder(view!!) {
        val imageView:ImageView=view.findViewById(R.id.dogImage)
        val textViewDogAge:TextView=view.findViewById(R.id.txtDogAge)
        val textViewDogHobby:TextView=view.findViewById(R.id.txtDogHobby)
        val textViewDogName:TextView=view.findViewById(R.id.txtDogName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        val adapterLayout = when (layout) {
            // Inflate the layout
            GRID -> LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontalz_list_item, parent, false)
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount()=dogList.size // TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dogData=dogList[position]
        holder.imageView.setImageResource(dogData.imageResourceId)
        holder.textViewDogName.text=dogData.name
        holder.textViewDogAge.text=dogData.age
        holder.textViewDogHobby.text=dogData.hobbies
        val resources= context?.resources
        holder.textViewDogAge.text= resources?.getString(R.string.dog_age,dogData.age)
        holder.textViewDogHobby.text=resources?.getString(R.string.dog_hobby,dogData.hobbies)
    }
}