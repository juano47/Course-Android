package com.delaiglesia.moviesapp.data.repository.artist.dataSourceImpl

import com.delaiglesia.moviesapp.data.api.TMDBService
import com.delaiglesia.moviesapp.data.model.artist.ArtistList
import com.delaiglesia.moviesapp.data.repository.artist.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(private val apiTMDBService: TMDBService,
                                 private val apiKey: String): ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> = apiTMDBService.getPopularArtists(apiKey)

}