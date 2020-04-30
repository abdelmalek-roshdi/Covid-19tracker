package com.health.covid19.enitites

data class Case (
    val updated: Long,
    val country: String,
    val countryInfo: CountryInfo,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Long,
    val deathsPerOneMillion: Long,
    val tests: Long,
    val testsPerOneMillion: Long,
    val continent: String
)

