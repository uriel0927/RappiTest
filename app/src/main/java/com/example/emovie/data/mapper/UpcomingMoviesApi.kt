package com.example.emovie.data.mapper

import com.example.emovie.data.local.bd.entities.UpcomingMoviesEntity
import com.example.emovie.data.network.model.ResultUpcomingMoviesApi

fun ResultUpcomingMoviesApi.toUpcomingMovieEntity(): UpcomingMoviesEntity {

    return UpcomingMoviesEntity(
        idMovie = this.id,
        language = this.originalLanguage,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        releaseDate =  this.releaseDate,
        voteAverage = this.voteAverage
    )

}