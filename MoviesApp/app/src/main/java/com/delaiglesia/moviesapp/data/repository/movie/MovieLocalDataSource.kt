package com.delaiglesia.moviesapp.data.repository.movie

import com.delaiglesia.moviesapp.data.model.movie.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDb(): List<Movie>
    suspend fun saveMoviesToDb(movies: List<Movie>)
    suspend fun clearAll()
}