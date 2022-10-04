package com.example.emovie.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpcomingMovie(
    val id: Int,
    val genresId: List<Genres>,
    val language : String,
    val tittle: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double
) : Parcelable