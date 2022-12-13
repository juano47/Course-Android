package com.delaiglesia.moviesapp.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.delaiglesia.moviesapp.data.db.dao.MovieDao
import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var db: TMDBDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TMDBDatabase::class.java).build()
        movieDao = db.movieDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    //Unit is the default return type for functions in Kotlin and it means that the function does not return anything.
    fun saveMovies(): Unit =
        // runBlockingTest is a special test coroutine dispatcher that can be used to test coroutines.
        runBlocking {
            val movies = listOf(
                Movie(1, "overview", "poster_path", "2020-01-01", "title"),
                Movie(2, "overview_2", "poster_path_2", "2020-01-02", "title_2"),
            )

        movieDao.saveMovies(movies)

        val allMovies = movieDao.getAllMovies()
        assertThat(allMovies).containsExactlyElementsIn(movies)
    }

    @Test
fun deleteAllMovies(): Unit =
        runBlocking {
            val movies = listOf(
                Movie(1, "overview", "poster_path", "2020-01-01", "title"),
                Movie(2, "overview_2", "poster_path_2", "2020-01-02", "title_2"),
            )

        movieDao.saveMovies(movies)
        movieDao.deleteAllMovies()

        val allMovies = movieDao.getAllMovies()
        assertThat(allMovies).isEmpty()
    }

    @Test
    fun getAllMovies(): Unit =
        runBlocking {
            val movies = listOf(
                Movie(1, "overview", "poster_path", "2020-01-01", "title"),
                Movie(2, "overview_2", "poster_path_2", "2020-01-02", "title_2"),
            )

        movieDao.saveMovies(movies)

        val allMovies = movieDao.getAllMovies()
        assertThat(allMovies).containsExactlyElementsIn(movies)
    }
}