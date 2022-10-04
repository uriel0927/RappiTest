package com.example.emovie.data.network.model

import com.squareup.moshi.Json

data class ImagesApi(
    @Json(name = "base_url")
    val baseUrl: String,
    @Json(name = "secure_base_url")
    val secureBaseUrl: String,
    @Json(name = "backdrop_sizes")
    val backdropSizes: List<String>,
    @Json(name = "logo_sizes")
    val logoSizes: List<String>,
    @Json(name = "poster_sizes")
    val posterSizes: List<String>,
    @Json(name = "profile_sizes")
    val profileSizes: List<String>,
    @Json(name = "still_sizes")
    val stillSizes: List<String>
)