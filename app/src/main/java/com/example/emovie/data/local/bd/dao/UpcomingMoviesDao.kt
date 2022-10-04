package com.example.emovie.data.local.bd.dao

import androidx.room.*
import com.example.emovie.data.local.bd.entities.UpcomingMoviesEntity
import com.example.emovie.data.local.bd.model.UpcomingMoviesWithGenres

@Dao
interface UpcomingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovie(upcomingMovie : UpcomingMoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovie(upcomingMovie : List<UpcomingMoviesEntity>)

    @Query("select * from UpcomingMoviesEntity")
    fun getAllUpcomingMovies() :List<UpcomingMoviesEntity>

    @Query("select * from UpcomingMoviesEntity where idMovie = :id")
    fun getTopUpcomingById(id : Int) : UpcomingMoviesEntity

    @Query("delete from UpcomingMoviesEntity")
    fun deleteAllUpcomingMovies()

    @Transaction
    @Query("select * from UpcomingMoviesEntity")
    fun getUpcomingMoviesWithGenres() : List<UpcomingMoviesWithGenres>

}