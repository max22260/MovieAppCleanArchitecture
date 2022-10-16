package com.nagy.movieapp.common.data.api.model.mappers

import com.nagy.movieapp.common.data.api.model.ApiGenresItem
import com.nagy.movieapp.common.domain.model.movie.details.GenresItem
import javax.inject.Inject

class ApiGenresItemMapper @Inject constructor() : ApiMapper<ApiGenresItem,GenresItem> {
    override fun mapToDomain(apiEntity: ApiGenresItem?): GenresItem {

        return  GenresItem(
            name = apiEntity?.name.orEmpty(),
            id = apiEntity?.id?:-1
        )
    }
}