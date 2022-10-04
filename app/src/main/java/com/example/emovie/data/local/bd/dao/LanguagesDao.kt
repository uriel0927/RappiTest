package com.example.emovie.data.local.bd.dao

import androidx.room.*
import com.example.emovie.data.local.bd.entities.LanguagesEntity
import com.example.emovie.data.local.bd.model.LanguageWithCountries

@Dao
interface LanguagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguage(language : LanguagesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguages(languages : List<LanguagesEntity>)

    @Query("select * from LanguagesEntity")
    fun getLanguages():List<LanguagesEntity>

    @Query("select * from LanguagesEntity where isoLanguage = :isoLanguage")
    fun getLanguageByIsoLanguage(isoLanguage : String):LanguagesEntity

    @Query("delete from LanguagesEntity")
    fun deleteAllLanguages()

    @Transaction
    @Query("select * from LanguagesEntity order by isoLanguage ASC")
    fun getLanguagesWithCountries() : List<LanguageWithCountries>



}