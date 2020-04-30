package com.health.covid19.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "case_table")
data class Case (
   val updated: Long,
    @PrimaryKey(autoGenerate = false)val country: String,
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
   val continent: Continent
)

