package com.delaiglesia.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.delaiglesia.workmanagerdemo1.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener {
            setOneTimeWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)

        //constraints
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val filteringRequest = setFilteringWorker(constraints)
        val compressingRequest = setCompressingWorker(constraints)
        val uploadRequest = setUploadingWorker(constraints, workManager)
        val downloadingRequest = setDownloadingWorker(constraints)

        val parallelWorks = mutableListOf(downloadingRequest, filteringRequest)

        //encadenamos workers! omg
        workManager.beginWith(parallelWorks)
            .then(compressingRequest)
            .then(uploadRequest)
            .enqueue()
    }

    private fun setFilteringWorker(constraints: Constraints): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .setConstraints(constraints)
            .build()
    }

    private fun setCompressingWorker(constraints: Constraints): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .setConstraints(constraints)
            .build()
    }

    private fun setUploadingWorker(constraints: Constraints, workManager: WorkManager): OneTimeWorkRequest {
        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 30000)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploaderWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null) {
                    //RUNNING, SUCCEEDED, FAILED, BLOCKED, ENQUEUED states
                    binding.textView.text = workInfo.state.name
                    if (workInfo.state.isFinished) {
                        val data = workInfo.outputData
                        val message = data.getString(UploaderWorker.KEY_WORKER)
                        binding.textView.append("\n\n $message")
                    }
                }
            }
        return uploadRequest
    }

    private fun setDownloadingWorker(constraints: Constraints): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .setConstraints(constraints)
            .build()
    }

    //PERIODIC WORK REQUEST
    private fun setPeriodicWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)

        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java, 15, TimeUnit.MINUTES)
            .build()

        workManager.enqueue(periodicWorkRequest)
    }
}