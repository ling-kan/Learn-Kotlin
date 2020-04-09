package com.example.learnkotlinapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*
import kotlinx.android.synthetic.main.example_item.view.image_view
import kotlinx.android.synthetic.main.fragment_activity2_2.view.*


// View holder represent a single row in view holder
// Cache references in view holder and reference them
// Create three properties with references to the row layout

class ExampleAdapter(
//    private val context: Context,
    private val exampleList: List<ExampleItem>
) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    interface ClickListener {
        fun onClickListener(item: ExampleItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        val clickHandler: ExampleAdapter.ClickEventHandler =
            holder.imageView.context as ExampleAdapter.ClickEventHandler

        holder.imageView.setImageResource(currentItem.imgResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

        holder.itemView.setOnClickListener {
                Toast.makeText(
                    holder.imageView.context,
                    "Clicked ${currentItem.text1} ${currentItem.text2} ",
                    Toast.LENGTH_SHORT
                ).show()

            val toPass = Bundle()
            toPass.putString("text1", currentItem.text1)
            toPass.putString("text2", currentItem.text2)

            val intent =
                Intent(
                    holder.itemView.context,
                    Activity2Fragment2::class.java
                ) //context we got from constructor
            intent.putExtras(toPass)
            holder.itemView.context.startActivity(intent) // or  can use ContextCompat?
            // ContextCompat.startActivity(holder.itemView.context, intent, null)
            //startActivity(intent);

            // listener.onClickListener(currentItem)
            // exampleList.invoke(position)
            // holder.getContext().startActivity(Intent(view.getContext(), SecondActivity::class.java))
            // val action = Activity2FragmentDirections.actionActivity2FragmentToActivity2Fragment2(currentItem.text1)
            // findNavController().navigate(action)
        }
    }


    interface ClickEventHandler {
        fun forwardClick(holder: ExampleViewHolder)
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view

        // itemView.image_view is the same as  itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.text_view1
        val textView2: TextView = itemView.text_view2
    }
}
