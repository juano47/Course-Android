package com.delaiglesia.moviesapp.presentation.di.core

import com.delaiglesia.moviesapp.data.api.TMDBService
import com.delaiglesia.moviesapp.data.repository.artist.ArtistRemoteDataSource
import com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl.ArtistRemoteDataSourceImpl
import com.delaiglesia.moviesapp.data.repository.movie.MovieRemoteDataSource
import com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl.MovieRemoteDataSourceImpl
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowRemoteDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmdbService, apiKey)
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(tmdbService, apiKey)
    }

}