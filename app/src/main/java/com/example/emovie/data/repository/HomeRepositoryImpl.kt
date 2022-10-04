package com.example.emovie.data.repository

import com.example.emovie.data.local.LocalDataSource
import com.example.emovie.data.local.bd.entities.CountriesEntity
import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.local.bd.entities.LanguagesEntity
import com.example.emovie.data.local.bd.model.LanguageWithCountries
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef
import com.example.emovie.data.mapper.*
import com.example.emovie.data.model.*
import com.example.emovie.data.network.RemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : HomeRepository {

    override suspend fun getTopRatedMovies(language: String): List<TopRatedMovie> {

        val remoteResult = remoteDataSource.getTopRatedMovies(language = language)

        if (!remoteResult.isError) {

            val insertData = remoteResult.requiredResult.results.map { topRatedMovieApi ->
                topRatedMovieApi.toTopRatedEntity()
            }

            localDataSource.insertTopRatedMovie(insertData)

            remoteResult.requiredResult.results.forEach { movie ->
                movie.genreIds.forEach { idGenre ->
                    localDataSource.insertTopRatedMovieGenre(
                        TopRatedMoviesGenresCrossRef(
                            movie.id,
                            idGenre
                        )
                    )
                }
            }

        }


        return localDataSource.getAllTopRatedMoviesWithGenre().map { it.toTopRatedMovie() }

    }

    override suspend fun getUpcomingMovies(language: String): List<UpcomingMovie> {

        val remoteResult = remoteDataSource.getUpcomingMovies(language)

        if (!remoteResult.isError) {

            val insertData = remoteResult.requiredResult.results.map { upcomingMovieApi ->
                upcomingMovieApi.toUpcomingMovieEntity()
            }

            localDataSource.insertUpcomingMovie(insertData)

            remoteResult.requiredResult.results.forEach { movie ->
                movie.genreIds.forEach { idGenre ->
                    localDataSource.insertUpcomingMovieGenre(
                        UpcomingMoviesGenresCrossRef(
                            movie.id,
                            idGenre
                        )
                    )
                }
            }

        }

        return localDataSource.getAllUpcomingMoviesWithGenre().map { it.toUpcomingMovie() }
    }

    override suspend fun getTranslations(): List<LanguageWithCountries> {
        val remoteResult = remoteDataSource.getPrimaryTranslations()

        if (!remoteResult.isError) {
            remoteResult.requiredResult.forEach { translation ->
                val splitTranslation = translation.split("-")

                val isoLanguage = splitTranslation[0]
                val isoCountry = splitTranslation[1]

                localDataSource.insertLanguageCountry(
                    LanguagesCountriesCrossRef(
                        isoLanguage,
                        isoCountry
                    )
                )
            }
        }

        return localDataSource.getAllLanguageCountries()

    }

    override suspend fun getGenresMovies(language: String): List<Genres> {

        val remoteResult = remoteDataSource.getGenresMovies(language)

        if (!remoteResult.isError) {
            remoteResult.requiredResult.genres.forEach {
                localDataSource.insertGenre(GenresEntity(idGenre = it.id, description = it.name))
            }
        }

        return localDataSource.getAllGenres().map { it.toGenres() }
    }

    override suspend fun downloadCountriesConfiguration() {
        val remoteResult = remoteDataSource.getCountries()

        if (!remoteResult.isError) {

            localDataSource.deleteAllCountries()

            remoteResult.requiredResult.forEach { country ->
                localDataSource.insertCountry(
                    CountriesEntity(
                        isoCountry = country.iso,
                        englishName = country.englishName,
                        nativeName = country.nativeName
                    )
                )
            }
        }

    }

    override suspend fun downloadLanguageConfiguration() {
        val remoteResult = remoteDataSource.getLanguages()

        if (remoteResult != null) {

            localDataSource.deleteAllLanguage()

            remoteResult.requiredResult.forEach { language ->
                localDataSource.insertLanguage(
                    LanguagesEntity(
                        isoLanguage = language.iso,
                        englishName = language.englishName,
                        name = language.nativeName
                    )
                )
            }

        }
    }

    override suspend fun getYearsRelease(): List<String> {
        return localDataSource.getYearsRelease()
    }

    override suspend fun getTopRatedMoviesFilteredByLanguage(language: String): List<TopRatedMovie> {

        val data = if (language != "") {
            localDataSource.getAllTopRatedMoviesWithGenreFIltered(language)
        } else {
            localDataSource.getAllTopRatedMoviesWithGenre()
        }

        return data.map { it.toTopRatedMovie() }
    }

}