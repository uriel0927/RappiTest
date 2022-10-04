package com.example.emovie.data.local.bd.relations

import androidx.room.Entity

@Entity(primaryKeys = ["isoLanguage","isoCountry"])
data class LanguagesCountriesCrossRef(
    val isoLanguage :String,
    val isoCountry: String)
