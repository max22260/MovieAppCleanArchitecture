package com.nagy.movieapp.common.data

import com.nagy.movieapp.common.data.cache.Cache
import com.nagy.movieapp.common.domain.repositories.WorkerRepo
import javax.inject.Inject

class WorkerRepository@Inject constructor(
    private val cache: Cache) : WorkerRepo {

    override suspend fun deleteAllCachedMovies() {
        cache.deleteAllMovies()
    }
}