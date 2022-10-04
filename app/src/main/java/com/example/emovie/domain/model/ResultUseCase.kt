package com.example.emovie.domain.model

sealed class ResultUseCase<out T>(
){

    data class Success<T>(val data : T) : ResultUseCase<T>()
    data class Failure(val messageError : String) : ResultUseCase<Nothing>()

}