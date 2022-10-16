package com.nagy.movieapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nagy.movieapp.common.data.api.model.mappers.MappingException
import com.nagy.movieapp.common.domain.model.movie.MovieDetails
import com.nagy.movieapp.common.domain.model.movie.details.GenresItem


@Entity(tableName = "movies")
data class CachedMovie(
    @PrimaryKey(autoGenerate = false) val movieId: Long,
    val originalLanguage: String,
    val imdbId: String,
    val video: Boolean,
    val title: String,
    val backdropPath: String,
    val revenue: Int,
    val popularity: Double,
    val voteCount: Int,
    val budget: Int,
    val overview: String,
    val originalTitle: String,
    val runtime: Int,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val belongsToCollection: String,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
    val status: String
) {


    companion object {

        fun fromDomain(domainMovieDetails: MovieDetails): CachedMovie {

            return CachedMovie(
                movieId = domainMovieDetails.id,
                originalLanguage = domainMovieDetails.originalLanguage,
                imdbId = domainMovieDetails.imdbId,
                video = domainMovieDetails.video,
                title = domainMovieDetails.title,
                backdropPath = domainMovieDetails.backdropPath,
                revenue = domainMovieDetails.revenue,
                popularity = domainMovieDetails.popularity,
                voteCount = domainMovieDetails.voteCount,
                budget = domainMovieDetails.budget,
                overview = domainMovieDetails.overview,
                originalTitle = domainMovieDetails.originalTitle,
                runtime = domainMovieDetails.runtime,
                posterPath = domainMovieDetails.posterPath,
                releaseDate = domainMovieDetails.releaseDate,
                voteAverage = domainMovieDetails.voteAverage,
                belongsToCollection = domainMovieDetails.belongsToCollection.toString(),
                tagline = domainMovieDetails.tagline,
                adult = domainMovieDetails.adult,
                homepage = domainMovieDetails.homepage,
                status = domainMovieDetails.status
            )
        }
    }
}