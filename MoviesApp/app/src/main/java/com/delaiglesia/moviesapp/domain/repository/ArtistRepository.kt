package com.delaiglesia.moviesapp.domain.repository

import com.delaiglesia.moviesapp.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}