package com.nagy.movieapp.common.di

import com.nagy.movieapp.common.data.MovieRepository
import com.nagy.movieapp.common.domain.repositories.Repository
import com.nagy.movieapp.common.utils.CoroutineDispatchersProvider
import com.nagy.movieapp.common.utils.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindMovieRepository(repository: MovieRepository): Repository

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider): DispatchersProvider
}
