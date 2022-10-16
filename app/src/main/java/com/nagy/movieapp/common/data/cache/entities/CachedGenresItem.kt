package com.nagy.movieapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.nagy.movieapp.common.domain.model.movie.details.GenresItem


@Entity(
    tableName = "genres", foreignKeys = [ForeignKey(
        entity = CachedMovie::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [androidx.room.Index("movieId")]
)
data class CachedGenresItem(
    val movieId: Long, val name: String, @PrimaryKey(autoGenerate = false) val id: Int
) {

    companion object {

        fun fromDomain(movieId: Long, domainGenresItem: GenresItem): CachedGenresItem {

            return CachedGenresItem(
                movieId = movieId, name = domainGenresItem.name, id = domainGenresItem.id
            )
        }

        fun toDomain(genresItem: CachedGenresItem): GenresItem {

            return GenresItem(
                name = genresItem.name, id = genresItem.id
            )
        }
    }
}