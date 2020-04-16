package com.example.learnkotlinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.activity1_button).setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_Activity1Fragment)
        }

        view.findViewById<Button>(R.id.activity2_button).setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_Activity2Fragment)
        }


        view.findViewById<Button>(R.id.activity3_button).setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_Activity3Fragment)
        }

    }
}
