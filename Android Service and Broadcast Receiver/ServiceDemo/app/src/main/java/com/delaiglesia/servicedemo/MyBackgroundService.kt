package com.delaiglesia.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService: Service() {

    init {
        Log.i(TAG, "service has been created")
    }

    //onStartCommand
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "service started")
        val name = intent?.getStringExtra(NAME)
        Log.i(TAG, "name is $name")
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? = null
    //es lo mismo que:
    //override fun onBind(p0: Intent?): IBinder? {
    //    return null
    //}

    override fun onDestroy() {
        Log.i(TAG, "service destroyed")
        super.onDestroy()
    }

    //companion object es como un static en java
    companion object {
        const val TAG = "MyTag"
        const val NAME = "name"
    }

}