package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null
)