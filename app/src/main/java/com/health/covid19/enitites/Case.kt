package com.health.covid19.enitites

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "case_table")
data class Case (
    @PrimaryKey
    val country: String = "",
    var updated: Long = 0,
    @Embedded
    var countryInfo: CountryInfo = CountryInfo(),
    var cases: Long = 0,
    var todayCases: Long = 0,
    var deaths: Long = 0,
    var todayDeaths: Long = 0,
    var recovered: Long = 0,
    var active: Long = 0,
    var critical: Long = 0,
    var casesPerOneMillion: Long = 0,
    var deathsPerOneMillion: Long = 0,
    var tests: Long = 0,
    var testsPerOneMillion: Long = 0,
    var continent: String= "",
    var isSubcribed: Boolean = false
)

