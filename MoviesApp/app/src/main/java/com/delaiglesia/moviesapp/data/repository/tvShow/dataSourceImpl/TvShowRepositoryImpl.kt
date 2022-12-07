package com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl

import com.delaiglesia.moviesapp.data.model.tvshow.TvShow
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowCacheDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowLocalDataSource
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowRemoteDataSource
import com.delaiglesia.moviesapp.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
): TvShowRepository {

    override suspend fun getTvShows(): List<TvShow> = getTvShowsFromCache()
    override suspend fun updateTvShows(): List<TvShow> {
        val newTvShowList = getTvShowsFromAPI()
        tvShowCacheDataSource.saveTvShowsToCache(newTvShowList)
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDb(newTvShowList)
        return newTvShowList
    }

    private suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShowsFromAPI: List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvShowsFromAPI = body.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return tvShowsFromAPI
    }

    private suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShowsFromDB: List<TvShow>
        try {
            tvShowsFromDB = tvShowLocalDataSource.getTvShowsFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (tvShowsFromDB.isEmpty()) {
            tvShowsFromDB = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDb(tvShowsFromDB)
        }
        return tvShowsFromDB
    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowsFromCache: List<TvShow>
        try {
            tvShowsFromCache = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (tvShowsFromCache.isEmpty()) {
            tvShowsFromCache = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowsFromCache)
        }
        return tvShowsFromCache
    }
}