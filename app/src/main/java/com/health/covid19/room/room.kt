package com.health.covid19.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.health.covid19.enitites.Case
import com.health.covid19.enitites.CountryInfo
import java.util.ArrayList

class LocalData(application: Application){
    private val caseDao: CaseDao
    private val countryInfoDao: CountryInfoDao

    init {
       val DB=LocalDB.DB.getInstance(application)
        this.caseDao=DB.caseDao()
        this.countryInfoDao=DB.countryInfoDao()
    }

    suspend fun allCases():LiveData<List<Case>>{
        return caseDao.getAllCases()
    }

    suspend fun getCountryInfo(Id:Long):LiveData<List<CountryInfo>>{
        return countryInfoDao.getcountryInfo(countryId = Id)
    }
   suspend fun InsertCase(case:Case){
       caseDao.insert(case = case)
   }
    suspend fun insertCountryInfo(countryinfo:CountryInfo){
       countryInfoDao.insert(countryInfo = countryinfo)
    }
    suspend fun deleteAllCases(){
        caseDao.deleteAll()
    }
    suspend fun deleteCountryInfo(){
        countryInfoDao.deleteAll()
    }
    suspend fun updatecase(case:Case){
        caseDao.update(case = case)
    }
    suspend fun updateCountryInfo(info:CountryInfo){
        countryInfoDao.update(countryInfo = info)
    }

}



//________________________________________
@Database(entities = [Case::class,CountryInfo::class], version = 1)
@TypeConverters(IntegerListTypeConverter::class)
abstract class LocalDB:RoomDatabase(){

    abstract fun caseDao():CaseDao
    abstract fun countryInfoDao():CountryInfoDao

    object DB{
        private val DB_NAME="local_database"
        private var INSTANCE: LocalDB? = null

        fun getInstance(application: Application):LocalDB{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(application,LocalDB::class.java, DB_NAME).build()
                return INSTANCE as LocalDB
                Log.i("TAG","Room DB Built")
            }
            else return INSTANCE as LocalDB
        }
    }
}






//_______________________________________________
class IntegerListTypeConverter {
    @TypeConverter
    fun stringToIntegertList(data: String?): List<Int>? {
        val gson = Gson()
        if (data != null) {
            if (data.length != 0 && data != "null") {
                val listType = object : TypeToken<List<Int>>() {

                }.type
                return gson.fromJson<List<Int>>(data, listType)
            }
            return ArrayList()
        }

        val var4 = false
        return ArrayList()
    }

    @TypeConverter
    fun integertListToString(integers: List<Int>): String {
        val gson = Gson()
        return gson.toJson(integers)
    }
}