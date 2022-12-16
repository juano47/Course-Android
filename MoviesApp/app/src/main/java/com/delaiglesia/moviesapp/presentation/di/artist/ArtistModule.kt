package com.delaiglesia.moviesapp.presentation.di.artist

import com.delaiglesia.moviesapp.domain.usecase.GetArtistsUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateArtistsUseCase
import com.delaiglesia.moviesapp.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
class ArtistModule {

    @ActivityScoped
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ) = ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)

}