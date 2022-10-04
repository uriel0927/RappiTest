package com.example.emovie.data.network.model

import com.squareup.moshi.Json

data class LanguagesApi(
    @field:Json(name = "iso_639_1")
    val iso: String,
    @field:Json(name = "english_name")
    val englishName: String,
    @field:Json(name = "name")
    val nativeName: String
)