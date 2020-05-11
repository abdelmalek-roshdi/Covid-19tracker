package com.health.covid19.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.health.covid19.enitites.Case

@Database(entities = [Case::class], version = 2, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun caseDao(): CaseDao
}
