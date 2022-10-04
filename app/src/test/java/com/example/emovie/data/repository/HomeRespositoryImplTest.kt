package com.example.emovie.data.repository

import com.example.emovie.data.local.LocalDataSource
import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef
import com.example.emovie.data.mapper.toTopRatedEntity
import com.example.emovie.data.mapper.toUpcomingMovieEntity
import com.example.emovie.data.network.RemoteDataSource
import com.example.emovie.data.network.model.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.Times
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class HomeRespositoryImplTest {

    private lateinit var homeRepositoryImpl: HomeRepositoryImpl

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        homeRepositoryImpl = HomeRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `Return list of top rated movies when remote data response success`() {
        runBlocking {

            val movie = ResultTopRatedMoviesApi(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2, 3),
                id = 1,
                originalLanguage = "en",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0
            )

            val topRatedList = listOf(movie).map { it.toTopRatedEntity() }

            Mockito.`when`(remoteDataSource.getTopRatedMovies("en-US"))
                .thenReturn(
                    NetworkResult<TopRatedMoviesApi>(
                        result = TopRatedMoviesApi(
                            page = 1, results = listOf(
                                movie
                            ),
                            total_pages = 1,
                            total_results = 1
                        )
                    )
                )

            homeRepositoryImpl.getTopRatedMovies()

            verify(remoteDataSource).getTopRatedMovies("en-US")
            verify(localDataSource, times(1)).insertTopRatedMovie(topRatedList)
            verify(localDataSource, times(1)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(1, 1)
            )
            verify(localDataSource, times(1)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(
                    1,
                    2
                )
            )
            verify(localDataSource, times(1)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(
                    1,
                    3
                )
            )

            verify(localDataSource).getAllTopRatedMoviesWithGenre()


        }
    }

    @Test
    fun `Return list of top rated movies when remote data response error API`() {
        runBlocking {

            val movie = ResultTopRatedMoviesApi(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2, 3),
                id = 1,
                originalLanguage = "en",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0
            )

            val topRatedList = listOf(movie).map { it.toTopRatedEntity() }

            Mockito.`when`(remoteDataSource.getTopRatedMovies("en-US"))
                .thenReturn(
                    NetworkResult<TopRatedMoviesApi>(
                        networkError = NetworkError(
                            NetworkErrorType.API_ERROR,
                            rawError = "Ocurrio un error",
                            errorCode = ""
                        )
                    )
                )

            homeRepositoryImpl.getTopRatedMovies()

            verify(remoteDataSource).getTopRatedMovies("en-US")
            verify(localDataSource, times(0)).insertTopRatedMovie(topRatedList)
            verify(localDataSource, times(0)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(1, 1)
            )
            verify(localDataSource, times(0)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(
                    1,
                    2
                )
            )
            verify(localDataSource, times(0)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(
                    1,
                    3
                )
            )

            verify(localDataSource).getAllTopRatedMoviesWithGenre()


        }
    }

    @Test
    fun `Return list of top rated movies when remote data response CONNECTION ERROR`() {
        runBlocking {

            val movie = ResultTopRatedMoviesApi(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2, 3),
                id = 1,
                originalLanguage = "en",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0
            )

            val topRatedList = listOf(movie).map { it.toTopRatedEntity() }

            Mockito.`when`(remoteDataSource.getTopRatedMovies("en-US"))
                .thenReturn(
                    NetworkResult<TopRatedMoviesApi>(
                        networkError = NetworkError(
                            NetworkErrorType.CONNECTION_ERROR,
                            rawError = "Ocurrio un error",
                            errorCode = ""
                        )
                    )
                )

            homeRepositoryImpl.getTopRatedMovies()

            verify(remoteDataSource).getTopRatedMovies("en-US")
            verify(localDataSource, times(0)).insertTopRatedMovie(topRatedList)
            verify(localDataSource, times(0)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(1, 1)
            )
            verify(localDataSource, times(0)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(
                    1,
                    2
                )
            )
            verify(localDataSource, times(0)).insertTopRatedMovieGenre(
                TopRatedMoviesGenresCrossRef(
                    1,
                    3
                )
            )

            verify(localDataSource).getAllTopRatedMoviesWithGenre()


        }
    }

    @Test
    fun `Return list of upcoming rated movies when remote data response success`() {
        runBlocking {

            val movie = ResultUpcomingMoviesApi(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2, 3),
                id = 1,
                originalLanguage = "en",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0
            )

            val upComingMovies = listOf(movie).map { it.toUpcomingMovieEntity() }

            Mockito.`when`(remoteDataSource.getUpcomingMovies("en-US"))
                .thenReturn(
                    NetworkResult<UpcomingMoviesApi>(
                        result = UpcomingMoviesApi(
                            page = 1, results = listOf(
                                movie
                            ), dates = DatesApi("", ""), totalPages = 1, totalResults = 0
                        )
                    )
                )

            homeRepositoryImpl.getUpcomingMovies()

            verify(remoteDataSource).getUpcomingMovies("en-US")
            verify(localDataSource, times(1)).insertUpcomingMovie(upComingMovies)
            verify(localDataSource, times(1)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    1
                )
            )
            verify(localDataSource, times(1)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    2
                )
            )
            verify(localDataSource, times(1)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    3
                )
            )

            verify(localDataSource).getAllUpcomingMoviesWithGenre()


        }
    }

    @Test
    fun `Return list of upcoming movies when remote data response error API`() {
        runBlocking {

            val movie = ResultUpcomingMoviesApi(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2, 3),
                id = 1,
                originalLanguage = "en",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0
            )

            val upComingList = listOf(movie).map { it.toUpcomingMovieEntity() }

            Mockito.`when`(remoteDataSource.getUpcomingMovies("en-US"))
                .thenReturn(
                    NetworkResult<UpcomingMoviesApi>(
                        networkError = NetworkError(
                            NetworkErrorType.API_ERROR,
                            rawError = "Ocurrio un error",
                            errorCode = ""
                        )
                    )
                )

            homeRepositoryImpl.getUpcomingMovies()

            verify(remoteDataSource).getUpcomingMovies("en-US")
            verify(localDataSource, times(0)).insertUpcomingMovie(upComingList)
            verify(localDataSource, times(0)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(1, 1)
            )
            verify(localDataSource, times(0)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    2
                )
            )
            verify(localDataSource, times(0)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    3
                )
            )

            verify(localDataSource).getAllUpcomingMoviesWithGenre()


        }
    }

    @Test
    fun `Return list of upcoming movies when remote data response CONNECTION ERROR`() {
        runBlocking {

            val movie = ResultUpcomingMoviesApi(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2, 3),
                id = 1,
                originalLanguage = "en",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0
            )

            val topRatedList = listOf(movie).map { it.toUpcomingMovieEntity() }

            Mockito.`when`(remoteDataSource.getUpcomingMovies("en-US"))
                .thenReturn(
                    NetworkResult<UpcomingMoviesApi>(
                        networkError = NetworkError(
                            NetworkErrorType.CONNECTION_ERROR,
                            rawError = "Ocurrio un error",
                            errorCode = ""
                        )
                    )
                )

            homeRepositoryImpl.getUpcomingMovies()

            verify(remoteDataSource).getUpcomingMovies("en-US")
            verify(localDataSource, times(0)).insertUpcomingMovie(topRatedList)
            verify(localDataSource, times(0)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(1, 1)
            )
            verify(localDataSource, times(0)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    2
                )
            )
            verify(localDataSource, times(0)).insertUpcomingMovieGenre(
                UpcomingMoviesGenresCrossRef(
                    1,
                    3
                )
            )

            verify(localDataSource).getAllUpcomingMoviesWithGenre()


        }
    }

    @Test
    fun `Return list of translations movies`() {

        runBlocking {

            Mockito.`when`(remoteDataSource.getPrimaryTranslations()).thenReturn(
                NetworkResult(
                    result = listOf(
                        "en-US", "en-CA"
                    )
                )
            )

            homeRepositoryImpl.getTranslations()

            verify(remoteDataSource).getPrimaryTranslations()

            verify(localDataSource, times(1)).insertLanguageCountry(
                LanguagesCountriesCrossRef(
                    "en",
                    "US"
                )
            )
            verify(localDataSource, times(1)).insertLanguageCountry(
                LanguagesCountriesCrossRef(
                    "en",
                    "CA"
                )
            )

            verify(localDataSource).getAllLanguageCountries()
        }

    }

    @Test
    fun `Return list of genres movies`() {
        runBlocking {

            Mockito.`when`(remoteDataSource.getGenresMovies("en-US")).thenReturn(
                NetworkResult(
                    result = ResponseGenresApi(
                        genres = listOf<GenresApi>(
                            GenresApi(2, "Drama"),
                            GenresApi(3, "fiction")
                        )
                    )
                )
            )

            Mockito.`when`(localDataSource.getAllGenres())
                .thenReturn(listOf(GenresEntity(2, "Drama"), GenresEntity(3, "Fiction")))

            homeRepositoryImpl.getGenresMovies()

            verify(localDataSource, Times(1)).insertGenre(GenresEntity(2, "Drama"))
            verify(localDataSource, Times(1)).insertGenre(GenresEntity(3, "fiction"))

            verify(localDataSource).getAllGenres()

        }

    }

}