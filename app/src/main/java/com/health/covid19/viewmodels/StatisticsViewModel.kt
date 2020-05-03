package com.health.covid19.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.health.covid19.data.CasesRepository
import com.health.covid19.data.StatisticsRepository
import com.health.covid19.enitites.Case
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class StatisticsViewModel @Inject constructor (

    private val StatisticsRepo: StatisticsRepository
    ): ViewModel() {

    var casesAfrica: LiveData<List<Case>> = liveData {
            emit(StatisticsRepo.getCaseForContinent("Africa"))
        }
    var casesAsia: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("Asia"))
    }
    var casesEurope: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("Europe"))
    }
    var casesAmerica: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("North_America"))
    }
    var casesSouthAmerica: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("SouthAmerica"))
    }
    var casesAustralia: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("Australia"))
    }

    fun getAfricaCases(){
        casesAfrica.forEach

    }


    fun getCaseForContinent(Continent :String): LiveData<List<Case>> {
            val caseForContinent : LiveData<List<Case>> = liveData(Dispatchers.IO) {
                StatisticsRepo.getCaseForContinent(Continent)?.let { emit(it) }
            }
            return  caseForContinent
        }





}