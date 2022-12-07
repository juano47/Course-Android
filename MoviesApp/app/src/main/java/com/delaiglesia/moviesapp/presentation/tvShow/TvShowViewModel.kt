package com.delaiglesia.moviesapp.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.delaiglesia.moviesapp.domain.usecase.GetTvShowsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
): ViewModel() {
    fun getTvShows() = liveData {
        val artists = getTvShowsUseCase.invoke()
        emit(artists)
    }

    fun updateTvShows() = liveData {
        val artists = updateTvShowsUseCase.invoke()
        emit(artists)
    }
}