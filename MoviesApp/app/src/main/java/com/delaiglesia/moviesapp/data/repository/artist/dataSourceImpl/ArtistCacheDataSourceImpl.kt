package com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl

import com.delaiglesia.moviesapp.data.model.artist.Artist
import com.delaiglesia.moviesapp.data.repository.artist.ArtistCacheDataSource

class ArtistCacheDataSourceImpl: ArtistCacheDataSource {
    private var artistList = ArrayList<Artist>()

    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }

    override suspend fun getArtistsFromCache(): List<Artist> = artistList
}