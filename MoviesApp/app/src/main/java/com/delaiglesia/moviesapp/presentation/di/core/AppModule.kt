package com.delaiglesia.moviesapp.presentation.di.core

import android.app.Application
import android.content.Context
import com.delaiglesia.moviesapp.presentation.di.artist.ArtistSubComponent
import com.delaiglesia.moviesapp.presentation.di.movie.MovieSubComponent
import com.delaiglesia.moviesapp.presentation.di.tvShow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieSubComponent::class, TvShowSubComponent::class, ArtistSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}