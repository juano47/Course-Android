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
        // Set the click listener for the button to enqueue one-time work
        binding.button.setOnClickListener {
            setOneTimeWorkRequest()
            //setPeriodicWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest() {
        /* applicationContext is the context of the application and not the activity context (this) because
        the work manager is going to run in the background and not in the activity context (this) which
        is going to be destroyed when the activity is destroyed and the work manager is going to be destroyed with it.
        So we need to use the application context. The application context is going to be alive as long as the application is alive.
        */
        val workManager = WorkManager.getInstance(applicationContext)

        //constraints, setear las condiciones para que se ejecute el trabajo,
        //en este caso que haya conectividad a internet y que la batería se esté cargando
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        //creamos los workers que se van a ejecutar en el background thread
        val filteringRequest = setFilteringWorker(constraints)
        val compressingRequest = setCompressingWorker(constraints)
        val uploadRequest = setUploadingWorker(constraints, workManager)
        val downloadingRequest = setDownloadingWorker(constraints)

        //workers que se ejecutan en paralelo
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
        //enviamos al worker la data que necesita
        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 30000)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploaderWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        //observamos el estado del worker usando el id del worker
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null) {
                    //RUNNING, SUCCEEDED, FAILED, BLOCKED, ENQUEUED states
                    binding.textView.text = workInfo.state.name
                    if (workInfo.state.isFinished) {
                        //leemos la data que nos devuelve el worker
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

        //Importante: el periodo de tiempo debe ser mayor a 15 minutos para que
        // funcione en dispositivos con Android 7.0 o superior (API 24).
        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java, 15, TimeUnit.MINUTES)
            .build()

        workManager.enqueue(periodicWorkRequest)
    }
}