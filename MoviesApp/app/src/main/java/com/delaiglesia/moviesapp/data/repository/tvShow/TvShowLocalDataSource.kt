package com.delaiglesia.moviesapp.data.repository.tvShow

import com.delaiglesia.moviesapp.data.model.tvshow.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDb(): List<TvShow>
    suspend fun saveTvShowsToDb(tvShows: List<TvShow>)
    suspend fun clearAll()
}