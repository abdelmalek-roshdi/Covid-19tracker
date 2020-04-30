package com.health.covid19.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import java.util.*

@Dao
interface CaseDao{


    @Query("SELECT * FROM case_table")
    suspend fun  getAllCases(): LiveData<List<Case>>

    @Insert(onConflict = REPLACE)
    suspend  fun insert(case: Case)


    @Query("DELETE FROM case_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(case: Case)
}


@Dao
interface CountryInfoDao {


    @Query("SELECT * FROM countryInfo_table WHERE id = :countryId")
    suspend fun getcountryInfo(countryId:Long): LiveData<List<CountryInfo>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(countryInfo: CountryInfo)


    @Query("DELETE FROM countryInfo_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(countryInfo: CountryInfo)

}


