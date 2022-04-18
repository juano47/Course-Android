package com.anushka.didemo

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(var memorySize: Int) {

    @Provides
    fun provideMemoryCard(): MemoryCard {
        Log.i("MYTAG", "Memory size is $memorySize")
        return MemoryCard()
    }
}