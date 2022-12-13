package com.delaiglesia.moviesapp.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.data.repository.movie.FakeMovieRepository
import com.delaiglesia.moviesapp.domain.usecase.GetMoviesUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateMoviesUseCase
import com.delaiglesia.moviesapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    //necessary to test live data
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        movieViewModel = MovieViewModel(getMoviesUseCase, updateMoviesUseCase)
    }

    @Test
    fun getMovies() {
        val expectedMovies = mutableListOf<Movie>()
        expectedMovies.add(Movie(1, "overview", "poster_path", "2020-01-01", "title"))
        expectedMovies.add(Movie(2, "overview_2", "poster_path_2", "2020-01-02", "title_2"))

        val movies = movieViewModel.getMovies().getOrAwaitValue {  }

        assertNotNull(movies)
        assertEquals(2, movies?.size)
        assertThat(movies).containsExactlyElementsIn(expectedMovies)
    }

    @Test
    fun updateMovies() {
        val expectedMovies = mutableListOf<Movie>()
        expectedMovies.add(Movie(3, "overview_3", "poster_path_3", "2020-01-03", "title_3"))
        expectedMovies.add(Movie(4, "overview_4", "poster_path_4", "2020-01-04", "title_4"))

        val movies = movieViewModel.updateMovies().getOrAwaitValue {  }

        assertNotNull(movies)
        assertEquals(2, movies?.size)
        assertThat(movies).containsExactlyElementsIn(expectedMovies)
    }

}

