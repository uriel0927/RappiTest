package com.example.emovie.data.local.bd.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountriesEntity(
    @PrimaryKey
    val isoCountry : String,
    val englishName : String,
    val nativeName : String
)
