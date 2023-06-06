package com.example.apimovie.domain

import com.example.apimovie.data.repo.RepositoryMovie
import com.example.apimovie.model.movies.ItemMovie
import javax.inject.Inject

class GetMovies @Inject constructor(private val repositoryMovie: RepositoryMovie) {

    suspend fun getAllMovies(): MutableList<ItemMovie> {
        return repositoryMovie.getPopularList()
    }
}