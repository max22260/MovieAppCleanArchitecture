package com.nagy.movieapp.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nagy.movieapp.common.data.cache.daos.MovieDao
import com.nagy.movieapp.common.data.cache.entities.*

@Database(
    entities = [
        CachedGenresItem::class,
        CachedMovie::class,
        CachedProductionCompaniesItem::class,
        CachedProductionCountriesItem::class,
        CachedSpokenLanguagesItem::class
    ],
    version = 1
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}