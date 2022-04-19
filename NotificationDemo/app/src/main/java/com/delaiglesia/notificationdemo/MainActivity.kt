package com.delaiglesia.notificationdemo

import android.app.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private val channelID = "com.delaiglesia.notificationdemo.channel1"
    private var notificationManager: NotificationManager? = null
    private val KEY_REPLY = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "Channel 1", "This is the first channel")

        button = findViewById(R.id.button)
        button.setOnClickListener {
           displayNotification()
        }
    }

    private fun displayNotification() {
        val tapResultIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        //reply action
        val remoteInput = RemoteInput.Builder(KEY_REPLY)
            .setLabel("Insert your name here")
            .build()
        val replyAction = Notification.Action.Builder(0, "Reply", pendingIntent)
            .addRemoteInput(remoteInput)
            .setAllowGeneratedReplies(true)
            .build()

        //action button
        val actionIntent = Intent(this, DetailsActivity::class.java)
        val actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val action = Notification.Action.Builder(R.drawable.ic_launcher_foreground, "Action", actionPendingIntent).build()

        val notification = Notification.Builder(this, channelID)
            .setContentTitle("Notification Title")
            .setContentText("This is the notification text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setActions(action)
            .addAction(replyAction)
            .build()

        notificationManager?.notify(1, notification)
    }

    private fun createNotificationChannel(id: String, name: String, description: String) {
        //validation for lower API, notification only works on API 26+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance)
            channel.description = description
            notificationManager?.createNotificationChannel(channel)
        }
    }
}