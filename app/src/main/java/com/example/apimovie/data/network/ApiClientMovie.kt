package com.example.apimovie.data.network

import com.example.apimovie.di.Credentials
import com.example.apimovie.model.Movie
import com.example.apimovie.model.ResultMovie
import com.example.apimovie.model.details.DetailsMovies
import com.example.apimovie.model.details.ItemDetailsMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClientMovie {

    @GET("movie/popular")
    suspend fun getListMoviesPopular(
        @Query("api_key")
        apiKey: String = Credentials.API_KEY,
    ): Response<Movie>

    @GET("/movie/{movie_id}")
    suspend fun getDetailsMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Credentials.API_KEY,
    ): Response<DetailsMovies>
}