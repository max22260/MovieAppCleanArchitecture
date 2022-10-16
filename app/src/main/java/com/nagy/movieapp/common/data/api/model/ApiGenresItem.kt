package com.nagy.movieapp.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiGenresItem(

    @Json(name="name")
    val name: String? = null,

    @Json(name="id")
    val id: Int? = null
)