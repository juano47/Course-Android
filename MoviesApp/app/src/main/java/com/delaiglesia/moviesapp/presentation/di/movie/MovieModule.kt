package com.delaiglesia.moviesapp.presentation.di.movie

import com.delaiglesia.moviesapp.domain.usecase.GetMoviesUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateMoviesUseCase
import com.delaiglesia.moviesapp.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
class MovieModule {

    @ActivityScoped
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ) = MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)

}