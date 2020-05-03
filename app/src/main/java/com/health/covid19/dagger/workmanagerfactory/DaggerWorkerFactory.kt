package com.health.covid19.dagger.workmanagerfactory

import android.content.Context
import androidx.work.*
import com.health.covid19.data.CasesRepository
import com.health.covid19.workmanager.RefreshWorker


class DaggerWorkerFactory(private val casesRepository: CasesRepository) : WorkerFactory() {

    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {

        val workerKlass = Class.forName(workerClassName).asSubclass(CoroutineWorker::class.java)
        val constructor = workerKlass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {
            is RefreshWorker -> {
                instance.casesRepository = casesRepository
            }
            // optionally, handle other workers
        }

        return instance
    }
}