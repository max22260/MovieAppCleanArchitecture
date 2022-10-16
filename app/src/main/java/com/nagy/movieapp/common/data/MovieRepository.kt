package com.nagy.movieapp.common.data

import com.nagy.movieapp.common.data.api.MovieApi
import com.nagy.movieapp.common.data.api.model.mappers.ApiMovieDetailsMapper
import com.nagy.movieapp.common.data.cache.Cache
import com.nagy.movieapp.common.data.cache.RoomCache
import com.nagy.movieapp.common.data.cache.entities.CachedMovieWithDetails
import com.nagy.movieapp.common.domain.model.movie.MovieDetails
import com.nagy.movieapp.common.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApi,
    private val cache: Cache,
    private val apiMovieDetailsMapper: ApiMovieDetailsMapper
) : Repository {
    override suspend fun requestMovie(movieId: Long): MovieDetails =
        apiMovieDetailsMapper.mapToDomain(api.getMovieDetails(movieId))


    override fun getAllCachedMovies(): Flow<List<MovieDetails>> {
        return cache.getAllMovies()
            .map { movieList -> movieList.map { CachedMovieWithDetails.toDomain(it) } }
    }

    override suspend fun storeMovieWithDetails(cachedMovieWithDetails: CachedMovieWithDetails) {
        cache.insertMovieWithDetails(cachedMovieWithDetails)
    }
}