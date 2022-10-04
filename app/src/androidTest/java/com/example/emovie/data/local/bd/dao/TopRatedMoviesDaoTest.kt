package com.example.emovie.data.local.bd.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.emovie.data.local.bd.MoviesDatabase
import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.local.bd.entities.TopRatedMoviesEntity
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class TopRatedMoviesDaoTest {

    private lateinit var db: MoviesDatabase
    private lateinit var topRatedMoviesDao: TopRatedMoviesDao
    private lateinit var topRatedMoviesWithGenresDao: TopRatedMoviesWithGenresDao
    private lateinit var genresDao: GenresDao


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).build()
        topRatedMoviesDao = db.getTopRatedMoviesDao()
        genresDao = db.getGenresDao()
        topRatedMoviesWithGenresDao = db.getTopRatedMoviesWithGenresDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndSelectTopRatedMovieTest() {

        val topRatedMovie =
            TopRatedMoviesEntity(
                idMovie = 1,
                language = "en",
                title = "Movie Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 8.5
            )

        topRatedMoviesDao.insertTopRatedMovie(topRatedMovie)

        val topRatedMovieBD = topRatedMoviesDao.getTopRatedMovieById(1)

        assertThat(topRatedMovieBD == topRatedMovie).isTrue()

    }

    @Test
    fun insertAndSelectAllTopRatedMoviesTest() {

        val topRatedMovies = listOf(
            TopRatedMoviesEntity(
                idMovie = 1,
                language = "en",
                title = "Movie Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 8.5
            ),
            TopRatedMoviesEntity(
                idMovie = 2,
                language = "en",
                title = "Movie 2 Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 5.0
            )
        )

        topRatedMoviesDao.insertTopRatedMovie(topRatedMovies)

        val topRatedMoviesBD = topRatedMoviesDao.getAllTopRatedMovies()

        assertThat(topRatedMoviesBD.containsAll(topRatedMovies)).isTrue()

    }

    @Test
    fun deleteAllTopRatedMoviesTest() {
        val topRatedMovies = listOf(
            TopRatedMoviesEntity(
                idMovie = 1,
                language = "en",
                title = "Movie Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 8.5
            ),
            TopRatedMoviesEntity(
                idMovie = 2,
                language = "en",
                title = "Movie 2 Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 5.0
            )
        )

        topRatedMoviesDao.insertTopRatedMovie(topRatedMovies)
        topRatedMoviesDao.deleteAllTopRatedMovies()

        val topRatedMoviesBD = topRatedMoviesDao.getAllTopRatedMovies()

        assertThat(topRatedMoviesBD.containsAll(topRatedMovies)).isFalse()


    }

    @Test
    fun getMoviesWithGenresTest() {

        val topRatedMovies = listOf(
            TopRatedMoviesEntity(
                idMovie = 1,
                language = "en",
                title = "Movie Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 8.5
            ),
            TopRatedMoviesEntity(
                idMovie = 2,
                language = "en",
                title = "Movie 2 Example",
                overview = "Overview example",
                posterPath = "posterPath.jpg",
                backdropPath = "backdropPath.jpg",
                releaseDate = "2022",
                voteAverage = 5.0
            )
        )
        val genres = listOf(
            GenresEntity(1, "Drama"),
            GenresEntity(2, "Action"),
            GenresEntity(3, "Love"),
            GenresEntity(4, "Fiction")
        )
        val topRatedMoviesGenres = listOf(
            TopRatedMoviesGenresCrossRef(1, 1),
            TopRatedMoviesGenresCrossRef(1, 3),
            TopRatedMoviesGenresCrossRef(2, 2),
            TopRatedMoviesGenresCrossRef(2, 4)
        )

        //First insert the movies
        topRatedMoviesDao.insertTopRatedMovie(topRatedMovies)

        //Second insert de Genres
        genresDao.insertAllGenres(genres)

        //Third insert the relations of the movies with the idMovie and idGenre
        topRatedMoviesWithGenresDao.insertTopRatedMoviesWithGenres(topRatedMoviesGenres)

        val topRatedMoviesBD = topRatedMoviesDao.getMoviesWithGenres()

        val topRatedMoviesExpected = listOf(
            TopRatedMoviesWithGenres(
                movie = TopRatedMoviesEntity(
                    idMovie = 1,
                    language = "en",
                    title = "Movie Example",
                    overview = "Overview example",
                    posterPath = "posterPath.jpg",
                    backdropPath = "backdropPath.jpg",
                    releaseDate = "2022",
                    voteAverage = 8.5
                ),
                genres = listOf(
                    GenresEntity(1, "Drama"),
                    GenresEntity(3, "Love")
                )
            ),
            TopRatedMoviesWithGenres(
                movie = TopRatedMoviesEntity(
                    idMovie = 2,
                    language = "en",
                    title = "Movie 2 Example",
                    overview = "Overview example",
                    posterPath = "posterPath.jpg",
                    backdropPath = "backdropPath.jpg",
                    releaseDate = "2022",
                    voteAverage = 5.0
                ),
                genres = listOf(
                    GenresEntity(2, "Action"),
                    GenresEntity(4, "Fiction")
                )
            )

        )

        assertThat(topRatedMoviesBD.containsAll(topRatedMoviesExpected)).isTrue()


    }

}