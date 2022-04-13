package com.anushka.didemo

import android.util.Log
import javax.inject.Inject

//Quitamos el @Inject y el constructor para simular que es un objeto de terceros que no es nuestro
//y no se puede modificar
class MemoryCard {
    init {
        Log.i("MYTAG","Memory Card Constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Memory space available")
    }
}