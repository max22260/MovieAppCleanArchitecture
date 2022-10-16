package com.nagy.movieapp.common.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nagy.movieapp.common.data.cache.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {

    @Transaction
    @Query("SELECT * FROM movies")
    abstract fun getAllMovies(): Flow<List<CachedMovieWithDetails>>

    @Query("DELETE FROM movies")
    abstract suspend fun deleteAllMovies(): Unit

    suspend fun insertMovieWithDetails(cachedMovieWithDetails: CachedMovieWithDetails) {

        insertMovie(
            cachedMovie = cachedMovieWithDetails.cachedMovie,
            cachedProductionCountries = cachedMovieWithDetails.cachedProductionCountries,
            cachedProductionCompaniesItem = cachedMovieWithDetails.cachedProductionCompaniesItem,
            cachedGenresItem = cachedMovieWithDetails.cachedGenresItem,
            cachedSpokenLanguagesItem = cachedMovieWithDetails.cachedSpokenLanguagesItem
        )
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovie(
        cachedMovie: CachedMovie,
        cachedProductionCountries: List<CachedProductionCountriesItem>,
        cachedGenresItem: List<CachedGenresItem>,
        cachedProductionCompaniesItem: List<CachedProductionCompaniesItem>,
        cachedSpokenLanguagesItem: List<CachedSpokenLanguagesItem>
    )
}