package com.example.emovie.data.local.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef

@Dao
interface TopRatedMoviesWithGenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovieWithGenre(relation : TopRatedMoviesGenresCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMoviesWithGenres(relations : List<TopRatedMoviesGenresCrossRef>)

    @Query("select * from topratedmoviesgenrescrossref")
    fun getAllTopRatedMoviesGenre() : List<TopRatedMoviesGenresCrossRef>

    @Query("select * from topratedmoviesgenrescrossref where idMovie = :idMovie ")
    fun getIdGenresFromMovieById(idMovie : Int) : List<TopRatedMoviesGenresCrossRef>

    @Query("delete from topratedmoviesgenrescrossref")
    fun deleteAllRelations()

}