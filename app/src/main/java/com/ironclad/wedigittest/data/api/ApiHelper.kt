package com.ironclad.wedigittest.data.api

import com.ironclad.wedigittest.data.model.CreditList
import com.ironclad.wedigittest.data.model.Movie
import com.ironclad.wedigittest.data.model.MovieList
import retrofit2.Response

interface ApiHelper {
    suspend fun getPopularMovies(queries: Map<String, Any>): Response<MovieList>
    suspend fun getMovieDetails(movieId: Int, queries: Map<String, Any>): Response<Movie>
    suspend fun getCreditDetails(movieId: Int, queries: Map<String, Any>): Response<CreditList>
}