package com.delaiglesia.moviesapp.presentation.di.movie

import com.delaiglesia.moviesapp.domain.usecase.GetMoviesUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateMoviesUseCase
import com.delaiglesia.moviesapp.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ) = MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)

}