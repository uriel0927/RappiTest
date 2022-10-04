package com.example.emovie.domain

import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.domain.model.ResultUseCase

interface GetTopRatedMoviesUseCase {
    suspend fun execute(
        releaseDate: String = "",
        language: String = "en-US"
    ): ResultUseCase<List<TopRatedMovie>>
}