package com.example.emovie.domain

import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.local.bd.entities.TopRatedMoviesEntity
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
import com.example.emovie.data.model.Genres
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.domain.model.ResultUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetTopRatedMoviesUseCaseImplTest {

    @Mock
    private lateinit var homeRepository: HomeRepository

    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCaseImpl

    @Before
    fun setUp() {
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCaseImpl(homeRepository)
    }

    @Test
    fun `Return top rated movies without release date`() {

        runBlocking {
            val expected = listOf(
                TopRatedMovie(
                    id = 1,
                    language = "en",
                    tittle = "",
                    overview = "",
                    posterPath = "",
                    backdropPath = "",
                    releaseDate = "",
                    voteAverage = 0.0,
                    genresId = listOf(Genres(1, "Drama")),
                    video = false
                ),
                TopRatedMovie(
                    id = 2,
                    language = "en",
                    tittle = "",
                    overview = "",
                    posterPath = "",
                    backdropPath = "",
                    releaseDate = "",
                    voteAverage = 0.0,
                    genresId = listOf(Genres(1, "Action")),
                    video = false
                )
            )

            Mockito.`when`(homeRepository.getTopRatedMovies("en-US")).thenReturn(
                listOf(
                    TopRatedMovie(
                        id = 1,
                        language = "en",
                        tittle = "",
                        overview = "",
                        posterPath = "",
                        backdropPath = "",
                        releaseDate = "",
                        voteAverage = 0.0,
                        genresId = listOf(Genres(1, "Drama")),
                        video = false
                    ),
                    TopRatedMovie(
                        id = 2,
                        language = "en",
                        tittle = "",
                        overview = "",
                        posterPath = "",
                        backdropPath = "",
                        releaseDate = "",
                        voteAverage = 0.0,
                        genresId = listOf(Genres(1, "Action")),
                        video = false
                    )
                )
            )

            val resultUseCase = getTopRatedMoviesUseCase.execute("", "en-US")

            val data = when (resultUseCase) {
                is ResultUseCase.Failure -> resultUseCase.messageError
                is ResultUseCase.Success -> resultUseCase.data
            }

            Truth.assertThat(resultUseCase is ResultUseCase.Success).isTrue()

            Truth.assertThat((data as List<TopRatedMovie>).containsAll(expected))
                .isTrue()

        }


    }

    @Test
    fun `Return top rated movies with release date`() {

        runBlocking {
            val expected = listOf(
                TopRatedMovie(
                    id = 2,
                    language = "en",
                    tittle = "",
                    overview = "",
                    posterPath = "",
                    backdropPath = "",
                    releaseDate = "2009",
                    voteAverage = 0.0,
                    genresId = listOf(Genres(1, "Action")),
                    video = false
                )
            )

            Mockito.`when`(homeRepository.getTopRatedMovies("en-US")).thenReturn(
                listOf(
                    TopRatedMovie(
                        id = 1,
                        language = "en",
                        tittle = "",
                        overview = "",
                        posterPath = "",
                        backdropPath = "",
                        releaseDate = "2010",
                        voteAverage = 0.0,
                        genresId = listOf(Genres(1, "Drama")),
                        video = false
                    ),
                    TopRatedMovie(
                        id = 2,
                        language = "en",
                        tittle = "",
                        overview = "",
                        posterPath = "",
                        backdropPath = "",
                        releaseDate = "2009",
                        voteAverage = 0.0,
                        genresId = listOf(Genres(1, "Action")),
                        video = false
                    )
                )
            )

            val resultUseCase = getTopRatedMoviesUseCase.execute("2009", "en-US")

            val data = when (resultUseCase) {
                is ResultUseCase.Failure -> resultUseCase.messageError
                is ResultUseCase.Success -> resultUseCase.data
            }


            verify(homeRepository, times(1)).getTopRatedMovies("en-US")
            Truth.assertThat(resultUseCase is ResultUseCase.Success).isTrue()

            Truth.assertThat((data as List<TopRatedMovie>).containsAll(expected))
                .isTrue()


        }


    }
}