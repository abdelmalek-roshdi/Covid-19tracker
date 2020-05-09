package com.health.covid19.net

import com.health.covid19.enitites.Case
import com.health.covid19.enitites.WorldWide
import retrofit2.http.GET
import retrofit2.http.Path

interface CaseApi {

   @GET("countries")
   suspend fun getCases(): List<Case>

   @GET("countries/{path}")
   suspend fun getCasesForCountryName(@Path("path") countryName: String): Case

   @GET("/all")
   suspend fun getCasesForWorldWide(): WorldWide
}