package com.delaiglesia.moviesapp.domain.usecase

import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun invoke(): List<Movie>? = movieRepository.getMovies()
}