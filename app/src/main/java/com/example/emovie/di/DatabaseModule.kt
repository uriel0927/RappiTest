package com.example.emovie.di

import android.content.Context
import androidx.room.Room
import com.example.emovie.data.local.bd.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabaseRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "moviesDB").build()

    @Provides
    @Singleton
    fun providesCountriesDao(db: MoviesDatabase) = db.getCountriesDao()

    @Provides
    @Singleton
    fun providesGenresDao(db: MoviesDatabase) = db.getGenresDao()

    @Provides
    @Singleton
    fun providesLanguagesDao(db: MoviesDatabase) = db.getLanguageDao()

    @Provides
    @Singleton
    fun providesTopRatedMoviesDao(db: MoviesDatabase) = db.getTopRatedMoviesDao()

    @Provides
    @Singleton
    fun providesLanguagesCountriesDao(db: MoviesDatabase) = db.getLanguagesCountriesDao()

    @Provides
    @Singleton
    fun providesUpcomingMoviesDao(db: MoviesDatabase) = db.getUpcomingDao()

    @Provides
    @Singleton
    fun providesTopRatedMoviesWithGenreDao(db: MoviesDatabase) = db.getTopRatedMoviesWithGenresDao()

    @Provides
    @Singleton
    fun providesUpcomingMoviesWithGenreDao(db: MoviesDatabase) = db.getUpcomingMoviesWithGenresDao()



}