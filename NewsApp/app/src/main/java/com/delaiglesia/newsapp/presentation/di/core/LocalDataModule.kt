package com.delaiglesia.newsapp.presentation.di.core

import com.delaiglesia.newsapp.data.db.dao.ArticleDao
import com.delaiglesia.newsapp.data.repository.dataSource.NewsLocalDataSource
import com.delaiglesia.newsapp.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideArticleLocalDataSource(movieDao: ArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(movieDao)
    }

}