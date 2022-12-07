package com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl

import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.data.repository.movie.MovieCacheDataSource

class MovieCacheDataSourceImpl: MovieCacheDataSource {
    private var movieList = ArrayList<Movie>()

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }

    override suspend fun getMoviesFromCache(): List<Movie> = movieList
}