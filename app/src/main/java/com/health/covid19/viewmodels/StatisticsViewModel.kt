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
        emit(StatisticsRepo.getCaseForContinent("North America"))
    }
    var casesSouthAmerica: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("South America"))
    }
    var casesAustralia: LiveData<List<Case>> = liveData {
        emit(StatisticsRepo.getCaseForContinent("Australia"))
    }



    ///////////////////////////////////////////////////////////////////////////////////
    fun getAfricaCases():Long?{
        return casesAfrica.value?.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
    }

    fun getAsiaCases(): Long? {
     return casesAsia.value?.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
    }
    fun getEuropeCases(): Long? {
        return casesEurope.value?.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
    }

    fun getAustraliaCases(): Long? {
        return casesAustralia.value?.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
    }

    fun getAmericaCases():Long?{
        return casesAmerica.value?.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
    }
    fun getsouthAmericaCases():Long?{
        return casesSouthAmerica.value?.map{ case->case.cases}?.reduce{ acc, cases -> acc+cases}
    }
//   fun getWorldWideCases():Long?{
//     return ()
//   }

    fun getCaseForContinent(Continent :String): LiveData<List<Case>> {
            val caseForContinent : LiveData<List<Case>> = liveData(Dispatchers.IO) {
                StatisticsRepo.getCaseForContinent(Continent)?.let { emit(it) }
            }
            return  caseForContinent
        }





}