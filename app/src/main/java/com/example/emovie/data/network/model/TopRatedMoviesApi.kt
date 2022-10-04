package com.example.emovie.data.network.model

data class TopRatedMoviesApi(
    val page: Int,
    val results: List<ResultTopRatedMoviesApi>,
    val total_pages: Int,
    val total_results: Int
)