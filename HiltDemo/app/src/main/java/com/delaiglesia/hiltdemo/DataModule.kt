package com.delaiglesia.hiltdemo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
//SingletonComponent is the scope of the application and it is the default scope for Hilt
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideDataSource() = DataSource()

}