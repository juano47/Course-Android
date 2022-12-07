package com.delaiglesia.moviesapp.data.repository.artist

import com.delaiglesia.moviesapp.data.model.artist.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDb(): List<Artist>
    suspend fun saveArtistsToDb(artists: List<Artist>)
    suspend fun clearAll()
}