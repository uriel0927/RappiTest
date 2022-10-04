package com.example.emovie.domain

import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.model.UpcomingMovie
import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.domain.model.ResultUseCase
import javax.inject.Inject

class GetUpcomingMoviesUseCaseImpl @Inject constructor(private val repository: HomeRepository)  : GetUpcomingMoviesUseCase{

    override suspend fun execute(language : String): ResultUseCase<List<UpcomingMovie>> {

        return try{
            ResultUseCase.Success(repository.getUpcomingMovies(language))
        }catch (ex : Throwable){
            ResultUseCase.Failure(ex.message ?: "")
        }


    }
}