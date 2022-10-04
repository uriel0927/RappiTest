package com.example.emovie.data.local

import com.example.emovie.data.local.bd.entities.*
import com.example.emovie.data.local.bd.model.LanguageWithCountries
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef
import com.example.emovie.data.model.UpcomingMovie

interface LocalDataSource {

    suspend fun insertCountry(country : CountriesEntity)

    suspend fun getCountries() : List<CountriesEntity>

    suspend fun getCountryById(iso : String) : CountriesEntity

    suspend fun deleteAllCountries()

    suspend fun insertGenre(genre : GenresEntity)

    suspend fun insertAllGenres(genre : List<GenresEntity>)

    suspend fun getAllGenres() : List<GenresEntity>

    suspend fun getGenresById(id : Int) : GenresEntity

    suspend fun deleteAllGenres()

    suspend fun insertLanguage(language : LanguagesEntity)

    suspend fun insertLanguages(languages : List<LanguagesEntity>)

    suspend fun getLanguages() : List<LanguagesEntity>

    suspend fun getLanguages(isoLanguage : String) : LanguagesEntity

    suspend fun deleteAllLanguage()

    suspend fun insertTopRatedMovie(topRatedMovie : TopRatedMoviesEntity)

    suspend fun insertTopRatedMovie(topRatedMovies : List<TopRatedMoviesEntity>)

    suspend fun getAllTopRatedMovies() : List<TopRatedMoviesEntity>

    suspend fun getAllTopRatedMoviesWithGenre() : List<TopRatedMoviesWithGenres>

    suspend fun getTopRatedMovieById(id : Int) : TopRatedMoviesEntity

    suspend fun deleteAllTopRatedMovies()

    suspend fun insertLanguageCountry(translation: LanguagesCountriesCrossRef)

    suspend fun insertLanguagesCountries(translations: List<LanguagesCountriesCrossRef>)

    suspend fun getAllLanguageCountries(): List<LanguageWithCountries>

    suspend fun deleteAllLanguagesCountries()

    suspend fun insertUpcomingMovie(upcomingMovie : UpcomingMoviesEntity)

    suspend fun insertUpcomingMovie(upcomingMovie : List<UpcomingMoviesEntity>)

    suspend fun getAllUpcomingMovies() : List<UpcomingMoviesEntity>

    suspend fun getAllUpcomingMoviesWithGenre() : List<UpcomingMoviesWithGenres>

    suspend fun getUpcomingMovieById(id : Int) : UpcomingMoviesEntity

    suspend fun deleteAllUpcomingMovies()

    suspend fun insertTopRatedMovieGenre(movierGenre: TopRatedMoviesGenresCrossRef)

    suspend fun insertUpcomingMovieGenre(movierGenre: UpcomingMoviesGenresCrossRef)

    suspend fun getYearsRelease(): List<String>

    suspend fun getAllTopRatedMoviesWithGenreFIltered(language : String) : List<TopRatedMoviesWithGenres>

}