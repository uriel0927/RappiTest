package com.example.emovie.data.mapper

import com.example.emovie.data.local.bd.entities.TopRatedMoviesEntity
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.network.model.ResultTopRatedMoviesApi



fun ResultTopRatedMoviesApi.toTopRatedEntity(): TopRatedMoviesEntity {
    return TopRatedMoviesEntity(
        idMovie = this.id,
        language = this.originalLanguage,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )
}
