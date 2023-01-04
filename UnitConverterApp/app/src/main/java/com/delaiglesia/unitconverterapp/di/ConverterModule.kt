package com.delaiglesia.unitconverterapp.di

import com.delaiglesia.unitconverterapp.ConverterViewModelFactory
import com.delaiglesia.unitconverterapp.data.ConverterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ConverterModule {

    @ActivityScoped
    @Provides
    fun provideMovieViewModelFactory(converterRepository: ConverterRepository) =
        ConverterViewModelFactory(
            converterRepository
        )
}