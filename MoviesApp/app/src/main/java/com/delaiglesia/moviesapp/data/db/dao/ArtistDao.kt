package com.delaiglesia.moviesapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delaiglesia.moviesapp.data.model.artist.Artist

@Dao
interface ArtistDao {
    //use suspend to make it a coroutine!
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM artist")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM artist")
    suspend fun getAllArtists(): List<Artist>
}