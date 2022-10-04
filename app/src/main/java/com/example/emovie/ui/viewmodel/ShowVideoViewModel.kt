package com.example.emovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emovie.domain.GetVideoDataUseCase
import com.example.emovie.domain.model.ResultUseCase
import com.example.emovie.ui.UiStateDetailMovieFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowVideoViewModel @Inject constructor(private val getVideoDataUseCase: GetVideoDataUseCase) : ViewModel() {

    private var _uistate  = MutableLiveData<UiStateDetailMovieFragment>()
    val uistate get() : LiveData<UiStateDetailMovieFragment> = _uistate


    fun getVideoData(idMovie : Int){
        viewModelScope.launch {

            _uistate.value = UiStateDetailMovieFragment.Loading

            when(val videoData = getVideoDataUseCase.execute(idMovie)){
                is ResultUseCase.Failure -> _uistate.value = UiStateDetailMovieFragment.ErrorDataVideo
                is ResultUseCase.Success -> _uistate.value = UiStateDetailMovieFragment.VideoDataLoaded(videoData.data)
            }


        }
    }

}