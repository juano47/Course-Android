package com.delaiglesia.moviesapp.presentation.di.core

import com.delaiglesia.moviesapp.data.db.dao.ArtistDao
import com.delaiglesia.moviesapp.data.db.dao.MovieDao
import com.delaiglesia.moviesapp.data.db.dao.TvShowDao
import com.delaiglesia.moviesapp.data.repository.artist.ArtistLocalDataSource
import com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl.ArtistLocalDataSourceImpl
import com.delaiglesia.moviesapp.data.repository.movie.MovieLocalDataSource
import com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl.MovieLocalDataSourceImpl
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowLocalDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDao)
    }
}