package com.ironclad.wedigittest.domain

import com.ironclad.wedigittest.data.api.ApiHelper
import com.ironclad.wedigittest.data.api.ApiService
import javax.inject.Inject

class ApiHelperImp @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPopularMovies(queries: Map<String, Any>) =
        apiService.getPopularMovies(queries)

    override suspend fun getMovieDetails(movieId: Int, queries: Map<String, Any>) =
        apiService.getMovieDetails(movieId, queries)
}