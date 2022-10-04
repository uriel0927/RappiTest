package com.example.emovie.data.local.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.emovie.data.local.bd.dao.*
import com.example.emovie.data.local.bd.entities.*
import com.example.emovie.data.local.bd.relations.LanguagesCountriesCrossRef
import com.example.emovie.data.local.bd.relations.TopRatedMoviesGenresCrossRef
import com.example.emovie.data.local.bd.relations.UpcomingMoviesGenresCrossRef


@Database(
    entities = [
        CountriesEntity::class,
        GenresEntity::class,
        LanguagesEntity::class,
        TopRatedMoviesEntity::class,
        UpcomingMoviesEntity::class,
        UpcomingMoviesGenresCrossRef::class,
        TopRatedMoviesGenresCrossRef::class,
        LanguagesCountriesCrossRef::class], version = 1
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getCountriesDao(): CountriesDao

    abstract fun getGenresDao(): GenresDao

    abstract fun getLanguageDao(): LanguagesDao

    abstract fun getTopRatedMoviesDao(): TopRatedMoviesDao

    abstract fun getUpcomingDao(): UpcomingMoviesDao

    abstract fun getLanguagesCountriesDao() : LanguageCountriesDao

    abstract fun getTopRatedMoviesWithGenresDao() : TopRatedMoviesWithGenresDao

    abstract fun getUpcomingMoviesWithGenresDao() : UpcomingMoviesWithGenresDao

}