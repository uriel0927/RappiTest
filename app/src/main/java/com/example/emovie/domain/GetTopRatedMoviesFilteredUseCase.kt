package com.example.emovie.domain

import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.domain.model.ResultUseCase

interface GetTopRatedMoviesFilteredUseCase {

    suspend fun execute(
        language: String,
        releaseYear: String,
        limit: Int
    ): ResultUseCase<List<TopRatedMovie>>
}