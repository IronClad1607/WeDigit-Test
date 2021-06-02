package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastList(
    @Json(name = "cast")
    var cast: List<Cast>? = null,
    @Json(name = "crew")
    var crew: List<Crew>? = null,
    @Json(name = "id")
    var id: Int? = null
)