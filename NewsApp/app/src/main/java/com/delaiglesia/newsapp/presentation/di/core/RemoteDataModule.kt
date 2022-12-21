package com.delaiglesia.newsapp.presentation.di.core

import com.delaiglesia.newsapp.data.api.NewsApiService
import com.delaiglesia.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.delaiglesia.newsapp.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)
    }

}