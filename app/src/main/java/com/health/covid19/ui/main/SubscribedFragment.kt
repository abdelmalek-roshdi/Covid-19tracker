package com.health.covid19.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.ui.adapters.CountryRatesAdapter
import com.health.covid19.ui.adapters.SubscribedCountriesAdapter
import com.health.covid19.viewmodels.CasesViewModel
import com.health.covid19.viewmodels.SubscribedViewModel
import kotlinx.android.synthetic.main.subscribed_fragment.*
import kotlinx.android.synthetic.main.subscribed_fragment.view.*
import kotlinx.android.synthetic.main.subscribed_fragment.view.emptySubscriptionList
import javax.inject.Inject

class SubscribedFragment : Fragment() {

    private lateinit var app: Covid19TrackerApp

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var model: CasesViewModel
    private lateinit var subscribedRecyclerView: RecyclerView
    private lateinit var subscribedAdapter: SubscribedCountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subscribed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)

    }

    private fun init(view: View) {
        app = activity?.application as Covid19TrackerApp
        app.covid19TrackerComponent.inject(this)
        model = modelFactory.create(CasesViewModel::class.java)
        subscribedRecyclerView = view.subscribed_countries_recyclerView
        subscribedAdapter = SubscribedCountriesAdapter()
        subscribedRecyclerView.layoutManager = LinearLayoutManager(activity)
        subscribedRecyclerView.adapter = subscribedAdapter
        view.subscribed_countries_recyclerView.layoutManager = LinearLayoutManager(activity)
        subscribedAdapter.setViewModel(model)
        setView()

    }

    private fun setView(){
        model.subscribedCases.observe(requireActivity(),  Observer {
                subscribedCases ->
            if (subscribedCases.count()>0){
                emptySubscriptionList.isVisible=false
                emptystateimg.isVisible=false
            }else{ emptySubscriptionList.isVisible=true
                emptystateimg.isVisible=true}
            subscribedAdapter.submitList(subscribedCases)
            Log.d("sub","sub")

            // countryRatesAdapter.submitList(model.cases.value?.filter { case -> case.country.startsWith("s", true) })

        })
    }


}
