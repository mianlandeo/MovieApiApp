package com.example.apimovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovie.domain.DetailsMovies
import com.example.apimovie.model.ApiStatus
import com.example.apimovie.model.details.ItemDetailsMovies
import com.example.apimovie.navigation.details.DetailsFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelDetails @Inject constructor(private val detailsMovies: DetailsMovies) : ViewModel() {

    private var _movieDetails = MutableLiveData<ItemDetailsMovies>()
    val movieDetails: LiveData<ItemDetailsMovies> get() = _movieDetails

    private var _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus> get() = _loading


    /*init {
        getDetailsMovie(DetailsFragment.idMovie)
    }*/
     fun getDetailsMovie(id: Int) {
        _loading.value = ApiStatus.LOADING
        viewModelScope.launch {
            _movieDetails.value = detailsMovies.getDetailsMovie(id)
            delay(2000)
            _loading.value = ApiStatus.DONE
        }
    }
}