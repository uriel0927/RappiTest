package com.example.emovie.data.network.model

import com.squareup.moshi.Json


data class CountriesApi(
    @field:Json(name="iso_3166_1")
    val iso: String,
    @field:Json(name="english_name")
    val englishName: String,
    @field:Json(name="native_name")
    val nativeName: String
)
