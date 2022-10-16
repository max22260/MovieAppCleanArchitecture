package com.nagy.movieapp.common.domain.model.movie

import android.os.Parcelable
import com.nagy.movieapp.common.domain.model.movie.details.GenresItem
import com.nagy.movieapp.common.domain.model.movie.details.ProductionCompaniesItem
import com.nagy.movieapp.common.domain.model.movie.details.ProductionCountriesItem
import com.nagy.movieapp.common.domain.model.movie.details.SpokenLanguagesItem
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieDetails(
    val originalLanguage: String,
    val imdbId: String,
    val video: Boolean,
    val title: String,
    val backdropPath: String,
    val revenue: Int,
    val genres: List<GenresItem>,
    val popularity: Double,
    val productionCountries: List<ProductionCountriesItem>,
    val id: Long,
    val voteCount: Int,
    val budget: Int,
    val overview: String,
    val originalTitle: String,
    val runtime: Int,
    val posterPath: String,
    val spokenLanguages: List<SpokenLanguagesItem>,
    val productionCompanies: List<ProductionCompaniesItem>,
    val releaseDate: String,
    val voteAverage: Double,
    val belongsToCollection: String,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
    val status: String
) : Parcelable

