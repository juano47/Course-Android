package com.delaiglesia.hiltdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application()
//no need to add anything here, Hilt will do the work for us