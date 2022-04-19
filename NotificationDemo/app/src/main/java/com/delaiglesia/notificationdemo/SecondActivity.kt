package com.delaiglesia.notificationdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.RemoteInput
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    private val KEY_REPLY = "key_reply"
    private lateinit var textViewName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textViewName = findViewById(R.id.textViewName)
        receiveNotification()


    }

    private fun receiveNotification() {
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val input = remoteInput.getCharSequence(KEY_REPLY)
            textViewName.text = input

            val channelID = "com.delaiglesia.notificationdemo.channel1"
            val notificationId = 1

            val repliedNotification = Notification.Builder(this, channelID)
                .setContentTitle("Notification Reply")
                .setContentText("You replied: $input")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .build()
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)
        }
    }
}