package com.health.covid19.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.StatisticsRepository
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.WorldWide
import com.health.covid19.util.Connectivity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class StatisticsViewModel @Inject constructor (

    private val StatisticsRepo: StatisticsRepository,private val connectivity: Connectivity

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

    fun  getworldWide(): LiveData<WorldWide>{
        val global : LiveData<WorldWide> = liveData(Dispatchers.IO) {
            StatisticsRepo.getWorlWidw()?.let { emit(it) }
        }
        return  global
    }




}