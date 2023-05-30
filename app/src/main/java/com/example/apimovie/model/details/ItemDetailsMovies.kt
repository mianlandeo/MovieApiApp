package com.example.apimovie.model.details

import com.example.apimovie.di.Credentials
import java.text.NumberFormat

data class ItemDetailsMovies(
    val backdropPath: String,
    val title: String,
    val genres: String,
    val budget: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val posterPath: String,
    val runtime: String
)

fun DetailsMovies.toDomain(): ItemDetailsMovies {
    val backdropPath =
        if (backdropPath.isNullOrEmpty()) "" else "${Credentials.URL_IMAGE}.${this.backdropPath}"
    val posterPath = "${Credentials.URL_IMAGE}.${this.posterPath}"
    val budget  = if (this.budget == 0) "- - -" else getFormattedNumber(this.budget)
    val runTime = if (this.runtime == null) "" else "${this.runtime}"

    val genres = mutableListOf<String>()
    for (i in this.genres) genres.add(i.name)
    val genders = genres.joinToString(separator = " , ").plus(".")

    return ItemDetailsMovies(
        backdropPath,
        title,
        genders,
        budget,
        overview,
        popularity,
        releaseDate,
        posterPath,
        runTime,

    )
}

private fun getFormattedNumber(number: Int): String {
    return NumberFormat.getCurrencyInstance().format(number)
}
