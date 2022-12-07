package com.delaiglesia.moviesapp.domain.usecase

import com.delaiglesia.moviesapp.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun invoke() = movieRepository.updateMovies()
}