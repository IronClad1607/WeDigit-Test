package com.ironclad.wedigittest.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironclad.wedigittest.data.model.CreditList
import com.ironclad.wedigittest.data.model.Movie
import com.ironclad.wedigittest.domain.ApiRepository
import com.ironclad.wedigittest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repo: ApiRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<Resource<Movie>>()
    val movieDetails: LiveData<Resource<Movie>>
        get() = _movieDetails


    fun getMovieDetails(movieId: Int, queries: Map<String, Any>) = viewModelScope.launch {
        _movieDetails.postValue(Resource.loading(null))
        repo.getMovieDetails(movieId, queries).let { res ->
            if (res.isSuccessful) {
                _movieDetails.postValue(Resource.success(res.body()))
            } else {
                _movieDetails.postValue(Resource.error(res.errorBody().toString(), null))
            }
        }
    }
}