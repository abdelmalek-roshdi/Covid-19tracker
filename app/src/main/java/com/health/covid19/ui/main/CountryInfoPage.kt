package com.health.covid19.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.viewmodels.CountryInfoPageViewModel
import kotlinx.android.synthetic.main.country_info_page_fragment.*
import javax.inject.Inject


class CountryInfoPage : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory
    private lateinit var viewModel: CountryInfoPageViewModel

    val args: CountryInfoPageArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_info_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        (activity?.application as Covid19TrackerApp).covid19TrackerComponent.inject(this)
        viewModel = provider.create(CountryInfoPageViewModel::class.java)
        setViewModel(args.countryName)
    }

    fun setViewModel(Country:String){
         viewModel.getCaseForCountry(Country).observe(viewLifecycleOwner, Observer {case:Case ->

             country_name.text=case.country
             country_flag
             country_confirmed.text=case.cases.toString()
             country_recovered.text=case.recovered.toString()
             country_deaths.text=case.deaths.toString()
             today_cases.text=case.todayCases.toString()
             today_deaths.text=case.todayDeaths.toString()
             per_million_cases.text=case.casesPerOneMillion.toString()
             per_million_deaths.text=case.deathsPerOneMillion.toString()
             per_million_test.text=case.testsPerOneMillion.toString()


         })


    }






}
