package com.health.covid19.workmanager


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.health.covid19.data.CasesRepository
import com.health.covid19.extensions.makeStatusNotification
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
//                     val subcases:List<Case> = cases.take(10)
//                     subcases.forEach{ case -> case.isSubcribed = true }
//                    casesRepository.insertAll(cases)
                    val subscribedCases = casesRepository.getSubscribedCases()
                    casesRepository.insertAll(cases)
                    var i = 0
                    if (subscribedCases.size > 0){
                        cases.forEach {
                            if (it.country.equals(subscribedCases[i].country)){
                                it.isSubcribed = true
                                casesRepository.insertCase(it)
                                val newCases = it.cases - subscribedCases[i].cases
                                val newRecovredCases = it.recovered - subscribedCases[i].recovered
                                val newDeathCases = it.deaths - subscribedCases[i].deaths
                                val notification = makeStatusNotification(applicationContext, it.country, newCases, newRecovredCases, newDeathCases)
                                withContext(Dispatchers.Main){
                                    NotificationManagerCompat.from(applicationContext).notify(i, notification)
                                }
                                i++
                            }

                        }
                    }



//                    withContext(Dispatchers.Main){
//                        Toast.makeText(
//                            applicationContext,
//                            "updated from work manager",
//                            Toast.LENGTH_LONG
//                        ).show()
//                        Log.d("updated", "Workmanager")
//                    }

                }

         refreshJob.await()
         Result.success()
        }





}