package com.delaiglesia.moviesapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delaiglesia.moviesapp.data.model.tvshow.TvShow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM tv_show")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM tv_show")
    suspend fun getAllTvShows(): List<TvShow>
}