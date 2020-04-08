package com.example.learnkotlinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_activity2.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Activity2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity2, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val exampleList = createFakeList(100)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ExampleAdapter(exampleList)
        recyclerView.setHasFixedSize(true) //optimisation
    }

    private fun createFakeList(size: Int): List<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_burger
                1 -> R.drawable.ic_fries
                else -> R.drawable.ic_honey
            }
            val itemName = when (i % 3) {
                0 -> "Burger"
                1 -> "Fries"
                else -> "Honey"
            }

            val item = ExampleItem(drawable, itemName ,"Item $i")
            list += item
        }
        return list
    }
}
