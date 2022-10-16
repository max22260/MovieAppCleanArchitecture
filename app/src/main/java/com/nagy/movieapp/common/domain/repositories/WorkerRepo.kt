package com.nagy.movieapp.common.domain.repositories

interface WorkerRepo {

    suspend fun deleteAllCachedMovies()
}