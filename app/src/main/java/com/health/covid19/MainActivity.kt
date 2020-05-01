package com.health.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.room.LocalDB
import com.health.covid19.ui.adapters.CountryRatesAdapter
import com.health.covid19.viewmodels.CasesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var model: CasesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Covid19TrackerApp).covid19TrackerComponent.inject(this)

        model = modelFactory.create(CasesViewModel::class.java)
        model.getCaseForCountryName("USA").observe(this, object : Observer<Case>{
            override fun onChanged(t: Case?) {
                Log.d("casesssssss", t.toString())

            }
        })

    }
}
