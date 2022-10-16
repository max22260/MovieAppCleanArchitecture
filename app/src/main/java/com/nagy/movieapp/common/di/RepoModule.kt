package com.nagy.movieapp.common.di

import com.nagy.movieapp.common.data.MovieRepository
import com.nagy.movieapp.common.data.WorkerRepository
import com.nagy.movieapp.common.domain.repositories.Repository
import com.nagy.movieapp.common.domain.repositories.WorkerRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun provideRepository(
        repo: WorkerRepository
    ): WorkerRepo = repo

}