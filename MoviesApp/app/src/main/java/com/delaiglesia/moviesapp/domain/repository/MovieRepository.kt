package com.delaiglesia.moviesapp.domain.repository

import com.delaiglesia.moviesapp.data.model.movie.Movie


interface MovieRepository {

    suspend fun getMovies(): List<Movie>
    suspend fun updateMovies(): List<Movie>
}