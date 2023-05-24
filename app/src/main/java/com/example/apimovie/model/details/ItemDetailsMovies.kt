package com.example.apimovie.model.details

import java.text.NumberFormat


data class ItemDetailsMovies(
    val backdropPath: String,
    val title: String,
    val budget: String,
    val runtime: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
)

fun DetailsMovies.toDomain(): ItemDetailsMovies {

    val backdropPath =
        if (backdropPath!!.isEmpty()) "" else "https://image.tmdb.org/t/p/w500/${this.backdropPath}"
    val posterPath = "https://image.tmdb.org/t/p/w500/${this.posterPath}"
    val bundle = if (this.budget == 0) "- - -" else getFormattedNumber(this.budget)
    val runTime = if (this.runtime == null) "" else "${this.runtime}"

    return ItemDetailsMovies(
        backdropPath,
        posterPath,
        bundle,
        runTime,
        overview,
        popularity,
        posterPath,
        releaseDate
    )

}

private fun getFormattedNumber(number: Int): String {
    return NumberFormat.getCurrencyInstance().format(number)
}