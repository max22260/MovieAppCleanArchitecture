package com.nagy.movieapp.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiProductionCompaniesItem(

    @Json(name="logo_path")
    val logoPath: String? = null,

    @Json(name="name")
    val name: String? = null,

    @Json(name="id")
    val id: Int? = null,

    @Json(name="origin_country")
    val originCountry: String? = null
)