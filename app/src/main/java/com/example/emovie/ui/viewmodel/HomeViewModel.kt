package com.example.emovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emovie.domain.GetConfigurationUseCase
import com.example.emovie.domain.GetTopRatedMoviesFilteredUseCase
import com.example.emovie.domain.GetTopRatedMoviesUseCase
import com.example.emovie.domain.GetUpcomingMoviesUseCase
import com.example.emovie.domain.model.ResultUseCase
import com.example.emovie.ui.UiStateHomeFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesFilteredUseCase: GetTopRatedMoviesFilteredUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {

    private var _uistate = MutableLiveData<UiStateHomeFragment>()
    val uistate get() : LiveData<UiStateHomeFragment> = _uistate


    fun getToRatedMovies() {
        viewModelScope.launch {
            _uistate.value = UiStateHomeFragment.Loading

            when (val topRatedMovies = getTopRatedMoviesUseCase.execute()) {
                is ResultUseCase.Failure -> _uistate.value = UiStateHomeFragment.ErrorLoadedData
                is ResultUseCase.Success -> _uistate.value =
                    UiStateHomeFragment.TopRatedMoviesLoaded(topRatedMovies.data)
            }

        }

    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            _uistate.value = UiStateHomeFragment.Loading

            when (val topRatedMovies = getUpcomingMoviesUseCase.execute()) {
                is ResultUseCase.Failure -> _uistate.value = UiStateHomeFragment.ErrorLoadedData
                is ResultUseCase.Success -> _uistate.value =
                    UiStateHomeFragment.UpcomingMoviesLoaded(topRatedMovies.data)
            }

        }

    }

    fun getRecomendedForYou(language : String = "", releaseYear : String= "", limitsMovies : Int = 6) {
        viewModelScope.launch {
            _uistate.value = UiStateHomeFragment.Loading
            when (val topRatedMovies = getTopRatedMoviesFilteredUseCase.execute(language,releaseYear,limitsMovies)) {
                is ResultUseCase.Failure -> _uistate.value = UiStateHomeFragment.ErrorLoadedData
                is ResultUseCase.Success -> _uistate.value =
                    UiStateHomeFragment.ReocomndedForYouLoaded(topRatedMovies.data)
            }

        }

    }

    fun getConfigurations() {
        viewModelScope.launch {
            _uistate.value = UiStateHomeFragment.LoadingConfigurations
            val configurations = getConfigurationUseCase.execute()

            when(configurations){
                is ResultUseCase.Failure -> _uistate.value = UiStateHomeFragment.ErrorLoadedData
                is ResultUseCase.Success -> _uistate.value = UiStateHomeFragment.ConfigurationsLoaded(configurations.data)
            }

        }
    }


}