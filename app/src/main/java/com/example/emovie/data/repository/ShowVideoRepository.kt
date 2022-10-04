package com.example.emovie.data.repository

import com.example.emovie.data.model.VideoData
import com.example.emovie.data.network.model.ResultVideoApi

interface ShowVideoRepository {

    suspend fun getVideoData(idMovie : Int) : List<VideoData>

}