package com.example.emovie.data.local.bd.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.entities.TopRatedMoviesEntity


data class TopRatedMoviesWithGenres(
    @Embedded val movie: TopRatedMoviesEntity,
    @Relation(
        parentColumn = "idMovie",
        entityColumn = "idGenre",
        associateBy = Junction(TopRatedMoviesGenresCrossRef::class)
    )
    val genres : List<GenresEntity>
)