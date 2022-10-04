package com.example.emovie.data.local.bd.relations

import androidx.room.Entity

@Entity(primaryKeys = ["idMovie", "idGenre"])
data class TopRatedMoviesGenresCrossRef(
    val idMovie: Int,
    val idGenre: Int)
