package com.delaiglesia.moviesapp.presentation.di.tvShow

import com.delaiglesia.moviesapp.presentation.tvShow.TvShowActivity
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {

    fun inject(tvShowActivity: TvShowActivity)

    // Factory that is used to create instances of this subcomponent (TvShowSubComponent) from the AppComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}