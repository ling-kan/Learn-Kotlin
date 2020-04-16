package com.example.learnkotlinapp

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.example.learnkotlinapp.Activity3Fragment.Companion.LOG_TAG
import kotlinx.android.synthetic.main.fragment_activity3.*
import java.nio.file.Files.delete
import java.text.SimpleDateFormat
import java.util.*


class Activity3Fragment : AppCompatActivity() {

    private val taskList: MutableList<String> = mutableListOf()
    private val adapter by lazy { makeAdapter(taskList) }
    private val ADD_TASK_REQUEST = 1
    private val tickReceiver by lazy { makeBroadcastReceiver() }

    //Persisting data between launches
    private val PREFS_TASKS = "prefs_tasks"
    private val KEY_TASKS_LIST = "tasks_list"

    companion object {
        private const val LOG_TAG = "MainActivityLog"

        private fun getCurrentTimeStamp(): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            val now = Date()
            return simpleDateFormat.format(now)
        }
    }

    private fun makeBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    dateTimeTextView.text = getCurrentTimeStamp()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity3)

        //The reference to taskListView is initialized using Kotlin Android Extensions,
        //this replaces findViewById() calls and the need for other view-binding libraries.
       taskListView.adapter = adapter
        //ListView to capture the user’s taps on individual list entries. The listener is a Kotlin lambda.
      // taskListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> }
        taskListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            taskSelected(position)
        }

        val savedList = getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).getString(KEY_TASKS_LIST, null)
        if (savedList != null) {
            val items = savedList.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            taskList.addAll(items)
        }
    }

    override fun onResume() {
        super.onResume()
        // Update date and time with textview with the current time
        dateTimeTextView.text = getCurrentTimeStamp()
        // Register broadcast recieve in resume(), to ensure the broadcasts for action time tick are sent every minute after the time is changed
        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun onPause() {
        super.onPause()
        // unregister the broadcast recieve onPause , so activity receives the time change broadcasts while paused
        // cuts down unnecessary system overhead
        try {
            unregisterReceiver(tickReceiver)
        } catch (e: IllegalArgumentException) {
            Log.e(Activity3Fragment.LOG_TAG, "Time tick Receiver not registered", e)
        }
    }
    override fun onStop() {
        super.onStop()

        // Save all data which you want to persist.
        val savedList = StringBuilder()
        for (task in taskList) {
            savedList.append(task)
            savedList.append(",")
        }

        getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).edit()
            .putString(KEY_TASKS_LIST, savedList.toString()).apply()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
// Check requestcode to ensure the activity result is for the correct activity
        if (requestCode == ADD_TASK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                // Extract the task description from the result intent after a null check with let, then add to the list
                val task = data?.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION)
                task?.let {
                    taskList.add(task)
                    //  notify listview about changes in datamodel to trigger refresh of the view
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

    private fun taskSelected(position: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title)
            .setMessage(taskList[position])
                //positive button to remove the item from the list and refresh it
            .setPositiveButton(R.string.delete, { _, _ ->
                taskList.removeAt(position)
                adapter.notifyDataSetChanged()
            })
            .setNegativeButton(R.string.cancel, {
                    dialog, _ -> dialog.cancel()
            })
            .create()
            .show()
    }
}
