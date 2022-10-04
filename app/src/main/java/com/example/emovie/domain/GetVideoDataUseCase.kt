package com.example.emovie.domain

import com.example.emovie.data.model.VideoData
import com.example.emovie.domain.model.ResultUseCase

interface GetVideoDataUseCase {

    suspend fun execute(idMovie : Int) : ResultUseCase<VideoData>

}