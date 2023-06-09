package com.example.apimovie.data.network

import com.example.apimovie.di.Credentials
import com.example.apimovie.model.movies.Movie
import com.example.apimovie.model.details.DetailsMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClientMovie {

    //https://api.themoviedb.org/3/movie/popular?api_key=baaa9a1174636d6731bd2bf3d3b9de2f&language=es-ES
    @GET("movie/popular")
    suspend fun getListMoviesPopular(
        @Query("api_key")
        apiKey: String = Credentials.API_KEY,
        @Query("language")
        language: String = "es-ES",
    ): Response<Movie>



    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=baaa9a1174636d6731bd2bf3d3b9de2f&language=es-ES
    @GET("movie/{movie_id}")
    suspend fun getDetailsMovies(
        @Path("movie_id")
        movieId: Int,
        @Query("api_key")
        apiKey: String = Credentials.API_KEY,
        @Query("language")
        language: String = "es-ES",
    ): Response<DetailsMovies>
}