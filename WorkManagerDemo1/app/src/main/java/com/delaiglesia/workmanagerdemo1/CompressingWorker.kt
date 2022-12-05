package com.delaiglesia.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressingWorker(context: Context, params:WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            for (i in 0..30000) {
                Log.d("MYTAG", "Compressing $i")
            }
            return Result.success()
        } catch (e: Exception) {
            Log.e("MYTAG", "Error Compressing", e)
            return Result.failure()
        }
    }
}