package com.anushka.didemo

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

//es necesario hacer el constructor publico para que el dagger pueda inyectar el objeto
@Singleton
class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailablity()
        Log.i("MYTAG", "SmartPhone Constructed")
    }

    fun makeACallWithRecording() {
        Log.i("MYTAG", "Calling.....")
    }
}

