package com.delaiglesia.moviesapp.data.repository.artist

import com.delaiglesia.moviesapp.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}