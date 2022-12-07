package com.delaiglesia.moviesapp.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.delaiglesia.moviesapp.domain.usecase.GetArtistsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateArtistsUseCase

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
): ViewModel() {
    fun getArtists() = liveData {
        val artists = getArtistsUseCase.invoke()
        emit(artists)
    }

    fun updateArtists() = liveData {
        val artists = updateArtistsUseCase.invoke()
        emit(artists)
    }
}