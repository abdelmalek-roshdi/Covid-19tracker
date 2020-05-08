package com.health.covid19.extensions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.health.covid19.R
import com.health.covid19.workmanager.RefreshWorker


    private const val channelName = "CovidSubscriptionChannel"
    private const val notificationDescription = "shows notification on countries subscription changes"
    private const val channelId = "CovidNotification"
    private const val notificationTitle = "Covid Updates"

    fun RefreshWorker.makeStatusNotification(
        context: Context,
        countryName: String,
        newCases: Long,
        newRecovredCases: Long,
        newDeaths: Long
    ): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                channelId,
                channelName,
                importance
            )
            channel.description = notificationDescription
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        val message = "$countryName $newCases new cases, $newRecovredCases new recovered Cases, $newDeaths new death cases"
        val builder =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(notificationTitle)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(LongArray(0))

        return builder.build()
    }



