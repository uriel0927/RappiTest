package com.example.emovie.domain

import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.domain.model.ResultUseCase
import javax.inject.Inject

class GetTopRatedMoviesFilteredUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) :
    GetTopRatedMoviesFilteredUseCase {

    override suspend fun execute(
        language: String,
        releaseYear: String,
        limit: Int
    ): ResultUseCase<List<TopRatedMovie>> {

        try {
            val topRated = homeRepository.getTopRatedMoviesFilteredByLanguage(language)

            val filtered = if (releaseYear != "") {
                topRated.filter { it.releaseDate.split("-")[0] == releaseYear }
            } else {
                topRated
            }

            return ResultUseCase.Success(filtered.take(limit))

        } catch (ex: Throwable) {
            return  ResultUseCase.Failure("${ex.message}")
        }


    }

}