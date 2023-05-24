package com.example.apimovie.model

import com.google.gson.annotations.SerializedName

data class ResultMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("title") val title: String
)