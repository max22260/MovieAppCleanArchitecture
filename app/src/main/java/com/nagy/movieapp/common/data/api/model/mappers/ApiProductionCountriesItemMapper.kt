package com.nagy.movieapp.common.data.api.model.mappers

import com.nagy.movieapp.common.data.api.model.ApiProductionCountriesItem
import com.nagy.movieapp.common.domain.model.movie.details.ProductionCountriesItem
import javax.inject.Inject

class ApiProductionCountriesItemMapper @Inject constructor() :
    ApiMapper<ApiProductionCountriesItem, ProductionCountriesItem> {
    override fun mapToDomain(apiEntity: ApiProductionCountriesItem?): ProductionCountriesItem {
        return ProductionCountriesItem(
            name = apiEntity?.name.orEmpty(), iso31661 = apiEntity?.iso31661.orEmpty()
        )
    }
}