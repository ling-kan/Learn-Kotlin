package com.example.learnkotlinapp

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_activity2.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Activity2Fragment2 : Fragment() {
    //line where the arguments are - pass data between fragments
   // val args: Activity2Fragment2Args by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity2_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //load
        val textView1 = arguments?.getString("textView1")
        val textView2 = activity?.intent?.getStringExtra("textView2")

        view.findViewById<TextView>(R.id.titleValue).setText(textView1);
        view.findViewById<TextView>(R.id.idValue).text =  textView2;
    }

}
