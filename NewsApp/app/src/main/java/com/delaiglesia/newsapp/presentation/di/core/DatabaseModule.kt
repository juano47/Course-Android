package com.delaiglesia.newsapp.presentation.di.core

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
class DatabaseModule {
/*

    @Singleton
    @Provides
    fun provideDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(app, NewsDatabase::class.java, "news-app.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.newsDao()
*/


}