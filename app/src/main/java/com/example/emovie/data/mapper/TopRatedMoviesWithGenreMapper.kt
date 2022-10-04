package com.example.emovie.data.mapper

import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.model.Genres
import com.example.emovie.data.model.TopRatedMovie

fun TopRatedMoviesWithGenres.toTopRatedMovie(): TopRatedMovie {

    val genres = mutableListOf<Genres>()

    this.genres.forEach {
        genres.add(Genres(id = it.idGenre, name = it.description))
    }

    return TopRatedMovie(
        id = this.movie.idMovie,
        language = this.movie.language,
        genresId = genres,
        tittle = this.movie.title,
        overview = this.movie.overview,
        backdropPath = this.movie.backdropPath,
        posterPath = this.movie.posterPath,
        releaseDate = this.movie.releaseDate,
        video = false,
        voteAverage = this.movie.voteAverage
    )
}