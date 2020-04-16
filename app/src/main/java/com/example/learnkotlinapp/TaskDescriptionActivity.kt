package com.example.learnkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.app.Activity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_task_description.*
class TaskDescriptionActivity : AppCompatActivity() {
//    Used the Kotlin companion object for the class to define attributes common across the class, similar to static members in Java.
    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    }

    //verriden the onCreate() lifecycle method to set the content view for the activity from the layout file.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_description)
    }

    //Added an empty click handler that will be used to finish the activity.
    fun doneClicked(view: View) {
// 1You retrieve the task description from the descriptionText EditText, where Kotlin Android Extensions has again been used to get references to view fields.
        val taskDescription = descriptionText.text.toString()

        if (!taskDescription.isEmpty()) {
            //You create a result Intent to pass back to MainActivity if the task description retrieved in step one is not empty. Then you bundle the task description with the intent and set the activity result to RESULT_OK, indicating that the user successfully entered a task.
            val result = Intent()
            result.putExtra(EXTRA_TASK_DESCRIPTION, taskDescription)
            setResult(Activity.RESULT_OK, result)
        } else {
            //If the user has not entered a task description, you set the activity result to RESULT_CANCELED.
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
