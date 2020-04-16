package com.example.learnkotlinapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.content.Intent
import kotlinx.android.synthetic.main.fragment_activity3.*


class Activity3Fragment : AppCompatActivity() {

    private val taskList: MutableList<String> = mutableListOf()
    private val adapter by lazy { makeAdapter(taskList) }
    private val ADD_TASK_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity3)

        //The reference to taskListView is initialized using Kotlin Android Extensions,
        //this replaces findViewById() calls and the need for other view-binding libraries.
       taskListView.adapter = adapter
        //ListView to capture the user’s taps on individual list entries. The listener is a Kotlin lambda.
       taskListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 1
        if (requestCode == ADD_TASK_REQUEST) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                val task = data?.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION)
                task?.let {
                    taskList.add(task)
                    // 4
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun addTaskClicked(view: View) {
        val intent = Intent(this, TaskDescriptionActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)

    }
//A private function that initializes the adapter for the list view
    private fun makeAdapter(list: List<String>): ArrayAdapter<String> =
        ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

}
