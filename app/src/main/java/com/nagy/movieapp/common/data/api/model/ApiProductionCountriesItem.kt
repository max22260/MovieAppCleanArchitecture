package com.nagy.movieapp.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiProductionCountriesItem(

    @Json(name="iso_3166_1")
    val iso31661: String? = null,

    @Json(name="name")
    val name: String? = null
)