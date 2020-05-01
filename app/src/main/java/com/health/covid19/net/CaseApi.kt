package com.health.covid19.net

import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CaseApi {

   @GET("countries")
   suspend fun getCases(): List<Case>

   @GET("countries/{path}")
   suspend fun getCasesForCountryName(@Path("path") countryName: String): Case
}