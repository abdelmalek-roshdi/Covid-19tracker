package com.health.covid19.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.ResultReceiver
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.viewmodels.CasesViewModel
import com.health.covid19.viewmodels.SubscribedViewModel
import kotlinx.android.synthetic.main.subscribed_fragment.view.*
import javax.inject.Inject

class SubscribedFragment : Fragment() {

    private lateinit var app: Covid19TrackerApp

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var model: CasesViewModel

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
        view.subscribed_countries_recyclerView.layoutManager = LinearLayoutManager(activity)
        model.subscribedCases.observe(requireActivity(),  Observer {
            //TODO populate the recyclerView with data
        })
    }


}
