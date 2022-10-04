package com.example.emovie.data.local.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emovie.data.local.bd.entities.CountriesEntity

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country : CountriesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country : List<CountriesEntity>)

    @Query("select * from CountriesEntity")
    fun getCountries() : List<CountriesEntity>

    @Query("select * from CountriesEntity where isoCountry = :iso")
    fun getCountries(iso : String) : CountriesEntity

    @Query("delete from CountriesEntity")
    fun deleteAll()

}