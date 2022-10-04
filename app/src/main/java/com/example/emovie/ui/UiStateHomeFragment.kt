package com.example.emovie.ui

import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.model.Configurations
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.model.UpcomingMovie

sealed class UiStateHomeFragment {

    data class TopRatedMoviesLoaded(val topRatedMovies: List<TopRatedMovie>) : UiStateHomeFragment()
    data class UpcomingMoviesLoaded(val upcomingMovies: List<UpcomingMovie>) : UiStateHomeFragment()
    object Loading : UiStateHomeFragment()
    object ErrorLoadedData : UiStateHomeFragment()
    data class ReocomndedForYouLoaded(val data: List<TopRatedMovie>) : UiStateHomeFragment()
    data class ConfigurationsLoaded(val configurations: Configurations) : UiStateHomeFragment()
    object LoadingConfigurations : UiStateHomeFragment()

}