package com.delaiglesia.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadingWorker(context: Context, params:WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            for (i in 0..30000) {
                Log.d("MYTAG", "Downloading $i")
            }
            return Result.success()
        } catch (e: Exception) {
            Log.e("MYTAG", "Error Downloading", e)
            return Result.failure()
        }
    }
}