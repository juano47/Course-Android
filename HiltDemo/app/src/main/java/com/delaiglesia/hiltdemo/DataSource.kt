package com.delaiglesia.hiltdemo

import android.util.Log

//assume that this is a third party library that we cannot modify
class DataSource {
    fun getRemoteData(): String {
        Log.i("MyTag", "Downloading data from the internet")
        return "Downloadin data from the internet \n\n Data downloaded"
    }
}