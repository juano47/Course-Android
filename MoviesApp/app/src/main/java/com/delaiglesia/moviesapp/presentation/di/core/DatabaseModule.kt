package com.delaiglesia.moviesapp.presentation.di.core

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.delaiglesia.moviesapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): TMDBDatabase {
        return Room.databaseBuilder(app, TMDBDatabase::class.java, "movies-app.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: TMDBDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun provideTvShowDao(database: TMDBDatabase) = database.tvShowDao()

    @Singleton
    @Provides
    fun provideArtistDao(database: TMDBDatabase) = database.artistDao()

}