package com.ironclad.wedigittest.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironclad.wedigittest.data.model.MovieList
import com.ironclad.wedigittest.domain.ApiRepository
import com.ironclad.wedigittest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repo: ApiRepository) : ViewModel() {
    private val _popularMovieList = MutableLiveData<Resource<MovieList>>()
    val popularMovieList: LiveData<Resource<MovieList>>
        get() = _popularMovieList

    fun getPopularMovieList(queries: Map<String, Any>) = viewModelScope.launch {
        _popularMovieList.postValue(Resource.loading(null))
        repo.getPopularMovies(queries).let { res ->
            if (res.isSuccessful) {
                _popularMovieList.postValue(Resource.success(res.body()))
            } else {
                _popularMovieList.postValue(Resource.error(res.errorBody().toString(), null))
            }
        }
    }
}