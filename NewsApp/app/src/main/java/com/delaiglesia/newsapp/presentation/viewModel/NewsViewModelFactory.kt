package com.delaiglesia.newsapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.newsapp.domain.usecase.GetNewsHeadlineUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlineUseCase) as T
    }
}