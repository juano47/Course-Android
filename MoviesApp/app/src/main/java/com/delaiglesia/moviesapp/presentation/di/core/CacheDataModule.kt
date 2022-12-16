package com.delaiglesia.moviesapp.presentation.di.core

import com.delaiglesia.moviesapp.data.repository.artist.ArtistCacheDataSource
import com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl.ArtistCacheDataSourceImpl
import com.delaiglesia.moviesapp.data.repository.movie.MovieCacheDataSource
import com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl.MovieCacheDataSourceImpl
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowCacheDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMoviesCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowsCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistsCacheDataSource(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }
}