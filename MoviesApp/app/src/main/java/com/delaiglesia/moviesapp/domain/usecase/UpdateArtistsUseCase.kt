package com.delaiglesia.moviesapp.domain.usecase

import com.delaiglesia.moviesapp.domain.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {

    suspend fun invoke() = artistRepository.updateArtists()
}