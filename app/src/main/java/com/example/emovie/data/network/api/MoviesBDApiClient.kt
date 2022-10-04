package com.example.emovie.data.network.api

import com.example.emovie.data.network.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesBDApiClient {

    @GET("configuration")
    suspend fun getConfigurationApi(@Query("api_key") apiKey: String) : ConfigurationApi

    @GET("configuration/primary_translations")
    suspend fun getPrimaryTranslations(@Query("api_key") apiKey: String): List<String>

    @GET("configuration/languages")
    suspend fun getLanguages(@Query("api_key") apiKey: String): List<LanguagesApi>

    @GET("configuration/countries")
    suspend fun getCountries(@Query("api_key") apiKey: String): List<CountriesApi>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String,
                                  @Query("language") language : String): UpcomingMoviesApi

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("language") language : String): TopRatedMoviesApi

    @GET("genre/movie/list")
    suspend fun getGenresOfMovies(
        @Query("api_key") apiKye: String,
        @Query("language") language: String
    ) : ResponseGenresApi


    @GET("movie/{movie_id}/videos")
    suspend fun getVideosMovie( @Path("movie_id") movieId : Int ,@Query("api_key") apiKey: String,) : VideosMovieApi

}