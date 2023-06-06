package com.example.apimovie.domain

import com.example.apimovie.data.repo.RepositoryMovie
import com.example.apimovie.model.details.ItemDetailsMovies
import javax.inject.Inject

class DetailsMovies @Inject constructor(private val repositoryMovie: RepositoryMovie) {

    suspend fun getDetailsMovie(id: Int): ItemDetailsMovies? {
        return repositoryMovie.getDetailsMovie(id)
    }

}