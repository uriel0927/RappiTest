package com.example.emovie.data.network

import com.example.emovie.data.network.model.*
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class RemoteDataSource {

    abstract suspend fun getConfigurationApi() : NetworkResult<ConfigurationApi>

    abstract suspend fun getPrimaryTranslations() : NetworkResult<List<String>>

    abstract suspend fun getLanguages() : NetworkResult<List<LanguagesApi>>

    abstract suspend fun getCountries() : NetworkResult<List<CountriesApi>>

    abstract suspend fun getUpcomingMovies(language : String) : NetworkResult<UpcomingMoviesApi>

    abstract suspend fun getTopRatedMovies(language : String) : NetworkResult<TopRatedMoviesApi>

    abstract suspend fun getGenresMovies(language : String) : NetworkResult<ResponseGenresApi>

    abstract suspend fun getVideosMovie(idMovie : Int) : NetworkResult<VideosMovieApi>

    protected suspend fun <T> safeCallApi(apiCall : suspend ()-> T ) : NetworkResult<T>{
        return withContext(Dispatchers.IO){
            try{
                val response = apiCall.invoke()
                return@withContext NetworkResult(response)
            }catch (throwable : Throwable){
                return@withContext NetworkResult<T>(networkError = createError(throwable))
            }
        }
    }

    private fun createError(throwable: Throwable): NetworkError {

        return when (throwable) {
            is IOException -> {
                NetworkError(NetworkErrorType.CONNECTION_ERROR, throwable.message)
            }
            is HttpException -> {
                val bodyResponse: String? = throwable.response()?.errorBody()?.string()
                NetworkError(
                    NetworkErrorType.API_ERROR,
                    bodyResponse,
                    throwable.code().toString()
                )
            }
            is JsonDataException -> {
                NetworkError(NetworkErrorType.API_ERROR, throwable.message)
            }
            else -> {
                NetworkError(NetworkErrorType.UNKNOWN_ERROR, throwable.message)
            }
        }
    }


}