package com.delaiglesia.newsapp.presentation.di.core

import com.delaiglesia.newsapp.data.repository.NewsRepositoryImpl
import com.delaiglesia.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.delaiglesia.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository = NewsRepositoryImpl(newsRemoteDataSource)

}