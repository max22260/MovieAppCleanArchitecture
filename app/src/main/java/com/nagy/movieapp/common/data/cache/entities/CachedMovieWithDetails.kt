package com.nagy.movieapp.common.data.cache.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.nagy.movieapp.common.domain.model.movie.MovieDetails

data class CachedMovieWithDetails(

    @Embedded val cachedMovie: CachedMovie,

    @Relation(
        parentColumn = "movieId", entityColumn = "movieId"
    ) val cachedProductionCountries: List<CachedProductionCountriesItem>,

    @Relation(
        parentColumn = "movieId", entityColumn = "movieId"
    ) val cachedGenresItem: List<CachedGenresItem>,

    @Relation(
        parentColumn = "movieId", entityColumn = "movieId"
    ) val cachedProductionCompaniesItem: List<CachedProductionCompaniesItem>,

    @Relation(
        parentColumn = "movieId", entityColumn = "movieId"
    ) val cachedSpokenLanguagesItem: List<CachedSpokenLanguagesItem>
) {


    companion object {

        fun fromDomain(domainMovie: MovieDetails): CachedMovieWithDetails {

            return CachedMovieWithDetails(cachedMovie = CachedMovie.fromDomain(domainMovie),
                cachedProductionCompaniesItem = domainMovie.productionCompanies.map {
                    CachedProductionCompaniesItem.fromDomain(
                        movieId = domainMovie.id, it
                    )
                },
                cachedProductionCountries = domainMovie.productionCountries.map {
                    CachedProductionCountriesItem.fromDomain(
                        movieId = domainMovie.id, it
                    )
                },
                cachedGenresItem = domainMovie.genres.map {
                    CachedGenresItem.fromDomain(
                        movieId = domainMovie.id, it
                    )
                },
                cachedSpokenLanguagesItem = domainMovie.spokenLanguages.map {
                    CachedSpokenLanguagesItem.fromDomain(movieId = domainMovie.id, it)
                }

            )
        }

        fun toDomain(cachedMovieWithDetails: CachedMovieWithDetails): MovieDetails {

            return MovieDetails(
                originalLanguage = cachedMovieWithDetails.cachedMovie.originalLanguage,
                imdbId = cachedMovieWithDetails.cachedMovie.imdbId,
                video = cachedMovieWithDetails.cachedMovie.video,
                title = cachedMovieWithDetails.cachedMovie.title,
                backdropPath = cachedMovieWithDetails.cachedMovie.backdropPath,
                revenue = cachedMovieWithDetails.cachedMovie.revenue,
                genres = cachedMovieWithDetails.cachedGenresItem.map { CachedGenresItem.toDomain(it) },
                popularity = cachedMovieWithDetails.cachedMovie.popularity,
                productionCountries = cachedMovieWithDetails.cachedProductionCountries.map {
                        CachedProductionCountriesItem.toDomain(it)
                    },
                id = cachedMovieWithDetails.cachedMovie.movieId,
                voteCount = cachedMovieWithDetails.cachedMovie.voteCount,
                budget = cachedMovieWithDetails.cachedMovie.budget,
                overview = cachedMovieWithDetails.cachedMovie.overview,
                originalTitle = cachedMovieWithDetails.cachedMovie.originalTitle,
                runtime = cachedMovieWithDetails.cachedMovie.runtime,
                posterPath = cachedMovieWithDetails.cachedMovie.posterPath,
                spokenLanguages = cachedMovieWithDetails.cachedSpokenLanguagesItem.map {
                        CachedSpokenLanguagesItem.toDomain(it)
                    },
                productionCompanies = cachedMovieWithDetails.cachedProductionCompaniesItem.map {
                        CachedProductionCompaniesItem.toDomain(it)
                    },
                releaseDate = cachedMovieWithDetails.cachedMovie.releaseDate,
                voteAverage = cachedMovieWithDetails.cachedMovie.voteAverage,
                belongsToCollection = cachedMovieWithDetails.cachedMovie.belongsToCollection.toString(),
                tagline = cachedMovieWithDetails.cachedMovie.tagline,
                adult = cachedMovieWithDetails.cachedMovie.adult,
                homepage = cachedMovieWithDetails.cachedMovie.homepage,
                status = cachedMovieWithDetails.cachedMovie.status
            )
        }
    }
}