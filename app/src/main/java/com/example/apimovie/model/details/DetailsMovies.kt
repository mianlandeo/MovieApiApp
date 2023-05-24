package com.example.apimovie.model.details

import com.google.gson.annotations.SerializedName

data class DetailsMovies(
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("title") val title: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
)

data class Gender(
    @SerializedName("name") val name: String
)