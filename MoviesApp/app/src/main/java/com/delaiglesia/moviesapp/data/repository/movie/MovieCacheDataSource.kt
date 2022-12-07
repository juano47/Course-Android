package com.delaiglesia.moviesapp.data.repository.movie

import com.delaiglesia.moviesapp.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun saveMoviesToCache(movies: List<Movie>)
    suspend fun getMoviesFromCache(): List<Movie>
}