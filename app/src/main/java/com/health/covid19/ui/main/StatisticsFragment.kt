package com.health.covid19.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.WorldWide
import com.health.covid19.viewmodels.CasesViewModel
import com.health.covid19.viewmodels.StatisticsViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import java.lang.Integer.parseInt
import javax.inject.Inject


class StatisticsFragment: Fragment() {

    private lateinit var data: PieChartData

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var model: StatisticsViewModel
    private lateinit var model2: CasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

       val view = inflater.inflate(R.layout.fragment_statistics, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as Covid19TrackerApp).covid19TrackerComponent.inject(this)
         model = modelFactory.create(StatisticsViewModel::class.java)
         model.getworldWide()
         model2 = modelFactory.create(CasesViewModel::class.java)
        setView()
        setGlobal()
     }

    fun setList(AF:Long,AS:Long,AU:Long,EU:Long,NA:Long,SA:Long){
        africaCases.text=AF.toString()
        asiaCases.text=AS.toString()
        australiaCases.text=AU.toString()
        europeCases.text=EU.toString()
        northamericaCases.text=NA.toString()
        southamericaCases.text=SA.toString()

    }


    fun setView(){
      model2.offlineCases.observe(viewLifecycleOwner, Observer {cases: List<Case> ->

          if(cases.count()>0) {

              var AF: Long = model.calcCasesForContinent(cases, "Africa") ?: 0
              var AS: Long = model.calcCasesForContinent(cases, "Asia") ?: 0
              var AU: Long = model.calcCasesForContinent(cases, "Australia/Oceania") ?: 0
              var EU: Long = model.calcCasesForContinent(cases, "Europe") ?: 0
              var NA: Long = model.calcCasesForContinent(cases, "North America") ?: 0
              var SA: Long = model.calcCasesForContinent(cases, "South America") ?: 0

              //Log.i("OMNIA", model.getCasesCount(cases).toString() )
              setChart(AF, AS, AU, EU, NA, SA)
              setList(AF, AS, AU, EU, NA, SA)
          }
          else{   setChart(0, 0, 0, 0, 0, 0)
          setList(0, 0, 0, 0, 0, 0)}
      })
  }


    fun setChart(AF:Long,AS:Long,AU:Long,EU:Long,NA:Long,SA:Long){




        var values: ArrayList<SliceValue> = ArrayList()

        val sliceAfrica = SliceValue((AF/10000).toFloat() *30 + 15, Color.parseColor("#53676C"))
        val sliceAsia = SliceValue((AS/10000).toFloat() * 30 + 15,  Color.parseColor("#FF9955"))
        val sliceAustralia = SliceValue((AU/10000).toFloat() * 30 + 15, Color.parseColor("#67DEF9"))
        val sliceEurope = SliceValue((EU/10000).toFloat() * 30 + 15, Color.parseColor("#C83771"))
        val sliceNorthAmerica = SliceValue((NA/10000).toFloat() * 30 + 15, Color.parseColor("#FFCC00"))
        val slicesouthAmerica = SliceValue((NA/10000).toFloat() * 30 + 15, Color.parseColor("#00AA00"))


        values.add(sliceAfrica)
        values.add(sliceAsia)
        values.add(sliceAustralia)
        values.add(sliceEurope)
        values.add(sliceNorthAmerica)
        values.add(slicesouthAmerica)

        data =PieChartData(values);

        data.setHasLabels(false);

        data.setHasLabelsOnlyForSelected(false);

        data.setHasLabelsOutside(false);

        data.setHasCenterCircle(true);
        chart.setPieChartData(data);
    }
    fun setGlobal(){
        model.getworldWide().observe(viewLifecycleOwner, Observer {case: Case ->
            globalConfirmed.text= case.cases.toString()
            globalRecovered.text= case.recovered.toString()
            globalDeaths.text= case.deaths.toString()
        })
    }

}