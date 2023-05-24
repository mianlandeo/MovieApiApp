package com.example.apimovie.data.repo

import android.util.Log
import com.example.apimovie.model.ItemMovie
import com.example.apimovie.model.toDomain
import com.example.apimovie.data.network.ApiServiceMovie
import com.example.apimovie.model.details.ItemDetailsMovies
import com.example.apimovie.model.details.toDomain
import javax.inject.Inject


class RepositoryMovie @Inject constructor(private val apiService: ApiServiceMovie) {

    // ResultMovie -> Movie.results -> ItemMovie
    suspend fun getPopularList(): MutableList<ItemMovie> {
        val response = apiService.serviceListMovie()
        return response?.results?.map { it.toDomain() } as MutableList<ItemMovie>
    }


    suspend fun getDetailsMovie(id: Int): ItemDetailsMovies? {
        val response = apiService.getDetailsMovies(id)
        return response?.toDomain()
    }

}