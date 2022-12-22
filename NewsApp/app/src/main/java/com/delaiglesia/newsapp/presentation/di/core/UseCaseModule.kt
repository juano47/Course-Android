package com.delaiglesia.newsapp.presentation.di.core

import com.delaiglesia.newsapp.domain.repository.NewsRepository
import com.delaiglesia.newsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCase(newsRepository: NewsRepository) =
        GetNewsHeadlineUseCase(newsRepository)

    @Singleton
    @Provides
    fun provideDeletedSavedNewsUseCase(newsRepository: NewsRepository) =
        DeleteSavedNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCase(newsRepository: NewsRepository) =
        GetSavedNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(newsRepository: NewsRepository) =
        GetSearchedNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun provideSaveNewsUseCase(newsRepository: NewsRepository) =
        SaveNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun provideGetSavedArticleUseCase(newsRepository: NewsRepository) =
        GetSavedArticleUseCase(newsRepository)

}