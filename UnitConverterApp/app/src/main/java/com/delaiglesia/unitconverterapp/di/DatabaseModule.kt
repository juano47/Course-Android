package com.delaiglesia.unitconverterapp.di

import android.app.Application
import androidx.room.Room
import com.delaiglesia.unitconverterapp.data.ConverterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): ConverterDatabase {
        return Room.databaseBuilder(app, ConverterDatabase::class.java, "converter_database.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterDao(database: ConverterDatabase) = database.getConverterDao()
}