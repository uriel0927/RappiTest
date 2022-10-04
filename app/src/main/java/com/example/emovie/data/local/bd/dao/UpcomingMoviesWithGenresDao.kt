package com.example.emovie.data.local.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef

@Dao
interface UpcomingMoviesWithGenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovieWithGenre(relation : UpcomingMoviesGenresCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMoviesWithGenres(relations : List<UpcomingMoviesGenresCrossRef>)

    @Query("select * from upcomingmoviesgenrescrossref")
    fun getAllUpcomingMoviesGenre() : List<UpcomingMoviesGenresCrossRef>

    @Query("select * from upcomingmoviesgenrescrossref where idMovie = :idMovie ")
    fun getIdUpcomingFromMovieById(idMovie : Int) : List<UpcomingMoviesGenresCrossRef>

    @Query("delete from upcomingmoviesgenrescrossref")
    fun deleteAllRelations()

}