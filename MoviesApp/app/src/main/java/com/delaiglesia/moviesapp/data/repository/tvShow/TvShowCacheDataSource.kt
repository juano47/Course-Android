package com.delaiglesia.moviesapp.data.repository.tvShow

import com.delaiglesia.moviesapp.data.model.tvshow.TvShow

interface TvShowCacheDataSource {
    suspend fun saveTvShowsToCache(movies: List<TvShow>)
    suspend fun getTvShowsFromCache(): List<TvShow>
}