package com.example.emovie.data.repository

import com.example.emovie.data.local.bd.model.LanguageWithCountries
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.model.*

interface HomeRepository {

    suspend fun getTopRatedMovies(language: String = "en-US"): List<TopRatedMovie>

    suspend fun getUpcomingMovies(language: String = "en-US"): List<UpcomingMovie>

    suspend fun getTranslations(): List<LanguageWithCountries>

    suspend fun getGenresMovies(language: String = "en-US"): List<Genres>

    suspend fun downloadCountriesConfiguration()

    suspend fun downloadLanguageConfiguration()

    suspend fun getYearsRelease() : List<String>

    suspend fun getTopRatedMoviesFilteredByLanguage(language : String) : List<TopRatedMovie>

}