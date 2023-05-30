package com.example.apimovie.data.network

import com.example.apimovie.model.movies.Movie
import com.example.apimovie.model.details.DetailsMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiServiceMovie @Inject constructor(private val apiClient: ApiClientMovie) {

    suspend fun serviceListMovie(): Movie?{
        return withContext(Dispatchers.IO){
            val response = apiClient.getListMoviesPopular()
            response.body()
        }
    }

    suspend fun getDetailsMovies(id: Int): DetailsMovies? {
        return withContext(Dispatchers.IO){
            val responseId = apiClient.getDetailsMovies(id)
            responseId.body()
        }
    }
}