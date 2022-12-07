package com.delaiglesia.moviesapp.presentation.di.artist

import com.delaiglesia.moviesapp.presentation.artist.ArtistActivity
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {

    fun inject(artistActivity: ArtistActivity)

    // Factory that is used to create instances of this subcomponent (ArtistSubComponent) from the AppComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): ArtistSubComponent
    }
}