package com.delaiglesia.newsapp.presentation.di.news

import android.app.Application
import com.delaiglesia.newsapp.domain.usecase.*
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class NewsModule {

    @ActivityScoped
    @Provides
    fun provideMovieViewModelFactory(
        app: Application,
        getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase,
        getSavedArticleUseCase: GetSavedArticleUseCase,
        deleteSavedNewsUseCase: DeleteSavedNewsUseCase
    ) = NewsViewModelFactory(
        app,
        getNewsHeadlineUseCase,
        getSearchedNewsUseCase,
        saveNewsUseCase,
        getSavedNewsUseCase,
        getSavedArticleUseCase,
        deleteSavedNewsUseCase
    )
}