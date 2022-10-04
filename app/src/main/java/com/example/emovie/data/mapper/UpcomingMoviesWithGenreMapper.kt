package com.example.emovie.data.mapper


import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.model.Genres
import com.example.emovie.data.model.UpcomingMovie

fun UpcomingMoviesWithGenres.toUpcomingMovie(): UpcomingMovie {

    val genres = mutableListOf<Genres>()

    this.genres.forEach {
        genres.add(Genres(id = it.idGenre, name = it.description))
    }

    return UpcomingMovie(
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