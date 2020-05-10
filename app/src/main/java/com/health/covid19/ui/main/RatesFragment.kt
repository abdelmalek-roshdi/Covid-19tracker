package com.health.covid19.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.ui.adapters.CountryRatesAdapter
import com.health.covid19.viewmodels.CasesViewModel
import kotlinx.android.synthetic.main.rates_fragment.view.*
import kotlinx.coroutines.*
import javax.inject.Inject


class RatesFragment : Fragment() {
    private lateinit var countriesRecyclerView: RecyclerView
    private lateinit var countryRatesAdapter: CountryRatesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var index:Int = 0

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var model: CasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.rates_fragment, container, false)

        initViews(view)
        observeData()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun observeData() {
        (activity?.application as Covid19TrackerApp).covid19TrackerComponent.inject(this)
        model = modelFactory.create(CasesViewModel::class.java)
        model.offlineCases.observe(requireActivity(),
            Observer<List<Case>> { t ->
                countryRatesAdapter.submitList(t)
                Log.d("sub","sub")

                // countryRatesAdapter.submitList(model.cases.value?.filter { case -> case.country.startsWith("s", true) })

            })

    }

    private fun initViews(view: View) {
        view.swipeLayout.setOnRefreshListener{
           CoroutineScope(Dispatchers.IO).launch {
               model.refreshDataAsync().await()
               withContext(Dispatchers.Main){
                   view.swipeLayout.isRefreshing = false
               }
           }
        }

        view.swipeLayout.setColorSchemeResources(
            R.color.colorAccent,
            R.color.card_header_text_color,
            android.R.color.holo_orange_light
        )

        countriesRecyclerView = view.countries_recyclerView
        countryRatesAdapter = CountryRatesAdapter()
        countriesRecyclerView.layoutManager = LinearLayoutManager(activity)
        countriesRecyclerView.adapter = countryRatesAdapter
        view.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(prefix: String?): Boolean {
                prefix?.let {
                    countryRatesAdapter.submitList(model.searchCountry(it))

                }
                return true
            }
        } )
    }

    override fun onPause() {
        super.onPause()
        //model.itemPostion = (countriesRecyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

    }

    override fun onResume() {
        super.onResume()
     //   (countriesRecyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(model.itemPostion, 0)

    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Log.d("dest","dest")
    }
}