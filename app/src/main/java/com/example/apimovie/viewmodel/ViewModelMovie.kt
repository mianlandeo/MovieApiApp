package com.example.apimovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovie.model.movies.ItemMovie
import com.example.apimovie.domain.GetMovies
import com.example.apimovie.model.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMovie @Inject constructor(private val getMovie: GetMovies) : ViewModel() {

    private var _movies = MutableLiveData<MutableList<ItemMovie>>()
    val movies : LiveData<MutableList<ItemMovie>> get() = _movies


    private var _loading = MutableLiveData<ApiStatus>()
    val loading : LiveData<ApiStatus> get() = _loading

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        _loading.value = ApiStatus.LOADING
        viewModelScope.launch {
            val response = getMovie.getAllMovies()
            if (response.isEmpty()){
                _loading.value = ApiStatus.ERROR
            } else {
                _movies.value = response
                _loading.value = ApiStatus.DONE
            }
        }
    }
}