package com.example.emovie.domain

import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.domain.model.ResultUseCase
import javax.inject.Inject

class GetTopRatedMoviesUseCaseImpl @Inject constructor(private val repository: HomeRepository) :
    GetTopRatedMoviesUseCase {

    override suspend fun execute(
        releaseDate: String,
        language: String
    ): ResultUseCase<List<TopRatedMovie>> {
        return try{
            val dataMoviesRepository = repository.getTopRatedMovies(language = language)

            val topRatedMovies = if (releaseDate != "") {
                dataMoviesRepository.filter { it.releaseDate == releaseDate }
            } else {
                dataMoviesRepository
            }

            ResultUseCase.Success(topRatedMovies)

        }catch(ex : Throwable){
            ResultUseCase.Failure(ex.message ?: "")
        }

    }

}