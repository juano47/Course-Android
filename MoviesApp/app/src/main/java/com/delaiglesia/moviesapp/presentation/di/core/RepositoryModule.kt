package com.delaiglesia.moviesapp.presentation.di.core

import com.delaiglesia.moviesapp.data.repository.artist.ArtistCacheDataSource
import com.delaiglesia.moviesapp.data.repository.artist.ArtistLocalDataSource
import com.delaiglesia.moviesapp.data.repository.artist.ArtistRemoteDataSource
import com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl.ArtistRepositoryImpl
import com.delaiglesia.moviesapp.data.repository.movie.MovieCacheDataSource
import com.delaiglesia.moviesapp.data.repository.movie.MovieLocalDataSource
import com.delaiglesia.moviesapp.data.repository.movie.MovieRemoteDataSource
import com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl.MovieRepositoryImpl
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowCacheDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowLocalDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowRemoteDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl.TvShowRepositoryImpl
import com.delaiglesia.moviesapp.domain.repository.ArtistRepository
import com.delaiglesia.moviesapp.domain.repository.MovieRepository
import com.delaiglesia.moviesapp.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource) : MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource) : TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource) : ArtistRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource,
            artistLocalDataSource,
            artistCacheDataSource
        )
    }
}