package com.health.covid19.workmanager


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.data.CasesRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class RefreshWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    @Inject
    lateinit var casesRepository: CasesRepository

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
            val refreshJob =
                async {
                    val cases = casesRepository.getCases()
                    casesRepository.insertAll(cases)

                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            applicationContext,
                            "updated from work manager",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("updated", "Workmanager")
                    }

                }

         refreshJob.await()
         Result.success()
        }





}