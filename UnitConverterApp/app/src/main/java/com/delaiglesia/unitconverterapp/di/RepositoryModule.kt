package com.delaiglesia.unitconverterapp.di

import com.delaiglesia.unitconverterapp.data.ConverterDao
import com.delaiglesia.unitconverterapp.data.ConverterRepository
import com.delaiglesia.unitconverterapp.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideConverterRepository(
        converterDao: ConverterDao
    ): ConverterRepository = ConverterRepositoryImpl(converterDao)

}