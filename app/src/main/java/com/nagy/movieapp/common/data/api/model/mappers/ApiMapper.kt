package com.nagy.movieapp.common.data.api.model.mappers

interface ApiMapper<in E, out D> {

    fun mapToDomain(apiEntity: E?): D
}