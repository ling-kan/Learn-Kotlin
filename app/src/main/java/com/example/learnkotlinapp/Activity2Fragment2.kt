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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_activity2.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Activity2Fragment2 : Fragment() {

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

        val textView1 = activity?.intent?.getStringExtra("textView1")
        val textView2 = activity?.intent?.getStringExtra("textView2")
      //  Intent intent = getIntent();
        //val myValue = intent.getStringExtra("textView1")

        //get
         // val bundle:Bundle ?= intent?.extras
         // val intent: Intent = getIntent()

        //  var message = intent!!.getString("textView1") // 1

        //  val textView1 = bundle.get("textView1")
         // val textView2 = bundle.get("textView2")

        view.findViewById<TextView>(R.id.titleValue).text =  textView1;
        view.findViewById<TextView>(R.id.idValue).text =  textView2;
    }

}
