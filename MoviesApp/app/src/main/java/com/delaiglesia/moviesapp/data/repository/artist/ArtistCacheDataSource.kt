package com.delaiglesia.moviesapp.data.repository.artist

import com.delaiglesia.moviesapp.data.model.artist.Artist

interface ArtistCacheDataSource {
    suspend fun saveArtistsToCache(artists: List<Artist>)
    suspend fun getArtistsFromCache(): List<Artist>
}