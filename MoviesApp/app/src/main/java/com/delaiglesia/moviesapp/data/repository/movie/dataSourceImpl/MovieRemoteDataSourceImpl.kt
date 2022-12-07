package com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl

import com.delaiglesia.moviesapp.data.api.TMDBService
import com.delaiglesia.moviesapp.data.model.movie.MovieList
import com.delaiglesia.moviesapp.data.repository.movie.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val apiTMDBService: TMDBService,
                                private val apiKey: String): MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> =
        apiTMDBService.getPopularMovies(apiKey)

}