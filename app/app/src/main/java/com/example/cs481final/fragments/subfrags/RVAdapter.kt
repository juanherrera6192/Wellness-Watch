package com.example.cs481final.fragments.subfrags

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cs481final.FoodItem
import com.example.cs481final.R

class RVAdapter (private val dataList: List<FoodItem>, private val listener: (FoodItem) -> Unit
    ): RecyclerView.Adapter<RVAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        val item = dataList[position]
        //holder.tvDescription.text =item.description.toString()
        holder.tvDescription.text =item.description.toString()

        holder.itemView.setOnClickListener { listener(item) }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)


    }

}