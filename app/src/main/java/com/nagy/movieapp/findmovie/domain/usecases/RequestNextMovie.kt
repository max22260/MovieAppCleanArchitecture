package com.nagy.movieapp.findmovie.domain.usecases

import com.nagy.movieapp.common.data.cache.entities.CachedMovieWithDetails
import com.nagy.movieapp.common.domain.model.NetworkException
import com.nagy.movieapp.common.domain.repositories.Repository
import retrofit2.HttpException
import javax.inject.Inject

class RequestNextMovie @Inject constructor(private val movieRepository: Repository) {
    suspend operator fun invoke(movieId: Long) {

        try {
            val movie = movieRepository.requestMovie(movieId)
            movieRepository.storeMovieWithDetails(CachedMovieWithDetails.fromDomain(movie))

        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }
}