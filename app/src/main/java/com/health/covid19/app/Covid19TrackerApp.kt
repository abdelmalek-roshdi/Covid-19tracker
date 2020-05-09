package com.health.covid19.app

import android.app.Application
import androidx.work.*
import com.health.covid19.dagger.componenets.AppComponent
import com.health.covid19.dagger.componenets.DaggerAppComponent
import com.health.covid19.dagger.modules.AppModule
import com.health.covid19.workmanager.RefreshWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class Covid19TrackerApp: Application() {
    lateinit var covid19TrackerComponent:AppComponent
    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun onCreate() {
        super.onCreate()
        covid19TrackerComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        covid19TrackerComponent.inject(this)

        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
        WorkManager.initialize(this, config)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val request = PeriodicWorkRequestBuilder<RefreshWorker>(15, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 1,TimeUnit.MINUTES)
            .setConstraints(constraints).build()

        val worker = WorkManager.getInstance(this)

        worker.enqueueUniquePeriodicWork("workName", ExistingPeriodicWorkPolicy.KEEP,
            request)

    }
}