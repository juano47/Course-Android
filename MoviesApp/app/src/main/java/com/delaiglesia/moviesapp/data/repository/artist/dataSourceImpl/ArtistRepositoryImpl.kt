package com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl

import com.delaiglesia.moviesapp.data.model.artist.Artist
import com.delaiglesia.moviesapp.data.repository.artist.ArtistCacheDataSource
import com.delaiglesia.moviesapp.data.repository.artist.ArtistLocalDataSource
import com.delaiglesia.moviesapp.data.repository.artist.ArtistRemoteDataSource
import com.delaiglesia.moviesapp.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
): ArtistRepository {

    override suspend fun getArtists(): List<Artist> = getArtistsFromCache()
    override suspend fun updateArtists(): List<Artist> {
        val newArtistList = getArtistsFromAPI()
        artistCacheDataSource.saveArtistsToCache(newArtistList)
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDb(newArtistList)
        return newArtistList
    }

    private suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artistsFromAPI: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if (body != null) {
                artistsFromAPI = body.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return artistsFromAPI
    }

    private suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistsFromDB: List<Artist>
        try {
            artistsFromDB = artistLocalDataSource.getArtistsFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (artistsFromDB.isEmpty()) {
            artistsFromDB = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDb(artistsFromDB)
        }
        return artistsFromDB
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistsFromCache: List<Artist>
        try {
            artistsFromCache = artistCacheDataSource.getArtistsFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (artistsFromCache.isEmpty()) {
            artistsFromCache = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistsFromCache)
        }
        return artistsFromCache
    }
}