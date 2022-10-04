package com.example.emovie.data.local.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef

@Dao
interface LanguageCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguageCountry(languageCountry : LanguagesCountriesCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguagesCountries(languagesCountries : List<LanguagesCountriesCrossRef>)

    @Query("select * from LanguagesCountriesCrossRef")
    fun getLanguagesCountries() : List<LanguagesCountriesCrossRef>

    @Query("select * from LanguagesCountriesCrossRef where isoLanguage = :isoLanguage")
    fun getLanguagesCountriesByIsoLanguage(isoLanguage : String) : List<LanguagesCountriesCrossRef>

    @Query("delete from LanguagesCountriesCrossRef")
    fun deleteAllLanguageCountry()

}