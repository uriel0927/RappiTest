package com.example.emovie.data.mapper

import com.example.emovie.data.local.bd.entities.GenresEntity
import com.example.emovie.data.model.Genres


fun GenresEntity.toGenres() : Genres{
    return Genres(id = this.idGenre, name = this.description)
}