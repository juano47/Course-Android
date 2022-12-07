package com.delaiglesia.moviesapp.presentation

import android.app.Application
import com.delaiglesia.moviesapp.BuildConfig
import com.delaiglesia.moviesapp.presentation.di.Injector
import com.delaiglesia.moviesapp.presentation.di.artist.ArtistSubComponent
import com.delaiglesia.moviesapp.presentation.di.core.*
import com.delaiglesia.moviesapp.presentation.di.movie.MovieSubComponent
import com.delaiglesia.moviesapp.presentation.di.tvShow.TvShowSubComponent

class App: Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent =
        appComponent.movieSubComponent().create()

    override fun createTvShowSubComponent(): TvShowSubComponent =
        appComponent.tvShowSubComponent().create()

    override fun createArtistSubComponent(): ArtistSubComponent =
        appComponent.artistSubComponent().create()
}