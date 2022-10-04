package com.example.emovie.data.local.bd.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopRatedMoviesEntity(
    @PrimaryKey
    val idMovie: Int,
    val language: String,
    val title: String,
    val overview: String,
    val posterPath : String,
    val backdropPath : String,
    val releaseDate : String,
    val voteAverage : Double
)
