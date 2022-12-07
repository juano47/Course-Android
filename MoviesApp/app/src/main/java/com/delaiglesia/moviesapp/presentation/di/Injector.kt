package com.delaiglesia.moviesapp.presentation.di

import com.delaiglesia.moviesapp.presentation.di.artist.ArtistSubComponent
import com.delaiglesia.moviesapp.presentation.di.movie.MovieSubComponent
import com.delaiglesia.moviesapp.presentation.di.tvShow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}