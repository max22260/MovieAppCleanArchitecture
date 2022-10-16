package com.nagy.movieapp.findmovie.domain.usecases

import com.nagy.movieapp.common.domain.repositories.Repository
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetMovies @Inject constructor(private val movieRepository: Repository) {
    operator fun invoke() = movieRepository.getAllCachedMovies()
        .filter { it.isNotEmpty() }
}