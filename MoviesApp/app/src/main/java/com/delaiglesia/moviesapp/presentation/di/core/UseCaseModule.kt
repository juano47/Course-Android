package com.delaiglesia.moviesapp.presentation.di.core

import com.delaiglesia.moviesapp.domain.repository.ArtistRepository
import com.delaiglesia.moviesapp.domain.repository.MovieRepository
import com.delaiglesia.moviesapp.domain.repository.TvShowRepository
import com.delaiglesia.moviesapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(movieRepository: MovieRepository) = GetMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideUpdateMoviesUseCase(movieRepository: MovieRepository) = UpdateMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetTvShowsUseCase(tvShowRepository: TvShowRepository) = GetTvShowsUseCase(tvShowRepository)

    @Singleton
    @Provides
    fun provideUpdateTvShowsUseCase(tvShowRepository: TvShowRepository) = UpdateTvShowsUseCase(tvShowRepository)

    @Singleton
    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository) = GetArtistsUseCase(artistRepository)

    @Singleton
    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository) = UpdateArtistsUseCase(artistRepository)

}