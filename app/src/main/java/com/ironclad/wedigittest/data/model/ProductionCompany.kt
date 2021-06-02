package com.ironclad.wedigittest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "logo_path")
    var logoPath: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "origin_country")
    var originCountry: String? = null
)