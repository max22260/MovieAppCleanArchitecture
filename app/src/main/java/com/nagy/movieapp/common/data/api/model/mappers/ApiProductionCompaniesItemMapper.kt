package com.nagy.movieapp.common.data.api.model.mappers

import com.nagy.movieapp.common.data.api.model.ApiProductionCompaniesItem
import com.nagy.movieapp.common.domain.model.movie.details.ProductionCompaniesItem
import javax.inject.Inject

class ApiProductionCompaniesItemMapper @Inject constructor() :
    ApiMapper<ApiProductionCompaniesItem, ProductionCompaniesItem> {
    override fun mapToDomain(apiEntity: ApiProductionCompaniesItem?): ProductionCompaniesItem {

        return ProductionCompaniesItem(
            logoPath = apiEntity?.logoPath.orEmpty(),
            name = apiEntity?.name.orEmpty(),
            id = apiEntity?.id ?: -1,
            originCountry = apiEntity?.originCountry.orEmpty()
        )
    }
}