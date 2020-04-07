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
class Activity1Fragment2 : Fragment() {

    //line where the arguments are - pass data between fragments
    val args: Activity1Fragment2Args by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity1_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
           this.findNavController().navigate(R.id.action_Activity1Fragment2_to_Activity1Fragment)
        }
    // create a click listner, add lines to get the count argument, get the string and format it with the count and then set it for the textview_header
        val count = args.myArg
        val countText = getString(R.string.random_heading, count)
        view.findViewById<TextView>(R.id.textview_header).text = countText
        //generate a random number between 0 and the count
        val random = java.util.Random()
        var randomNumber = 0
        if (count > 0){
            randomNumber = random.nextInt(count + 1)
        }
        // add code to conver the string and set it as the text
        view.findViewById<TextView>(R.id.textview_random).text = randomNumber.toString()
    }
}
