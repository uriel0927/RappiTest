package com.example.emovie.data.local.bd.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenresEntity(
    @PrimaryKey
    val idGenre: Int,
    val description: String
)