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

    private val _creditList = MutableLiveData<Resource<CreditList>>()
    val creditList: LiveData<Resource<CreditList>>
        get() = _creditList


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

    fun getCreditList(movieId: Int, queries: Map<String, Any>) = viewModelScope.launch {
        _creditList.postValue(Resource.loading(null))
        repo.getCreditDetails(movieId, queries).let { response ->
            if (response.isSuccessful) {
                _creditList.postValue(Resource.success(response.body()))
            } else {
                _creditList.postValue(Resource.error(response.errorBody().toString(), null))
            }
        }
    }
}