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
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Activity2Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity2_2, container, false)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 //       setContentView(R.layout.fragment_activity2_2)

      //  val bundle = intent.extras
        if(bundle!=null)
        {
            val text1:String
            text1 = bundle.getString("text1")
        }

        val text1 = bundle?.get("text1")
        val text2 = bundle?.get("text2")

        view.findViewById<TextView>(R.id.textview_header).text = text1
    }*/
}
