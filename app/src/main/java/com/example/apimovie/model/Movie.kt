package com.example.apimovie.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ResultMovie>,
    @SerializedName("total_pages") val total_pages: Int
)