package com.example.emovie.data.local

import com.example.emovie.data.local.bd.dao.*
import com.example.emovie.data.local.bd.entities.*
import com.example.emovie.data.local.bd.model.LanguageWithCountries
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val countriesDao: CountriesDao,
    private val genresDao: GenresDao,
    private val languagesDao: LanguagesDao,
    private val topRatedMoviesDao: TopRatedMoviesDao,
    private val topRatedMoviesGenresDao: TopRatedMoviesWithGenresDao,
    private val languageCountriesDao: LanguageCountriesDao,
    private val upcomingMoviesDao: UpcomingMoviesDao,
    private val upcomingMoviesGenreDao: UpcomingMoviesWithGenresDao
) : LocalDataSource {

    override suspend fun insertCountry(country: CountriesEntity) {
        withContext(Dispatchers.IO) {
            countriesDao.insert(country)
        }
    }

    override suspend fun getCountries(): List<CountriesEntity> {
        return withContext(Dispatchers.IO) {
            countriesDao.getCountries()
        }
    }

    override suspend fun getCountryById(iso: String): CountriesEntity {
        return withContext(Dispatchers.IO) {
            countriesDao.getCountries(iso)
        }
    }

    override suspend fun deleteAllCountries() {
        return withContext(Dispatchers.IO) {
            countriesDao.deleteAll()
        }
    }

    override suspend fun insertGenre(genre: GenresEntity) {
        withContext(Dispatchers.IO) {
            genresDao.insertGenre(genre)
        }
    }

    override suspend fun insertAllGenres(genres: List<GenresEntity>) {
        withContext(Dispatchers.IO) {
            genresDao.insertAllGenres(genres)
        }
    }

    override suspend fun getAllGenres(): List<GenresEntity> {
        return withContext(Dispatchers.IO) {
            genresDao.getAllGenres()
        }
    }

    override suspend fun getGenresById(id: Int): GenresEntity {
        return withContext(Dispatchers.IO) {
            genresDao.getGenresById(id)
        }
    }

    override suspend fun deleteAllGenres() {
        withContext(Dispatchers.IO) {
            genresDao.deleteAllGenres()
        }
    }

    override suspend fun insertLanguage(language: LanguagesEntity) {
        withContext(Dispatchers.IO) {
            languagesDao.insertLanguage(language)
        }
    }

    override suspend fun insertLanguages(languages: List<LanguagesEntity>) {
        withContext(Dispatchers.IO) {
            languagesDao.insertLanguages(languages)
        }
    }

    override suspend fun getLanguages(): List<LanguagesEntity> {
        return withContext(Dispatchers.IO) {
            languagesDao.getLanguages()
        }
    }

    override suspend fun getLanguages(isoLanguage: String): LanguagesEntity {
        return withContext(Dispatchers.IO) {
            languagesDao.getLanguageByIsoLanguage(isoLanguage)
        }
    }

    override suspend fun deleteAllLanguage() {
        withContext(Dispatchers.IO) {
            languagesDao.deleteAllLanguages()
        }
    }

    override suspend fun insertTopRatedMovie(topRatedMovie: TopRatedMoviesEntity) {
        withContext(Dispatchers.IO) {
            topRatedMoviesDao.insertTopRatedMovie(topRatedMovie)
        }
    }

    override suspend fun insertTopRatedMovie(topRatedMovies: List<TopRatedMoviesEntity>) {
        withContext(Dispatchers.IO) {
            topRatedMoviesDao.insertTopRatedMovie(topRatedMovies)
        }
    }

    override suspend fun getAllTopRatedMovies(): List<TopRatedMoviesEntity> {
        return withContext(Dispatchers.IO) {
            topRatedMoviesDao.getAllTopRatedMovies()
        }
    }

    override suspend fun getAllTopRatedMoviesWithGenre(): List<TopRatedMoviesWithGenres> {
        return withContext(Dispatchers.IO) {
            topRatedMoviesDao.getMoviesWithGenres()
        }
    }

    override suspend fun getTopRatedMovieById(id: Int): TopRatedMoviesEntity {
        return withContext(Dispatchers.IO) {
            topRatedMoviesDao.getTopRatedMovieById(id)
        }
    }

    override suspend fun deleteAllTopRatedMovies() {
        withContext(Dispatchers.IO) {
            topRatedMoviesDao.deleteAllTopRatedMovies()
        }
    }

    override suspend fun insertLanguageCountry(translation: LanguagesCountriesCrossRef) {
        withContext(Dispatchers.IO) {
            languageCountriesDao.insertLanguageCountry(translation)
        }
    }

    override suspend fun insertLanguagesCountries(translations: List<LanguagesCountriesCrossRef>) {
        withContext(Dispatchers.IO) {
            languageCountriesDao.insertLanguagesCountries(translations)
        }
    }

    override suspend fun getAllLanguageCountries(): List<LanguageWithCountries> {
        return withContext(Dispatchers.IO) {
            languagesDao.getLanguagesWithCountries()
        }
    }

    override suspend fun deleteAllLanguagesCountries() {
        withContext(Dispatchers.IO) {
            languageCountriesDao.deleteAllLanguageCountry()
        }
    }

    override suspend fun insertUpcomingMovie(upcomingMovie: UpcomingMoviesEntity) {
        withContext(Dispatchers.IO) {
            upcomingMoviesDao.insertUpcomingMovie(upcomingMovie)
        }
    }

    override suspend fun insertUpcomingMovie(upcomingMovies: List<UpcomingMoviesEntity>) {
        withContext(Dispatchers.IO) {
            upcomingMoviesDao.insertUpcomingMovie(upcomingMovies)
        }
    }

    override suspend fun getAllUpcomingMovies(): List<UpcomingMoviesEntity> {
        return withContext(Dispatchers.IO) {
            upcomingMoviesDao.getAllUpcomingMovies()
        }
    }

    override suspend fun getAllUpcomingMoviesWithGenre(): List<UpcomingMoviesWithGenres> {
        return withContext(Dispatchers.IO) {
            upcomingMoviesDao.getUpcomingMoviesWithGenres()
        }
    }

    override suspend fun getUpcomingMovieById(id: Int): UpcomingMoviesEntity {
        return withContext(Dispatchers.IO) {
            upcomingMoviesDao.getTopUpcomingById(id)
        }
    }

    override suspend fun deleteAllUpcomingMovies() {
        withContext(Dispatchers.IO) {
            upcomingMoviesDao.deleteAllUpcomingMovies()
        }
    }

    override suspend fun insertTopRatedMovieGenre(movieGenre: TopRatedMoviesGenresCrossRef) {
        withContext(Dispatchers.IO) {
            topRatedMoviesGenresDao.insertTopRatedMovieWithGenre(movieGenre)
        }
    }

    override suspend fun insertUpcomingMovieGenre(movierGenre: UpcomingMoviesGenresCrossRef) {
        withContext(Dispatchers.IO) {
            upcomingMoviesGenreDao.insertUpcomingMovieWithGenre(movierGenre)
        }
    }

    override suspend fun getYearsRelease(): List<String> {
        return withContext(Dispatchers.IO) {
            listOf(
                "2000",
                "2001",
                "2002",
                "2003",
                "2004",
                "2005",
                "2006",
                "2007",
                "2008",
                "2009",
                "2010",
                "2011",
                "2012",
                "2013",
                "2014",
                "2015",
                "2016",
                "2017",
                "2018",
                "2019",
                "2020",
                "2021",
                "2022"
            )

        }
    }

    override suspend fun getAllTopRatedMoviesWithGenreFIltered(language: String): List<TopRatedMoviesWithGenres> {
        return withContext(Dispatchers.IO){
            topRatedMoviesDao.getMoviesWithGenresFiltred(language)
        }
    }

}