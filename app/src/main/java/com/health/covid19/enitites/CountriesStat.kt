package com.health.covid19.enitites

import com.google.gson.annotations.SerializedName

data class CountriesStat (
    @SerializedName("country_name")
    val countryName: String,

    val cases: String?,
    val deaths: String?,
    val region: String?,

    @SerializedName("total_recovered")
    val totalRecovered: String?,

    @SerializedName("new_deaths")
    val newDeaths: String?,

    @SerializedName("new_cases")
    val newCases: String?,

    @SerializedName("serious_critical")
    val seriousCritical: String?,

    @SerializedName("active_cases")
    val activeCases: String?,

    @SerializedName("total_cases_per_1m_population")
    val totalCasesPer1MPopulation: String?,

    @SerializedName("deaths_per_1m_population")
    val deathsPer1MPopulation: String?,

    @SerializedName("total_tests")
    val totalTests: String?,

    @SerializedName("tests_per_1m_population")
    val testsPer1MPopulation: String?
)