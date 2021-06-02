package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList(
    @Json(name = "page")
    var page: Int? = null,
    @Json(name = "results")
    var movieListItems: List<MovieListItem>? = null,
    @Json(name = "total_pages")
    var totalPages: Int? = null,
    @Json(name = "total_results")
    var totalResults: Int? = null
)