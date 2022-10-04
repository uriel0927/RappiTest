package com.example.emovie.data.mapper

import com.example.emovie.data.model.VideoData
import com.example.emovie.data.network.model.ResultVideoApi

fun ResultVideoApi.toVideoData(): VideoData {
    return VideoData(
        name = this.name,
        key = this.key,
        type = this.type,
        official = this.official,
        id = this.id
    )
}