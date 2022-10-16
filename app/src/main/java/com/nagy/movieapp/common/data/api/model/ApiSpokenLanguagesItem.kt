package com.nagy.movieapp.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSpokenLanguagesItem(

    @Json(name="name")
    val name: String? = null,

    @Json(name="iso_639_1")
    val iso6391: String? = null,

    @Json(name="english_name")
    val englishName: String? = null
)
