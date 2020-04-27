package com.example.learnkotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*


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
        // Does the same as below, difference?
        /*holder.itemView.setOnClickListener(
      Navigation.createNavigateOnClickListener(R.id.action_Activity2Fragment_to_activity2Fragment2)
         )*/

        holder.itemView.setOnClickListener { view ->
            Toast.makeText(
                holder.imageView.context,
                "Clicked ${currentItem.text1} ${currentItem.text2} ",
                Toast.LENGTH_SHORT
            ).show()

          /*fragment.setArguments(args)
            val bundle = Bundle();
            bundle.putString("textView1", currentItem.text1 );
            bundle.putString("textView2", currentItem.text2 );
           */

            //TODO:: LOAD
            //TODO:: creating and initializing an Intent object
            val context = holder.itemView.context;
            val intent = Intent(context, Activity2Fragment2::class.java);
            //attach the key value pair using putExtra to this intent
            intent.putExtra("textView1", currentItem.text1);
            intent.putExtra("textView2", currentItem.text2);
         //   context.startActivity(intent) // Error

           val fragment2 = Activity2Fragment();
            val bundle = Bundle()
            bundle.putString("textView1", currentItem.text1)
            bundle.putString("textView2", currentItem.text2)
            fragment2.arguments = bundle

            //  val bundle = bundleOf("myArgs2" to currentItem.text1 )
            //Redirect page
            view.findNavController().navigate(R.id.action_Activity2Fragment_to_activity2Fragment2)
          //  bundle
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
