package com.nagy.movieapp.common.data.di

import android.content.Context
import androidx.room.Room
import com.nagy.movieapp.common.data.cache.Cache
import com.nagy.movieapp.common.data.cache.MovieDataBase
import com.nagy.movieapp.common.data.cache.RoomCache
import com.nagy.movieapp.common.data.cache.daos.MovieDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): MovieDataBase {
            return Room.databaseBuilder(
                context, MovieDataBase::class.java, "Movie.db"
            ).build()
        }

        @Provides
        fun provideMovieDao(
            movieDataBase: MovieDataBase
        ): MovieDao = movieDataBase.movieDao()
    }
}