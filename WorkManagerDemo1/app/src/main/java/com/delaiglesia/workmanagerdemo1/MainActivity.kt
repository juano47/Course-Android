package com.delaiglesia.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.delaiglesia.workmanagerdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener {
            setOneTimeWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)
        val uploadRequest = OneTimeWorkRequest.Builder(UploaderWorker::class.java).build()
        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, { workInfo ->
                if (workInfo != null) {
                    //RUNNING, SUCCEEDED, FAILED, BLOCKED, ENQUEUED states
                    binding.textView.text = workInfo.state.name
                }
            })
    }

}