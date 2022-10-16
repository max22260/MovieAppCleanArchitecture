package com.nagy.movieapp.common.data.api.model.mappers

import com.nagy.movieapp.common.data.api.model.ApiSpokenLanguagesItem
import com.nagy.movieapp.common.domain.model.movie.details.SpokenLanguagesItem
import javax.inject.Inject

class ApiSpokenLanguagesItemMapper @Inject constructor() :
    ApiMapper<ApiSpokenLanguagesItem, SpokenLanguagesItem> {

    override fun mapToDomain(apiEntity: ApiSpokenLanguagesItem?): SpokenLanguagesItem {

        return SpokenLanguagesItem(
            name = apiEntity?.name.orEmpty(),
            iso6391 = apiEntity?.iso6391.orEmpty(),
            englishName = apiEntity?.englishName.orEmpty()
        )
    }
}