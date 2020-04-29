package com.health.covid19

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.net.CaseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var caseApi:CaseApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Covid19TrackerApp).covid19TrackerComponent.inject(this)

        caseApi.getCases().enqueue(object : Callback<ArrayList<Case>>{
            override fun onFailure(call: Call<ArrayList<Case>>, t: Throwable) {
                Log.d("tag", "failed")
            }

            override fun onResponse(
                call: Call<ArrayList<Case>>,
                response: Response<ArrayList<Case>>
            ) {

               response.body()!!.forEach {
                   Log.d("case", it.toString())
               }
            }

        })

    }
}
