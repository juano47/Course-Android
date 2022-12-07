package com.delaiglesia.moviesapp.presentation.di.tvShow

import com.delaiglesia.moviesapp.domain.usecase.GetTvShowsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateTvShowsUseCase
import com.delaiglesia.moviesapp.presentation.tvShow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ) = TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)

}