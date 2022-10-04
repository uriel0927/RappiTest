package com.example.emovie.data.network.model

data class GenresApi(val id: Int, val name: String)

data class ResponseGenresApi(val genres : List<GenresApi>)
