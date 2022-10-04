package com.example.emovie.data.local.bd.dao

import androidx.room.*
import com.example.emovie.data.local.bd.entities.TopRatedMoviesEntity
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres

@Dao
interface TopRatedMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovie(topRatedMovie : TopRatedMoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovie(topRatedMovies : List<TopRatedMoviesEntity>)

    @Query("select * from TopRatedMoviesEntity")
    fun getAllTopRatedMovies() :List<TopRatedMoviesEntity>

    @Query("select * from TopRatedMoviesEntity where idMovie = :id")
    fun getTopRatedMovieById(id : Int) : TopRatedMoviesEntity

    @Query("delete from TopRatedMoviesEntity")
    fun deleteAllTopRatedMovies()

    @Transaction
    @Query("select * from TopRatedMoviesEntity")
    fun getMoviesWithGenres() : List<TopRatedMoviesWithGenres>

    @Transaction
    @Query("select * from TopRatedMoviesEntity where language=:language")
    fun getMoviesWithGenresFiltred(language : String) : List<TopRatedMoviesWithGenres>

}