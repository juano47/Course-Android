package com.delaiglesia.moviesapp.data.repository.movie.dataSourceImpl

import com.delaiglesia.moviesapp.data.db.dao.MovieDao
import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.data.repository.movie.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao): MovieLocalDataSource {
    //no need to use coroutines here, Room does it for us
    override suspend fun getMoviesFromDb(): List<Movie> = movieDao.getAllMovies()

    override suspend fun saveMoviesToDb(movies: List<Movie>) {
        //we need to use coroutines here because Room doesn't do it for us
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }

}