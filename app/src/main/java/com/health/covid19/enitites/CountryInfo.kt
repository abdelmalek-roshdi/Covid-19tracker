package com.health.covid19.enitites


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class CountryInfo (
    @field:Json(name = "_id")
    @SerializedName("_id")
    var id: Long = 0,
    var iso2: String = "",
    var iso3: String = "",
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var flag: String= ""
)