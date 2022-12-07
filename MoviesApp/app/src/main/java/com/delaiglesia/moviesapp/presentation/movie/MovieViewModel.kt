package com.delaiglesia.moviesapp.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.delaiglesia.moviesapp.domain.usecase.GetMoviesUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateMoviesUseCase

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
): ViewModel() {

    //por que usamos liveData?
    // porque es un observable que se puede observar desde el fragment y se actualiza cuando cambia
    // el valor de la lista de peliculas
    fun getMovies() = liveData {
        val movies = getMoviesUseCase.invoke()
        emit(movies)
    }

    fun updateMovies() = liveData {
        val movies = updateMoviesUseCase.invoke()
        emit(movies)
    }
}