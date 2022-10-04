package com.example.emovie.domain

import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.local.bd.entities.UpcomingMoviesEntity
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres
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
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetUpcomingMoviesUseCaseImplTest {

    @Mock
    private lateinit var homeRepository: HomeRepository

    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCaseImpl

    @Before
    fun setup() {
        getUpcomingMoviesUseCase = GetUpcomingMoviesUseCaseImpl(homeRepository)
    }

    @Test
    fun `Return the upcoming movies`() {
        runBlocking {
            val expected =
                listOf(
                    UpcomingMoviesWithGenres(
                        UpcomingMoviesEntity(
                            idMovie = 1,
                            language = "en",
                            title = "",
                            overview = "",
                            posterPath = "",
                            backdropPath = "",
                            releaseDate = "",
                            voteAverage = 0.0
                        ), genres = listOf(GenresEntity(1, "Drama"), GenresEntity(2, "Action"))
                    )
                )

            Mockito.`when`(homeRepository.getUpcomingMovies()).thenReturn(
                listOf(
                    UpcomingMoviesWithGenres(
                        UpcomingMoviesEntity(
                            idMovie = 1,
                            language = "en",
                            title = "",
                            overview = "",
                            posterPath = "",
                            backdropPath = "",
                            releaseDate = "",
                            voteAverage = 0.0
                        ), genres = listOf(GenresEntity(1, "Drama"), GenresEntity(2, "Action"))
                    )
                )
            )

            val data = getUpcomingMoviesUseCase.execute()
            val result = when (data) {
                is ResultUseCase.Failure -> data.messageError
                is ResultUseCase.Success -> data.data
            }

            verify(homeRepository).getUpcomingMovies()

            Truth.assertThat(data is ResultUseCase.Success).isTrue()
            Truth.assertThat((result as List<UpcomingMoviesWithGenres>).containsAll(expected))
                .isTrue()

        }
    }
}