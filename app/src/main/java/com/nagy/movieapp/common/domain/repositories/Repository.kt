package com.nagy.movieapp.common.domain.repositories

import com.nagy.movieapp.common.data.cache.entities.CachedMovieWithDetails
import com.nagy.movieapp.common.domain.model.movie.MovieDetails
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun requestMovie(movieId : Long):MovieDetails

    fun getAllCachedMovies(): Flow<List<MovieDetails>>

    suspend fun storeMovieWithDetails(cachedMovieWithDetails: CachedMovieWithDetails)
}