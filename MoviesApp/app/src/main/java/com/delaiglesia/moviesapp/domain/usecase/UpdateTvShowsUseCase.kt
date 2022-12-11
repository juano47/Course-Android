package com.delaiglesia.moviesapp.domain.usecase

import com.delaiglesia.moviesapp.data.model.tvshow.TvShow
import com.delaiglesia.moviesapp.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun invoke(): List<TvShow> = tvShowRepository.updateTvShows()
}