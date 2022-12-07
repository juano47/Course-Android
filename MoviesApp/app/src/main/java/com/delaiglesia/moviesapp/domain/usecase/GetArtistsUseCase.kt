package com.delaiglesia.moviesapp.domain.usecase

import com.delaiglesia.moviesapp.data.model.artist.Artist
import com.delaiglesia.moviesapp.domain.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {

    suspend fun invoke(): List<Artist>? = artistRepository.getArtists()
}