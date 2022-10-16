package com.nagy.movieapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.nagy.movieapp.common.domain.model.movie.details.ProductionCountriesItem


@Entity(
    tableName = "productionCountriesItems", foreignKeys = [ForeignKey(
        entity = CachedMovie::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [androidx.room.Index("movieId")]
)
data class CachedProductionCountriesItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val movieId: Long,
    val iso31661: String,
    val name: String
) {

    companion object {

        fun fromDomain(
            movieId: Long, domainProductionCountriesItem: ProductionCountriesItem
        ): CachedProductionCountriesItem {

            return CachedProductionCountriesItem(
                movieId = movieId,
                name = domainProductionCountriesItem.name,
                iso31661 = domainProductionCountriesItem.iso31661
            )
        }

        fun toDomain(cachedProductionCountriesItem: CachedProductionCountriesItem): ProductionCountriesItem {

            return ProductionCountriesItem(
                name = cachedProductionCountriesItem.name,
                iso31661 = cachedProductionCountriesItem.iso31661
            )
        }
    }


}
