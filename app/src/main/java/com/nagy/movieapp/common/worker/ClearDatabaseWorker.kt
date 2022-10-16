package com.nagy.movieapp.common.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.nagy.movieapp.common.data.MovieRepository
import com.nagy.movieapp.common.data.WorkerRepository
import com.nagy.movieapp.common.data.cache.entities.CachedMovieWithDetails
import com.nagy.movieapp.common.domain.repositories.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject



@HiltWorker
class ClearDatabaseWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: WorkerRepository,
) : CoroutineWorker(appContext, workerParams) {



    override suspend fun doWork(): Result {

        repository.deleteAllCachedMovies()

        val outputData = Data.Builder()
            .putString("Success Message", "Data Base is clear")
            .build()

        return Result.success(outputData)
    }}