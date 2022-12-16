package com.delaiglesia.stopwatch

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delaiglesia.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
           startOrStop()
        }

        binding.btnReset.setOnClickListener {
            reset()
        }

        serviceIntent = Intent(this, StopWatchService::class.java)
        registerReceiver(updateTime, IntentFilter(StopWatchService.UPDATE_TIME))

    }

    private fun startOrStop() {
        if (isStarted) {
            stop()
        } else {
            start()
        }
    }
    private fun start() {
        serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)
        startService(serviceIntent)
        isStarted = true
        binding.btnStart.text = "Stop"
    }

    private fun stop() {
        stopService(serviceIntent)
        isStarted = false
        binding.btnStart.text = "Start"
    }

    private fun reset() {
        stop()
        time = 0.0
        binding.tvTime.text = getFormattedTime(time)
    }

    private val updateTime : BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
            binding.tvTime.text = getFormattedTime(time)
        }
    }

    private fun getFormattedTime(time: Double): String {
        val hours = time.toInt() / 3600
        val minutes = (time.toInt() % 3600) / 60
        val seconds = time.toInt() % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}