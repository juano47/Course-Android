package com.delaiglesia.unitconverterapp.di

import android.app.Application
import androidx.room.Room
import com.delaiglesia.unitconverterapp.data.ConverterDao
import com.delaiglesia.unitconverterapp.data.ConverterDatabase
import com.delaiglesia.unitconverterapp.data.ConverterRepository
import com.delaiglesia.unitconverterapp.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): ConverterDatabase {
        return Room.databaseBuilder(app, ConverterDatabase::class.java, "converter_database.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterDao(database: ConverterDatabase) = database.getConverterDao()

    @Singleton
    @Provides
    fun provideConverterRepository(
        converterDao: ConverterDao
    ): ConverterRepository = ConverterRepositoryImpl(converterDao)
}