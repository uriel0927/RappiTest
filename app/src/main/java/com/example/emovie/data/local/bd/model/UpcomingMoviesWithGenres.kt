package com.example.emovie.data.local.bd.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.emovie.data.local.bd.entities.*
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef

data class UpcomingMoviesWithGenres(
    @Embedded val movie: UpcomingMoviesEntity,
    @Relation(
        parentColumn = "idMovie",
        entityColumn = "idGenre",
        associateBy = Junction(UpcomingMoviesGenresCrossRef::class)
    )
    val genres: List<GenresEntity>
)