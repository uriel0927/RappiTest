package com.example.emovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(val id : Int , val name : String ) : Parcelable
