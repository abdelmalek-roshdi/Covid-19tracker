package com.health.covid19.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.StatisticsRepository
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.WorldWide
import com.health.covid19.util.Connectivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlin.math.roundToLong

class StatisticsViewModel @Inject constructor (

    private val StatisticsRepo: StatisticsRepository,private val connectivity: Connectivity, private val casesrepo: CasesRepository


    ): ViewModel() {


    fun getCaseForContinent(Continent :String): LiveData<List<Case>> {
            val caseForContinent : LiveData<List<Case>> = liveData(Dispatchers.IO) {
                StatisticsRepo.getCaseForContinent(Continent)?.let { emit(it) }
            }
            return  caseForContinent
        }




    fun calcCasesForContinent(cases:List<Case>,Continent:String):Long?{
       val allCases:List<Case> =cases.filter{case: Case -> case.continent==Continent }
       if(allCases!= null && allCases.count() > 0){
           return getCasesCount(allCases)
       }
        else{return  0}

    }


    private fun getCasesCount(cases:List<Case>):Long?{

        return cases.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
   }

    fun  getworldWide(): LiveData<Case>{

        val globalCase:Case=Case(country = "worldwide")
        val global : LiveData<Case>

        global = liveData(Dispatchers.IO) {
            if (connectivity.checkForConnectivity()){
                Log.i("OMNIA","OnLINE "+connectivity.checkForConnectivity())
            StatisticsRepo.getWorlWidw()?.let {

                globalCase.cases=it["cases"]?.roundToLong()?:0
                globalCase.recovered=it["recovered"]?.roundToLong()?:0
                globalCase.deaths=it["deaths"]?.roundToLong()?:0
                casesrepo.insertCase(globalCase)

                emit(globalCase)  }
            }

            else{
                casesrepo.getCaseForCountryOffline("worldwide").let {
                 Log.i("OMNIA","OFFLINE")

                    emit(it?:Case()) }
            }


        }

        return global
        }


    }

