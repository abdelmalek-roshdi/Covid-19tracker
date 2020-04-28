package com.health.covid19.enitites

import com.google.gson.annotations.SerializedName

data class CountryCases (
    val country: String,

    @SerializedName("latest_stat_by_country")
    val latestStatByCountry: List<LatestStatByCountry>
)