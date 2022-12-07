package com.delaiglesia.moviesapp.presentation.di.movie

import com.delaiglesia.moviesapp.presentation.movie.MovieActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieActivity: MovieActivity)

    // Factory that is used to create instances of this subcomponent (MovieSubComponent) from the AppComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}