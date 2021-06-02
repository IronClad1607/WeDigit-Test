package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListItem(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "original_title")
    var originalTitle: String? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "release_date")
    var releaseDate: String? = null
)