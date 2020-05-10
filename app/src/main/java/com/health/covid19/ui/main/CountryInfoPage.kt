package com.health.covid19.ui.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.viewmodels.CasesViewModel
import com.health.covid19.viewmodels.CountryInfoPageViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_info_page_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import javax.inject.Inject


class CountryInfoPage : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory
    private lateinit var viewModel: CountryInfoPageViewModel
    private lateinit var viewModel2: CasesViewModel
    private lateinit var casesViewModel: CasesViewModel

    val args: CountryInfoPageArgs by navArgs()
    lateinit var currentCase:Case
    var Subscribed:Boolean=false




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view=inflater.inflate(R.layout.country_info_page_fragment, container, false)
        initViews(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!Subscribed){
            subscribe.setBackgroundResource(R.drawable.ic_subscribe);
        }else{
            subscribe.setBackgroundResource(R.drawable.ic_unsubscribe);
        }
        subscribe.setOnClickListener {
            if(!Subscribed){
                Subscribed=true
                Subscribe(true)
                subscribe.setBackgroundResource(R.drawable.ic_unsubscribe);
                val myToast = Toast.makeText(context,"Subscribed!",Toast.LENGTH_SHORT)
                myToast.setGravity(Gravity.CENTER,0,200)
                myToast.show()

            }else{
                Subscribed=false
                Subscribe(false)
                subscribe.setBackgroundResource(R.drawable.ic_subscribe);
                val myToast = Toast.makeText(context,"Unsubscribed!",Toast.LENGTH_SHORT)
                myToast.setGravity(Gravity.CENTER,0,200)
                myToast.show()

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        activity?.menuInflater?.inflate(R.menu.country_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.settings -> {
                findNavController().navigate(R.id.action_countryInfoPage_to_settingsFragment)
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    private fun init() {
        (activity?.application as Covid19TrackerApp).covid19TrackerComponent.inject(this)
        viewModel = provider.create(CountryInfoPageViewModel::class.java)
        viewModel2 = provider.create(CasesViewModel::class.java)
        casesViewModel = provider.create(CasesViewModel::class.java)
        setViewModel(args.countryName)



    }

    private fun setViewModel(Country:String){
         viewModel.getCaseForCountry(Country).observe(viewLifecycleOwner, Observer {case:Case ->

             setFlag(case.countryInfo.flag?:" ")
             Subscribed=case.isSubscribed
             currentCase=case
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
             Subscribed=case.isSubscribed

             if(Subscribed){
                 subscribe.setBackgroundResource(R.drawable.ic_unsubscribe);

             }else{
                 subscribe.setBackgroundResource(R.drawable.ic_subscribe);
             }
             print("---------------------------------------------------caseee"+case.isSubscribed.toString())

         })


    }

    private fun initViews(view: View) {
        registerForContextMenu(view.menu_textView)
        view.menu_textView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.menu_textView.showContextMenu(20.0f, 20.0f)
            }
        }
    }

   private fun Subscribe(subscribed: Boolean){
       currentCase.isSubscribed=subscribed
       viewModel2.updateCase(currentCase)
   }

   fun setFlag(uri:String){
     Picasso.get().load(uri)
           .into(country_flag)
   }


    }
