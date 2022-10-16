package com.nagy.movieapp.common.domain.model.movie.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresItem(
    val name: String, val id: Int
) : Parcelable
