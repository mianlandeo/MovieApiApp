package com.example.apimovie.model.movies

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ResultMovie>,
    @SerializedName("total_pages") val total_pages: Int
)