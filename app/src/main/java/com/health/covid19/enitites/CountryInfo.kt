package com.health.covid19.enitites

import com.squareup.moshi.Json

data class CountryInfo (
    @field:Json(name = "_id")
    val id: Long? = null,
    val iso2: String? = null,
    val iso3: String? = null,
    val lat: Double,
    val long: Double,
    val flag: String
)