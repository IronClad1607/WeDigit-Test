package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @Json(name = "english_name")
    var englishName: String? = null,
    @Json(name = "iso_639_1")
    var iso6391: String? = null,
    @Json(name = "name")
    var name: String? = null
)