package com.example.learnkotlinapp

import android.app.PendingIntent.getActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*
import java.security.AccessController.getContext
import kotlin.coroutines.coroutineContext

// View holder represent a single row in view holder
// Cache references in view holder and reference them
// Create three properties with references to the row layout

class ExampleAdapter(private val exampleList: List<ExampleItem>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imgResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.imageView.context, "Clicked ${currentItem.text1} ${currentItem.text2} " , Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        // itemView.image_view is the same as  itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.text_view1
        val textView2: TextView = itemView.text_view2
    }
}
