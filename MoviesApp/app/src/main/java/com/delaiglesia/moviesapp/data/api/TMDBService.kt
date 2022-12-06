package com.delaiglesia.moviesapp.data.api

import com.delaiglesia.moviesapp.data.ArtistList
import com.delaiglesia.moviesapp.data.MovieList
import com.delaiglesia.moviesapp.data.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopularPeople(@Query("api_key") apiKey: String): Response<ArtistList>
}