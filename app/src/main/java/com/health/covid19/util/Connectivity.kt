package com.health.covid19.util

import android.app.Application
import android.content.Context
import android.net.NetworkInfo
import javax.inject.Inject

class Connectivity(private val app: Application) {

      fun  checkForConnectivity(): Boolean {
        val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}