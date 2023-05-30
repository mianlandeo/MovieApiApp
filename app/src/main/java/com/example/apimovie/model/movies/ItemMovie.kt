package com.example.apimovie.model.movies

import com.google.gson.annotations.SerializedName

data class ItemMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val poster_path: String
)
fun ResultMovie.toDomain(): ItemMovie {
    return ItemMovie(
        id, title, poster_path
    )
}
