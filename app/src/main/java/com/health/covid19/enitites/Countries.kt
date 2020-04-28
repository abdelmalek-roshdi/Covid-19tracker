package com.health.covid19.enitites

import com.google.gson.annotations.SerializedName


data class Countries (
    @SerializedName("countries_stat")
    val countriesStat: List<CountriesStat>,

    @SerializedName("statistic_taken_at")
    val statisticTakenAt: String?
)



