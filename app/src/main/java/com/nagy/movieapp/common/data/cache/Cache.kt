package com.nagy.movieapp.common.data.cache

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nagy.movieapp.common.data.cache.entities.CachedMovieWithDetails
import kotlinx.coroutines.flow.Flow

interface Cache {

    fun getAllMovies(): Flow<List<CachedMovieWithDetails>>

    suspend fun deleteAllMovies()

    suspend fun insertMovieWithDetails(cachedMovieWithDetails: CachedMovieWithDetails)
}