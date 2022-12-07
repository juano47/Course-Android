package com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl

import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.data.repository.movie.MovieCacheDataSource
import com.delaiglesia.moviesapp.data.repository.movie.MovieLocalDataSource
import com.delaiglesia.moviesapp.data.repository.movie.MovieRemoteDataSource
import com.delaiglesia.moviesapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
): MovieRepository {

    override suspend fun getMovies(): List<Movie> = getMoviesFromCache()
    override suspend fun updateMovies(): List<Movie> {
        val newMovieList = getMoviesFromAPI()
        movieCacheDataSource.saveMoviesToCache(newMovieList)
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDb(newMovieList)
        return newMovieList
    }

    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var moviesFromAPI: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                moviesFromAPI = body.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return moviesFromAPI
    }

    private suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var moviesFromDB: List<Movie>
        try {
            moviesFromDB = movieLocalDataSource.getMoviesFromDb()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (moviesFromDB.isEmpty()) {
            moviesFromDB = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDb(moviesFromDB)
        }
        return moviesFromDB
    }

    private suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var moviesFromCache: List<Movie>
        try {
            moviesFromCache = movieCacheDataSource.getMoviesFromCache()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (moviesFromCache.isEmpty()) {
            moviesFromCache = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(moviesFromCache)
        }
        return moviesFromCache
    }
}