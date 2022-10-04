package com.example.emovie.data.local.bd.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.emovie.data.local.bd.entities.CountriesEntity
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.example.emovie.data.local.bd.entities.LanguagesEntity

data class LanguageWithCountries(
    @Embedded val language: LanguagesEntity,
    @Relation(
        parentColumn = "isoLanguage",
        entityColumn = "isoCountry",
        associateBy = Junction(LanguagesCountriesCrossRef::class)
    )
    val countries: List<CountriesEntity>

)
