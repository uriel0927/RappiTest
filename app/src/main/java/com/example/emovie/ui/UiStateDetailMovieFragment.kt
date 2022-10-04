package com.example.emovie.ui

import com.example.emovie.data.model.VideoData

sealed class UiStateDetailMovieFragment {

    data class VideoDataLoaded(val data: VideoData) : UiStateDetailMovieFragment()
    object Loading : UiStateDetailMovieFragment()
    object ErrorDataVideo : UiStateDetailMovieFragment()

}