package com.health.covid19.net

import com.health.covid19.enitites.Case
import retrofit2.Call
import retrofit2.http.GET

interface CaseApi {

   @GET("countries")
   fun getCases(): Call<ArrayList<Case>>
}