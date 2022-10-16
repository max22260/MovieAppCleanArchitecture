package com.nagy.movieapp.common.domain.model.movie.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguagesItem(
    val name: String, val iso6391: String, val englishName: String
) : Parcelable
