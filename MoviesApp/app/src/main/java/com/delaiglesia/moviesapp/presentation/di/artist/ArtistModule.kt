package com.delaiglesia.moviesapp.presentation.di.artist

import com.delaiglesia.moviesapp.domain.usecase.GetArtistsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateArtistsUseCase
import com.delaiglesia.moviesapp.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ) = ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)

}