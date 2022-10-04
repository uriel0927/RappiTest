package com.example.emovie.domain


import com.example.emovie.data.model.VideoData
import com.example.emovie.data.repository.ShowVideoRepository
import com.example.emovie.domain.model.ResultUseCase
import com.example.emovie.resources.Constants
import javax.inject.Inject

class GetVideoDataUseCaseImpl @Inject constructor(private val repository: ShowVideoRepository) :
    GetVideoDataUseCase {


    override suspend fun execute(idMovie: Int): ResultUseCase<VideoData> {

        try{
            val videoDataOfMovie = repository.getVideoData(idMovie)

            return if (videoDataOfMovie != null && videoDataOfMovie.isNotEmpty()) {

                val videoMovie =
                    videoDataOfMovie.filter { it.official && it.type == Constants.KEY_WORD_TRAILER }

                if (videoMovie.isNotEmpty()) {
                    ResultUseCase.Success(videoMovie[0])
                } else {
                    ResultUseCase.Failure("No existe el trailer oficial")
                }

            } else {
                ResultUseCase.Failure("No hay videos para mostrar")
            }
        }catch (ex : Throwable){
           return  ResultUseCase.Failure(messageError = "${ex.message}")
        }


    }


}