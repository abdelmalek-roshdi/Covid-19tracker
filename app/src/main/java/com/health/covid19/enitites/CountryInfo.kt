package com.health.covid19.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "countryInfo_table")
data class CountryInfo (
    @field:Json(name = "_id")
    @PrimaryKey(autoGenerate = false)  val id: Long? = null,
    val iso2: String? = null,
    val iso3: String? = null,
    val lat: Double,
    val long: Double,
    val flag: String
)