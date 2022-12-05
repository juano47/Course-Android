package com.delaiglesia.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class FilteringWorker(context: Context, params:WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            for (i in 0..30000) {
                Log.d("MYTAG", "Filtering $i")
            }
            return Result.success()
        } catch (e: Exception) {
            Log.e("MYTAG", "Error Filtering", e)
            return Result.failure()
        }
    }
}