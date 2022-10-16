package com.nagy.movieapp.common.data.cache

import com.nagy.movieapp.common.data.cache.daos.MovieDao
import com.nagy.movieapp.common.data.cache.entities.CachedMovieWithDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(private val movieDao: MovieDao) : Cache {
    override fun getAllMovies(): Flow<List<CachedMovieWithDetails>> = movieDao.getAllMovies()


    override suspend fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }

    override suspend fun insertMovieWithDetails(cachedMovieWithDetails: CachedMovieWithDetails) {
        movieDao.insertMovieWithDetails(cachedMovieWithDetails)
    }
}