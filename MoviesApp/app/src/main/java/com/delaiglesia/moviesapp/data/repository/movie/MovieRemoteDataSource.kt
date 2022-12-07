package com.delaiglesia.moviesapp.data.repository.movie

import com.delaiglesia.moviesapp.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}