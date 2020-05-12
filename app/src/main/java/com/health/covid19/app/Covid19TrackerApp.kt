package com.health.covid19.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.NetworkInfo
import androidx.work.*
import com.health.covid19.dagger.componenets.AppComponent
import com.health.covid19.dagger.componenets.DaggerAppComponent
import com.health.covid19.dagger.modules.AppModule
import com.health.covid19.util.sharedPreferencesKey
import com.health.covid19.workmanager.RefreshWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class Covid19TrackerApp: Application() {
    lateinit var covid19TrackerComponent:AppComponent
    @Inject
    lateinit var workerFactory: WorkerFactory
    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        covid19TrackerComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        covid19TrackerComponent.inject(this)

        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
        WorkManager.initialize(this, config)

        setupWorkManager(intervalInHours =  preferences.getLong(sharedPreferencesKey, 2), keep = true)

    }

    fun setupWorkManager(intervalInHours: Long , keep: Boolean) {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val request = PeriodicWorkRequestBuilder<RefreshWorker>(intervalInHours, TimeUnit.HOURS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 1, TimeUnit.HOURS)
            .setConstraints(constraints)
        val worker = WorkManager.getInstance(this)

       if (keep){
           worker.enqueueUniquePeriodicWork(
               "workName", ExistingPeriodicWorkPolicy.KEEP,
               request
               .build()
           )
       }else{
           worker.enqueueUniquePeriodicWork(
               "workName", ExistingPeriodicWorkPolicy.REPLACE,
               request.setInitialDelay(intervalInHours, TimeUnit.HOURS)
               .build()
           )
       }
    }
}