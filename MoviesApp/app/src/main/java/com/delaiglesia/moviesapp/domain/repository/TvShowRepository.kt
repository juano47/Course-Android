package com.delaiglesia.moviesapp.domain.repository

import com.delaiglesia.moviesapp.data.model.tvshow.TvShow

interface TvShowRepository {

        suspend fun getTvShows(): List<TvShow>?
        suspend fun updateTvShows(): List<TvShow>?
}