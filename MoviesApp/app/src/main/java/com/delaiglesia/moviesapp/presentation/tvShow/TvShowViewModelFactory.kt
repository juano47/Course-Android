package com.delaiglesia.moviesapp.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.moviesapp.domain.usecase.GetTvShowsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModelFactory(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
): ViewModelProvider.Factory {
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTvShowsUseCase, updateTvShowsUseCase) as T
    }
}