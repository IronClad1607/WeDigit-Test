package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @Json(name = "iso_3166_1")
    var iso31661: String? = null,
    @Json(name = "name")
    var name: String? = null
)