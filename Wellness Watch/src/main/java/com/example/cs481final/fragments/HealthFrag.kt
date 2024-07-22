package com.example.cs481final.fragments

import android.Manifest
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.cs481final.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HealthFrag : Fragment(R.layout.fragment_health) {

    val CHANNEL_ID = "channel"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createNotificationChannel()
        view.findViewById<Button>(R.id.sendNotif).setOnClickListener{
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ){
                Toast.makeText(requireContext(), "Allow Permissions", Toast.LENGTH_SHORT).show()
            }
            sendNotification()
        }

        val findActivity = view.findViewById<Button>(R.id.find_activity)
        findActivity.setOnClickListener {
            val issue = view.findViewById<Spinner>(R.id.issue)
            val ideas = issue.selectedItem
            val activityList = getActivity(ideas.toString())
            val acts = activityList.reduce { str, item -> str + '\n' + item }
            val activities = view.findViewById<TextView>(R.id.workouts)
            activities.text = acts
        }
    }

    private fun getActivity(ideas: String): List<String> {
        return when (ideas) {
            "Sleep" -> listOf("Keep phone away for 30min before bed!", " ", "Create a sleep schedule and stick to it.")
            "Diet" -> listOf("Ensure you're eating 3 meals a day!", " ", "Eat your last meal 3 hours before bed.")
            "Mindspace" -> listOf("Try meditation!", " ", "Minimize digital distractions.")
            "Productiveness" -> listOf("Set small goals, achieving them gives you energy for another goal!", " ", "Avoid trying to multitask everything.")
            else -> listOf("idea", " ", "idea")
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= 26){
            val channel = NotificationChannel(CHANNEL_ID, "Main", NotificationManager.IMPORTANCE_DEFAULT)
            NotificationManagerCompat.from(requireContext()).createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val title = view?.findViewById<EditText>(R.id.textTitle)?.text.toString()
        val message = view?.findViewById<EditText>(R.id.textMessage)?.text.toString()
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(requireContext()).notify(1, builder.build())
    }


}


