package com.delaiglesia.moviesapp.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.delaiglesia.moviesapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "movies-app.db")
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