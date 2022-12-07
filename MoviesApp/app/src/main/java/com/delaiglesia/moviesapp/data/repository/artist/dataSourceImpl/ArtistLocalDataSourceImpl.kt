package com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl

import com.delaiglesia.moviesapp.data.db.dao.ArtistDao
import com.delaiglesia.moviesapp.data.model.artist.Artist
import com.delaiglesia.moviesapp.data.repository.artist.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao): ArtistLocalDataSource {
    //no need to use coroutines here, Room does it for us
    override suspend fun getArtistsFromDb(): List<Artist> = artistDao.getAllArtists()

    override suspend fun saveArtistsToDb(artists: List<Artist>) {
        //we need to use coroutines here because Room doesn't do it for us
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }

}