package com.ironclad.wedigittest.domain

import com.ironclad.wedigittest.data.api.ApiHelper
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getPopularMovies(queries: Map<String, Any>) = apiHelper.getPopularMovies(queries)
    suspend fun getMovieDetails(movieId: Int, queries: Map<String, Any>) =
        apiHelper.getMovieDetails(movieId, queries)

    suspend fun getCreditDetails(
        movieId: Int,
        queries: Map<String, Any>
    ) = apiHelper.getCreditDetails(movieId, queries)
}