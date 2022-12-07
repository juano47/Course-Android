package com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl

import com.delaiglesia.moviesapp.data.api.TMDBService
import com.delaiglesia.moviesapp.data.model.tvshow.TvShowList
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(private val apiTMDBService: TMDBService,
                                 private val apiKey: String): TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> = apiTMDBService.getPopularTvShows(apiKey)

}