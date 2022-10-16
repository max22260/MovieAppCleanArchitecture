package com.nagy.movieapp.common.domain.model.movie.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountriesItem(
    val iso31661: String, val name: String
) : Parcelable
