package com.example.emovie.data.local.bd.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.emovie.data.local.bd.MoviesDatabase
import com.example.emovie.data.local.bd.entities.GenresEntity
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GenresDaoTest {

    private lateinit var db: MoviesDatabase
    private lateinit var genresDao: GenresDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).build()
        genresDao = db.getGenresDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndSelectSpecificGenreTest() {

        val genre = GenresEntity(1, "Genre Example")
        genresDao.insertGenre(genre)

        val genreInserted = genresDao.getGenresById(1)
        assertThat(genre == genreInserted).isTrue()

    }

    @Test
    fun insertAndSelectAllGenresTest() {

        val genres = listOf(
            GenresEntity(1, "Genre 1 Example"),
            GenresEntity(2, "Genre 2 Example")
        )
        genresDao.insertAllGenres(genres)

        val genresInserted = genresDao.getAllGenres()

        assertThat(genresInserted.containsAll(genres)).isTrue()

    }

    @Test
    fun deleteAllGenres(){
        val genres = listOf(
            GenresEntity(1, "Genre 1 Example"),
            GenresEntity(2, "Genre 2 Example"),
            GenresEntity(3, "Genre 2 Example")
        )

        genresDao.insertAllGenres(genres)
        genresDao.deleteAllGenres()

        val genresInserted = genresDao.getAllGenres()

        assertThat(genresInserted.isEmpty()).isTrue()

    }


}