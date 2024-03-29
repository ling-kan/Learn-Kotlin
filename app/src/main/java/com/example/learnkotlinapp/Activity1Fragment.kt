package com.example.learnkotlinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Activity1Fragment : Fragment() {

    private fun countMe(view: View){
        //get the text view
        val showCountTextView = view.findViewById<TextView>(R.id.textview_first)

        //get the value of the text view
        val countString = showCountTextView.text.toString()
        //counvert value to number and increment it
        var count = countString.toInt()
        count++

        //Display the new value in text view
        showCountTextView.text = count.toString()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.activity1_home_button).setOnClickListener{
            findNavController().navigate(R.id.action_Activity1Fragment_to_mainFragment)
        }

        view.findViewById<Button>(R.id.random_button).setOnClickListener{
            //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
            val currentCount = showCountTextView.text.toString().toInt()
           val action = Activity1FragmentDirections.actionActivity1FragmentToActivity1Fragment2(currentCount)
       //     val action = Activity1Fragment
            findNavController().navigate(action)
        }

        //find the toast_button by its ID and set a click listener
        view.findViewById<Button>(R.id.toast_button).setOnClickListener{
            //create a toast with some text to appear for a short time
            val myToast = Toast.makeText(context, "Hello Toast!", Toast.LENGTH_SHORT)
            //show the Toast
            myToast.show()
        }

        view.findViewById<Button>(R.id.count_button).setOnClickListener{
            countMe(view)
        }


    }
}
