package com.delaiglesia.moviesapp.data.repository.tvShow.dataSourceImpl

import com.delaiglesia.moviesapp.data.db.dao.TvShowDao
import com.delaiglesia.moviesapp.data.model.tvshow.TvShow
import com.delaiglesia.moviesapp.data.repository.tvShow.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao): TvShowLocalDataSource {
    //no need to use coroutines here, Room does it for us
    override suspend fun getTvShowsFromDb(): List<TvShow> = tvShowDao.getAllTvShows()

    override suspend fun saveTvShowsToDb(tvShows: List<TvShow>) {
        //we need to use coroutines here because Room doesn't do it for us
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }

}