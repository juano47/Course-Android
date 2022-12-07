package com.delaiglesia.tvShowsapp.data.repository.tvShow.dataSourceImpl

import com.delaiglesia.moviesapp.data.model.tvshow.TvShow
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {
    private var tvShowList = ArrayList<TvShow>()

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }

    override suspend fun getTvShowsFromCache(): List<TvShow> = tvShowList
}