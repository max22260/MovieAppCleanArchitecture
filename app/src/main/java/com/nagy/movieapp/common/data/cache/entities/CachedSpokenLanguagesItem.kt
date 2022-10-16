package com.nagy.movieapp.common.data.cache.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.nagy.movieapp.common.domain.model.movie.details.SpokenLanguagesItem


@Entity(
    tableName = "SpokenLanguagesItems", foreignKeys = [ForeignKey(
        entity = CachedMovie::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [androidx.room.Index("movieId")]
)
data class CachedSpokenLanguagesItem(
    val movieId: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val iso6391: String,
    val englishName: String
) {
    companion object {

        fun fromDomain(
            movieId: Long, domainSpokenLanguagesItem: SpokenLanguagesItem
        ): CachedSpokenLanguagesItem {

            return CachedSpokenLanguagesItem(
                movieId = movieId,
                name = domainSpokenLanguagesItem.name,
                iso6391 = domainSpokenLanguagesItem.iso6391,
                englishName = domainSpokenLanguagesItem.englishName
            )
        }

        fun toDomain(
            cachedSpokenLanguagesItem: CachedSpokenLanguagesItem
        ): SpokenLanguagesItem {

            return SpokenLanguagesItem(
                name = cachedSpokenLanguagesItem.name,
                iso6391 = cachedSpokenLanguagesItem.iso6391,
                englishName = cachedSpokenLanguagesItem.englishName
            )
        }
    }
}
