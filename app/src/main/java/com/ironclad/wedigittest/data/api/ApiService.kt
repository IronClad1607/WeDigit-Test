package com.ironclad.wedigittest.data.api

import com.ironclad.wedigittest.data.model.CreditList
import com.ironclad.wedigittest.data.model.Movie
import com.ironclad.wedigittest.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @QueryMap queries: Map<String, Any>
    ): Response<MovieList>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @QueryMap queries: Map<String, Any>
    ): Response<Movie>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCreditDetails(
        @Path("movie_id") movieId: Int,
        @QueryMap queries: Map<String, Any>
    ): Response<CreditList>
}