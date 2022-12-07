package com.delaiglesia.moviesapp.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.moviesapp.domain.usecase.GetMoviesUseCase
import com.delaiglesia.moviesapp.domain.usecase.UpdateMoviesUseCase

class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
): ViewModelProvider.Factory {

    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
    }
}