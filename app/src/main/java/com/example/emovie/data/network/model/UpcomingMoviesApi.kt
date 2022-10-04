package com.example.emovie.data.network.model

import com.squareup.moshi.Json

data class UpcomingMoviesApi(
    val dates: DatesApi,
    val page: Int,
    val results: List<ResultUpcomingMoviesApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)