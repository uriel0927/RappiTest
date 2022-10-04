package com.example.emovie.data.local.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emovie.data.local.bd.entities.GenresEntity


@Dao
interface GenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: GenresEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGenres(genre: List<GenresEntity>)

    @Query("select * from GenresEntity")
    fun getAllGenres(): List<GenresEntity>

    @Query("select * from GenresEntity where  idGenre = :id")
    fun getGenresById(id: Int): GenresEntity

    @Query("delete from GenresEntity")
    fun deleteAllGenres()

}