package com.nagy.movieapp.common.domain.model.movie.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompaniesItem(
    val logoPath: String, val name: String, val id: Int, val originCountry: String
) : Parcelable
