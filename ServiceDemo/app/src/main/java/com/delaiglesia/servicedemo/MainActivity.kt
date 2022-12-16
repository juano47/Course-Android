package com.delaiglesia.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.delaiglesia.servicedemo.MyBackgroundService.Companion.NAME
import com.delaiglesia.servicedemo.MyBackgroundService.Companion.TAG
import com.delaiglesia.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent: Intent = Intent(this, MyBackgroundService::class.java)
        //enviamos data extra al servicio con putExtra (nombre, valor)
        serviceIntent.putExtra(NAME, "John")
        binding.buttonStart.setOnClickListener {
            Log.i(TAG, "starting service")
            startService(serviceIntent)
        }

        binding.buttonStop.setOnClickListener {
            Log.i(TAG, "stopping service")
            stopService(serviceIntent)
        }
    }
}