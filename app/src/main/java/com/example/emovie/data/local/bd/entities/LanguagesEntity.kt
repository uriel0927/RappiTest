package com.example.emovie.data.local.bd.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LanguagesEntity(
    @PrimaryKey
    val isoLanguage: String,
    val englishName: String,
    val name: String)
