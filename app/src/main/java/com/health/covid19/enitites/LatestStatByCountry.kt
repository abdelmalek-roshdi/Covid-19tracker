package com.health.covid19.enitites

import com.google.gson.annotations.SerializedName

data class LatestStatByCountry (
    val id: String,

    @SerializedName("country_name")
    val countryName: String?,

    @SerializedName("total_cases")
    val totalCases: String?,

    @SerializedName("new_cases")
    val newCases: String?,

    @SerializedName("active_cases")
    val activeCases: String?,

    @SerializedName("total_deaths")
    val totalDeaths: String?,

    @SerializedName("new_deaths")
    val newDeaths: String?,

    @SerializedName("total_recovered")
    val totalRecovered: String?,

    @SerializedName("serious_critical")
    val seriousCritical: String?,

    val region: String? ,

    @SerializedName("total_cases_per1m")
    val totalCasesPer1M: String?,

    @SerializedName("record_date")
    val recordDate: String?,

    @SerializedName("deaths_per1m")
    val deathsPer1M: String?,

    @SerializedName("total_tests")
    val totalTests: String?,

    @SerializedName("total_tests_per1m")
    val totalTestsPer1M: String?,

    @SerializedName("record_date_pure")
    val recordDatePure: String?
)