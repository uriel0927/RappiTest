package com.example.emovie.data.repository

import android.util.Log
import com.example.emovie.data.mapper.toVideoData
import com.example.emovie.data.model.VideoData
import com.example.emovie.data.network.RemoteDataSource
import javax.inject.Inject

class ShowVideoRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ShowVideoRepository {

    override suspend fun getVideoData(idMovie: Int): List<VideoData> {
        val remoteData = remoteDataSource.getVideosMovie(idMovie)


        try{
            return if (!remoteData.isError) {
                val videos = remoteData.requiredResult.results
                return videos.map { it.toVideoData() }
            } else {
                listOf()
            }
        }catch (ex : Exception){
            Log.i("ERROR_MOVIE", "${ex.message}")
            return  listOf()
        }



    }
}