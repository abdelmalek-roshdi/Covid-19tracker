package com.health.covid19.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.health.covid19.enitites.Case

@Dao
interface CaseDao {

    @Query("SELECT * FROM case_table")
    fun getAllCases(): LiveData<List<Case>>

    @Query("SELECT * FROM case_table where isSubcribed")
    fun getSubcsribesCase(): LiveData<List<Case>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(case: Case)

    @Query("DELETE FROM case_table")
    suspend fun deleteAll()

    @Query("DELETE FROM case_table where country = :countryName")
    suspend fun deleteByCountryName(countryName: String)

    @Update
    suspend fun update(case: Case)
}


