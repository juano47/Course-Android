package com.delaiglesia.moviesapp.presentation.di.tvShow

import com.delaiglesia.moviesapp.domain.usecase.GetTvShowsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateTvShowsUseCase
import com.delaiglesia.moviesapp.presentation.tvShow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
class TvShowModule {

    @ActivityScoped
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ) = TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)

}