package com.example.emovie.domain

import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.model.UpcomingMovie
import com.example.emovie.domain.model.ResultUseCase

interface GetUpcomingMoviesUseCase {

    suspend fun execute(language : String = "en-US") : ResultUseCase<List<UpcomingMovie>>

}