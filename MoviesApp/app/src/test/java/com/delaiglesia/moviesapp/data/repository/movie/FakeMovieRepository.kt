package com.delaiglesia.moviesapp.data.repository.movie

import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.domain.repository.MovieRepository

class FakeMovieRepository: MovieRepository {
    private val movies = mutableListOf<Movie>()

    init {
        movies.add(Movie(1, "overview", "poster_path", "2020-01-01", "title"))
        movies.add(Movie(2, "overview_2", "poster_path_2", "2020-01-02", "title_2"))
    }
    override suspend fun getMovies(): List<Movie> {
        return movies
    }

    override suspend fun updateMovies(): List<Movie> {
        movies.clear()
        movies.add(Movie(3, "overview_3", "poster_path_3", "2020-01-03", "title_3"))
        movies.add(Movie(4, "overview_4", "poster_path_4", "2020-01-04", "title_4"))
        return movies
    }
}