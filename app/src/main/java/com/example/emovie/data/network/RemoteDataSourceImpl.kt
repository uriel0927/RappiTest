package com.example.emovie.data.network

import com.example.emovie.data.network.api.MoviesBDApiClient
import com.example.emovie.data.network.model.*
import com.example.emovie.resources.Constants
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiClient: MoviesBDApiClient) : RemoteDataSource() {

    override suspend fun getConfigurationApi(): NetworkResult<ConfigurationApi> {
        return safeCallApi { apiClient.getConfigurationApi(Constants.API_KEY) }
    }

    override suspend fun getPrimaryTranslations() : NetworkResult<List<String>> {
        return safeCallApi { apiClient.getPrimaryTranslations(Constants.API_KEY) }
    }

    override suspend fun getLanguages() : NetworkResult<List<LanguagesApi>> {
        return safeCallApi { apiClient.getLanguages(Constants.API_KEY) }
    }

    override suspend fun getCountries(): NetworkResult<List<CountriesApi>> {
        return safeCallApi { apiClient.getCountries(Constants.API_KEY) }
    }

    override suspend fun getUpcomingMovies(language : String): NetworkResult<UpcomingMoviesApi> {
        return safeCallApi { apiClient.getUpcomingMovies(Constants.API_KEY, language) }
    }

    override suspend fun getTopRatedMovies(language : String): NetworkResult<TopRatedMoviesApi> {
        return safeCallApi { apiClient.getTopRatedMovies(Constants.API_KEY,language) }
    }

    override suspend fun getGenresMovies(language : String): NetworkResult<ResponseGenresApi> {
        return safeCallApi { apiClient.getGenresOfMovies(Constants.API_KEY,language)}
    }

    override suspend fun getVideosMovie(idMovie : Int): NetworkResult<VideosMovieApi> {
        return safeCallApi { apiClient.getVideosMovie(idMovie,Constants.API_KEY)}
    }
}