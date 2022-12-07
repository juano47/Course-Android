package com.delaiglesia.moviesapp.data.repository.tvShow

import com.delaiglesia.moviesapp.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}