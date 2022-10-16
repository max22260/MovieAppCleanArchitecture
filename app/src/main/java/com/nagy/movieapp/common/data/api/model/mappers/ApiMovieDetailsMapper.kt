package com.nagy.movieapp.common.data.api.model.mappers

import com.nagy.movieapp.common.data.api.model.ApiMovieDetails
import com.nagy.movieapp.common.domain.model.movie.MovieDetails
import javax.inject.Inject

class ApiMovieDetailsMapper @Inject constructor(
    private val apiGenresItemMapper: ApiGenresItemMapper,
    private val apiSpokenLanguagesItemMapper: ApiSpokenLanguagesItemMapper,
    private val apiProductionCountriesItemMapper: ApiProductionCountriesItemMapper,
    private val apiProductionCompaniesItemMapper: ApiProductionCompaniesItemMapper
) : ApiMapper<ApiMovieDetails, MovieDetails> {
    override fun mapToDomain(apiEntity: ApiMovieDetails?): MovieDetails {

        return MovieDetails(
            originalLanguage = apiEntity?.originalLanguage.orEmpty(),
            imdbId = apiEntity?.imdbId.orEmpty(),
            video = false,
            title = apiEntity?.title.orEmpty(),
            backdropPath = apiEntity?.backdropPath.orEmpty(),
            revenue = apiEntity?.revenue ?: 0,
            genres = apiEntity?.genres.orEmpty().map { apiGenresItemMapper.mapToDomain(it) },
            popularity = apiEntity?.popularity ?: 0.0,
            productionCountries = apiEntity?.productionCountries.orEmpty()
                .map { apiProductionCountriesItemMapper.mapToDomain(it) },
            id = apiEntity?.id ?: throw MappingException("Movie ID cannot be equal null !"),
            voteCount = apiEntity.voteCount ?: 0,
            budget = apiEntity.budget ?: 0,
            overview = apiEntity.overview.orEmpty(),
            originalTitle = apiEntity.originalTitle.orEmpty(),
            runtime = apiEntity.runtime ?: 0,
            posterPath = apiEntity.posterPath.orEmpty(),
            spokenLanguages = apiEntity.spokenLanguages.orEmpty()
                .map { apiSpokenLanguagesItemMapper.mapToDomain(it) },
            productionCompanies = apiEntity.productionCompanies.orEmpty()
                .map { apiProductionCompaniesItemMapper.mapToDomain(it) },
            releaseDate = apiEntity.releaseDate.orEmpty(),
            voteAverage = apiEntity.voteAverage?:0.0,
            belongsToCollection = apiEntity.belongsToCollection.toString(),
            tagline = apiEntity.tagline.orEmpty(),
            adult = apiEntity.adult?:true,
            homepage = apiEntity.homepage.orEmpty(),
            status = apiEntity.status.orEmpty()
        )
    }

}