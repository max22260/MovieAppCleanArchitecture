package com.nagy.movieapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.nagy.movieapp.common.domain.model.movie.details.ProductionCompaniesItem


@Entity(
    tableName = "productionCompaniesItems", foreignKeys = [ForeignKey(
        entity = CachedMovie::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [androidx.room.Index("movieId")]
)
data class CachedProductionCompaniesItem(
    val movieId: Long,
    val logoPath: String,
    val name: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val originCountry: String
) {

    companion object {

        fun fromDomain(
            movieId: Long, domainProductionCompaniesItem: ProductionCompaniesItem
        ): CachedProductionCompaniesItem {

            return CachedProductionCompaniesItem(
                movieId = movieId,
                logoPath = domainProductionCompaniesItem.logoPath,
                name = domainProductionCompaniesItem.name,
                id = domainProductionCompaniesItem.id,
                originCountry = domainProductionCompaniesItem.originCountry
            )
        }

        fun toDomain(
            cachedProductionCompaniesItem: CachedProductionCompaniesItem
        ): ProductionCompaniesItem {

            return ProductionCompaniesItem(
                logoPath = cachedProductionCompaniesItem.logoPath,
                name = cachedProductionCompaniesItem.name,
                id = cachedProductionCompaniesItem.id,
                originCountry = cachedProductionCompaniesItem.originCountry
            )
        }
    }
}